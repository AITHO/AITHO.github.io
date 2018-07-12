---
layout: post
title:  "Configurare accesso SSH con chiave pubblica/privata"
date:   2018-07-11 11:37:26 +0200
category: tutorial
tags: [ssh, linux, sysop]
authors:
- gabriele
show_excerpt: true
---
In questa guida configureremo l'accesso tramite chiave pubblica/privata. Il server a cui ci collegheremo ha come sistema operativo Debian Strecth, mentre per collegarci useremo una macchina windows e Git Bash come shell.       
<!--more-->
# Glossario  
- Server: la macchina a cui vogliamo connetterci  
- Client: la macchina da cui vogliamo connetterci
  
# Setup Server  
Da amministratore (o sudo) lanciare  
```
# apt-get install openssh-server;
# systemctl enable sshd;
# systemctl start sshd;
```
Il primo comando installa il server SSH, il secondo e il terzo avviano il servizio e si assicurano che venga avviato allo startup della macchina.  

# Creazione coppia di chiavi
Dalla macchina Client digitare nella shell
```
$ ssh-keygen -t rsa;
Generating public/private rsa key pair.
Enter file in which to save the key (/c/Users/user/.ssh/id_rsa): /c/Users/user/.ssh/server_rsa
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
The key fingerprint is:
SHA256:X+4PNyCpl1111138Mmgp6IhkFxVD+XxXx+ACBDE user@CLIENT
The key's randomart image is:
+---[RSA 2048]----+
|    _        _   |
|  |   |    |   | |
|  | _ |    | _ | |  
|                 |
|                 |
| .             . |
|   . . ... . .   |
|    .........    |
|      .. . .     |
+----[SHA256]-----+

```
Il primo comando specifica la posizione in cui saranno salvate la chiave privata (/c/Users/user/.ssh/server_rsa) e pubblica (/c/Users/user/.ssh/server_rsa.pub).
La passphrase può essere lasciata vuota. In tal caso sarà possibile effettuare il login tramite ssh senza bisogno di inserire alcuna password. Se si vuole incrementare la sicurezza è possibile impostarla e verrà richiesta ad ogni login. In questo modo un possibile attaccante dovrà venire in possesso sia della chiave privata che della passphrase per ottenere accesso al server.

# Copiare la chiave pubblica sul server
Bisogna autorizzare la chiave privata generata nel Client aggiungendo la chiave pubblica alla lista di chiavi autorizzate del server.

Dal Client lanciare

```
$ cat c/Users/user/.ssh/server_rsa.pub | ssh user@SERVER 'cat >> .ssh/authorized_keys'
Enter password:
```
Inserire la password dell'utente di cui si vuole registrare la chiave.

Modificare il file c/Users/user/.ssh/config aggiungendo
```
Host SERVER
    IdentityFile /c/Users/user/.ssh/server_rsa
```
Per verificare che tutto sia andato a buon fine, digitate
```
ssh user@SERVER
```
dovrebbe richiedere di inserire la Passphrase (se impostata) e farvi loggare su SERVER!


# Mettere in sicurezza il server
Ci sono alcuni accorgimenti che è possibile effettuare su Server per rafforzare la sicurezza.
1. Disabilitare l'accesso di root via SSH (da fare solo se esistono altri utenti)
2. Disabilitare l'accesso tramite password
3. Intallare softare di monitoraggio come Fail2Ban (guida dettagliata prossimamente)

Per il punto 1 basta impostare `PermitRootLogin no` nel file `/etc/ssh/sshd_config`.

Per il punto 2 `PasswordAuthentication no` sempre nel file `/etc/ssh/sshd_config`.
---
layout: post
title:  "Tutorial DialogFlow!"
date:   2018-07-11 11:37:26 +0200
categories: dialogflow ia
---
# Dialogflow  
Dialogflow è un servizio offerto nella suite Google Cloud Platform (GCP) tramite il quale è possibile sviluppare applicazioni interattive facilmente integrabili in siti web, piattaforme di messagistica, applicazioni mobile e web app, fornendo all'utente nuovi modi per interagire con il prodotto.  
Il tutorial illustrerà i processi e gli applicativi necessari per implementare un chatbot capace di rispondere ai messaggi utente in modo intelligente.  
### WebApp  
- Configurazione di un Agent.  
- Configurazione chatbot Meteo.  
- Conversation flow schema.  
  
### Webhook  
- Webhook.  
- Configurazione progetto.  
- Configurazione Agent Webhook.  
  
# Configurazione di un Agent  
Il chatbot che andremo a sviluppare dovrà essere capace di interpretare i messaggi utente al fine di fornire una risposta corretta e mirata alla specifica richiesta. Di seguito vedremo come implementare un **Agent**, la componente Dialogflow che permette di associare ad un set di frasi in linguaggio naturale una o più azioni.  
Facciamo un po' di chiarezza considerando il seguente messaggio: "Vorrei conoscere il meteo di domani a Catania". L'Agent del nostro chatbot deve essere capace di estrarre le informazioni relative all'azione, al periodo ed al luogo. Tuttavia quando un Agent viene creato, non è in grado sin da subito di elaborare il messaggio di esempio proposto. E' necessario avviare una specifica procedura detta **Training** tramite la quale lo si istruisce sulla base di alcune frasi tipo.     
Vediamo quindi come generare ed istruire l'Agent:  
1. Accedi al portale Dialogflow tramite il seguente [link](https://dialogflow.com).  
2. Crea un Agent tramite l'apposito menù laterale.  
3. Nelle impostazioni di base specifica il `Defaut Language` selezionando la lingua italiana `IT-it`.  
4. Accedi alle impostazioni dell'Agent ed abilita il bottone `V2 API`.  
4. Accedi alla sezione `Intents` dal menù laterale.  
5. Crea un nuovo Intent.  
6. Nella sezione `Training phrases` della configurazione dell'Intent abilita la modalità template cliccando sul simbolo `"` quindi inserisci la seguente lista di frasi:  
  - `Che tempo farà @sys.date:date a @sys.geo-city:city`
  - `Che tempo farà a @sys.geo-city:city @sys.date:date`
  - `Condizioni climatiche @sys.date:date a @sys.geo-city:city`
  - `Condizioni climatiche a @sys.geo-city:city @sys.date:date`
  - `Condizioni metereologiche @sys.date:date a @sys.geo-city:city`
  - `Condizioni metereologiche a @sys.geo-city:city @sys.date:date`
  - `Meteo @sys.date:date a @sys.geo-city:city`
  - `Meteo a @sys.geo-city:city @sys.date:date`
  - `Clima @sys.date:date a @sys.geo-city:city`
  - `Clima a @sys.geo-city:city @sys.date:date`
  - `Che tempo c'è @sys.date:date a @sys.geo-city:city`
  - `Che tempo c'è a @sys.geo-city:city @sys.date:date`
7. Salva l'Intent ed attendi il completamento del processo notificato da un apposito popup.  
  
La modalità template consente di inserire le **Entities** (dati di particolare interesse) nella frase.  
Riprendiamo l'esempio proposto precedentemente: `Vorrei conoscere il meteo di domani a Catania`. Dopo aver salvato la configurazione ed aver concluso la fase di training, l'Agent riesce a riconoscere la richiesta dell'utente attivando l'Intent appena creato, permettendo allo stesso tempo l'estrazione del luogo e della data (le Entities) qualora siano presenti.  
Quando si configurano Agent con molteplici **Intents** potrebbe accadere che una frase in linguaggio naturale venga interpretata in modo errato. In queste situazioni, per miglioare il riconoscimento degli Intents, è necessario arricchire i modelli inserendo nuovi esempi.  
  
# Configurazione chatbot Meteo    
Abbiamo visto come creare ed istruire l'**Agent** del nostro chatbot, tuttavia prima di andare avanti con il tutorial è bene introdurre tre nuovi concetti:  
1. **Contexts**: gli Intent hanno la possibilità di generare dei metadati in uscita, detti **Contexts**, tramite i quali è possibile tenere traccia dell'andamento della conversazione.  
2. **Responses**:  gli Intent, dopo essere stati attivati dalla frase utente, hanno la possibilità di inviare una risposta automatica di default.  
3. **Fullfilment text**: in modo analogo alle responses è possibile inoltrare l'azione dell'Intent al backend dell'applicazione Dialogflow qualora il bottone `Enable webhook call for this intent` sia stato abilitato. Cosi facendo le risposte possono essere costruite in modo dinamico, anche per mezzo di servizi esterni. Nel nostro caso l'ultima operazione effettuata dal chatbot meteo è ottenuta invocando il servizio [OpenWeatherMap](https://www.openweathermap.org/api) con gli appositi parametri collezionati (luogo, data).

Spiegati questi nuovi concetti, possiamo procedere alla configurazione dell'Agent per il chatbot meteo. Di seguito vengono proposte tre strategie che permettono di implementare il flow della conversazione:  
- **[Intents nidificati](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/follow-up)**    
  - **[get-richiesta-meteo-splitted](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/follow-up/get-richiesta-meteo-splitted.json)**: attivato quando l'Agent riceve una frase di tipo 'richiesta informazioni meteo'.  
  - **[get-luogo-meteo-splitted](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/follow-up/get-luogo-meteo-splitted.json)**: attivato quando l'utente digita un luogo. Al fine di restringere i casi di attivazione e' stato definito come **Intent follow-up**. In questo modo viene inserito come "nodo figlio" di 'get-richiesta-meteo-splitted'. Al "nodo padre" ed al "nodo figlio" viene impostato rispettivamente come `Output Context` ed `Input Context` il contesto `get-richiesta-meteo-splitted-followup`.  
  - **[get-data-meteo-splitted](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/follow-up/get-data-meteo-splitted.json)**: attivato quando l'utente digita una data. Al fine di restringere i casi di attivazione e' stato definito come **Intent follow-up**. In questo modo viene inserito come "nodo figlio" di 'get-luogo-meteo-splitted'. Al "nodo padre" ed al "nodo figlio" viene impostato rispettivamente come `Output Context` ed `Input Context` il contesto `get-luogo-meteo-splitted-followup`.  
- **[Intent con Prompt](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/compact)**    
  - **[get-richiesta-meteo-compact](/files/chatbot/compact/get-richiesta-meteo-compact.json)**: attivato quando l'Agent riceve una frase di tipo 'richiesta informazioni meteo'. A differenza del modello a follow-up è lo stesso Intent ad occupersi di 'raccogliere' i dati necessari (data e luogo). Qualora l'utente non avesse specificato i parametri nella frase, questi verranno richiesti inviando un ulteriore messaggio definito come promt del parametro in questione.  
- **[Intent con eventi](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/event-driven)**  
  - **[get-richiesta-meteo-compact-event-driven](/files/chatbot/event-driven/get-richiesta-meteo-compact-event-driven.json)**: attivato quando l'Agent riceve una frase di tipo 'richiesta informazioni meteo'. Come il precedente, anche quì è lo stesso Intent ad occupersi di 'raccogliere' i dati necessari (data e luogo). A differenza del precedente invece, qualora l'utente non avesse specificato i parametri nella frase sarà il Webhook a decidere cosa richiedere, invocando due appositi eventi (gli Intents definiti a seguire).
  - **[ask-luogo-meteo](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/event-driven/ask-luogo-meteo.json)**: attivato tramite API dal backend quando, dopo aver ricevuto i parametri dall'Intent `get-richiesta-meteo-compact-event-driven`, non riesce a recuperare il luogo. Ha il compito di chiedere il luogo.  
  - **[get-luogo-meteo](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/event-driven/get-luogo-meteo.json)**: attivato quando l'utente digita un luogo. Al fine di restringere i casi di attivazione e' stato definito come **Intent follow-up**. In questo modo viene inserito come "nodo figlio" di 'ask-luogo-meteo'. Al "nodo padre" ed al "nodo figlio" viene impostato rispettivamente come `Output Context` ed `Input Context` il contesto `ask-luogo-meteo-followup`.  
  - **[ask-data-meteo](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/event-driven/ask-data-meteo.json)**: attivato tramite API dal backend quando, dopo aver ricevuto i parametri dall'Intent `get-richiesta-meteo-compact-event-driven`, non riesce a recuperare la data. Ha il compito di chiedere la data.  
  - **[get-data-meteo](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/event-driven/get-data-meteo.json)**: attivato quando l'utente digita una data. Al fine di restringere i casi di attivazione e' stato definito come **Intent follow-up**. In questo modo viene inserito come "nodo figlio" di 'ask-data-meteo'. Al "nodo padre" ed al "nodo figlio" viene impostato rispettivamente come `Output Context` ed `Input Context` il contesto `ask-data-meteo-followup`.  
  
# Conversation flow schema  
Gli Agent Dialogflow permettono di controllare il flusso dei messaggi scambiati tra il bot (l'Agent) e l'utente.  
Chat particolarmente complesse ed articolate sono difficili da gestire. Il chatbot deve poter fornire rispote mirate ad ogni possibile nodo della conversazione. A tal fine è una buona pratica progettare ancor prima di iniziare gli sviluppi delle configurazioni dell'Agent un **conversation flow schema** (una mappa completa della conversazione). Esistono diversi tool che permettono di realizzare questi schemi, noi suggeriamo [MindMap](http://app.mindmapmaker.org), un software gratuito mediante il quale è possibile creare mappe di grafi connessi.  
Configurazione Chatbot Meteo Mindmap: [Download JSON](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/schema/Agent.json)  
![link esempio](/files/chatbot/schema/Agent.png)
  
# Webhook  
Il backend del chatbot Meteo deve essere in grado di fornire all'utente le informazioni metereologiche richieste. Quest'operazione non può essere eseguita in modo diretto dall'Agent, è necessario abilitare una nuova funzionalità tramite la quale è possibile delegare ad un'altra applicazione l'output di uno specifico Intent. Nel nostro caso la gestione della risposta finale viene affidata al backend del chatbot. Per abilitare il Webhook per il chatbot Meteo bisogna effettuare le operazioni riportate di seguito:  
- Accedi al pannello Dialogflow  
- Seleziona l'Agent 'Meteo'  
- Accedi al menù 'Fullfilment'  
- Abilita il Webhook (click sul bottone)  
  
Dopo aver abilitato questa funzionalità tutti gli Intent a cui è stato abilito il bottone Webhook nella sezione fullfilment text, inviano una richiesta HTTP al backend se attivati da una frase utente.  
# Configurazione progetto  
Premesse:  
- L'implementazione proposta di seguito utilizza Apache Tomcat per la configurazione del server.  
- L'IDE utilizzato è Eclipse Oxygen.  
- La versione di Java utilizzata è la 8.  

Procediamo: 
1. Installa Tomcat
2. Configura progetto Java
  - File > New Project > Dynamic Web Project
  - Imposta la seguente configurazione
```
Project name: ChatbotMeteo
Target runtime: Apache Tomcat v9.0
```
  - Lascia tutti gli altri parametri con i valori di default  
3. Crea la servlet Webhook  
  - Esegui: `Click destro sulla cartella src > new > package`
  - Inserisci come `name` il valore `org.aitho.chatbot`  
  - Completa l'operazione lasciando tutti gli altri parametri con i valori di default
  - Esegui: `Click destro sul package appena creato > new > servlet`  
  - Inserisci come `classname` il valore `Webhook`  
  - Completa l'operazione lasciando tutti gli altri parametri con i valori di default  
  - Esegui: `Click destro sulla cartella src > new > source folder`
  - Inserici come `Folder Name` il valore `libs`
  - Completa l'operazione lasciando tutti gli altri parametri con i valori di default  
  - Inserisci la libreria [Gson](http://central.maven.org/maven2/com/google/code/gson/gson/2.8.5/gson-2.8.5.jar) nella cartella `libs` appena creata 
  - Esegui: `Click destro > Build Path > Add to Build Path`
  - Inserisci la libreria [Gson](http://central.maven.org/maven2/com/google/code/gson/gson/2.8.5/gson-2.8.5.jar) nella cartella `WebContent > WEB-ING > lib` 
  4. Crea una configurazione server Tomcat  
  - Abilita la tab `Servers`: Window > show view > servers  
  - Tasto destro sul box della nuova tab > new > server  
  - Seleziona `Tomcat v9.0 Server` quindi `next`  
  - Seleziona ChatbotMeteo quindi `add`  
  - Completa l'operazione lasciando tutti gli altri parametri con i valori di default 
5. Avvia il Webhook  
  - Tasto destro sulla nuova configurazione server Tomcat > start
  
A questo punto la configurazione base dell'endpoint `localhost:8080/WebchatMeteo` è terminata. Tutte le richieste `HTTP GET` ed `HTTP POST` inoltrate a quell'indirizzo vengono gestite dalla classe Webhook per mezzo dei metodi `doGet(...){...}` e `doPost(...){...}`.  
# Configurazione Agent Webhook  
Come primo passo bisogna esternalizzare l'indirizzo del Webhook (il server Tomcat è in esecuzione sulla porta 8080 del localhost).  
Per semplicità suggeriamo di scaricare il tool gratuito [ngrok](https://ngrok.com/).  
Per configurare il Webhook passando per ngrok: 
- Estrai l'archivio ngrok
- Apri il terminale e spostati sulla cartella dell'eseguibilie `cd xx/yy/zz/ngrok`
- Esegui il comando `ngrok http 8080`

Nel terminale, dopo aver eseguito il comando `ngrok http 8080`, compaiono due voci `Forwarding` relative agli endpoint `https` ed `http`. Bisogna copiare nella clipboard il primo elemento (quello che inizia per `http://...`).
A questo punto inserire l'indirizzo precedentemente copiato nella configurazione dell'Agent Dialogflow.  
Di seguito è allegata la cartella dei sorgenti del backend del chatbot sviluppato in questo tutorial. Per poter comunicare con il chatbot Meteo, per praticità, abbiamo utilizzato la chat offerta dalla stessa piattaforma Dialogflow (sidebar destra).  

[Chatbot Meteo](https://github.com/AITHO/AITHO.github.io/blob/master/files/chatbot/backend).  
Nella configurazione proposta non abbiamo gestito l'informazione 'date', le condizioni metereologiche sono tornate sulla base della data odierna (limite del servizio che offre le API in versione gratuita).  
**Modificare la API Key, altrimenti il [servizio metereologico](https://www.openweathermap.org/api)  non tornera' risultati!**












---
layout: default
title: Category
---
{% comment%}
Here we generate all the categories.
{% endcomment%}

{% assign rawcats = "" %}
{% for post in site.posts %}
{% assign tcats = post.category | join:'|' | append:'|' %}
{% assign rawcats = rawcats | append:tcats %}
{% endfor %}

{% assign rawcats = rawcats | split:'|' | sort %}

{% assign cats = "" %}

{% for cat in rawcats %}
{% if cat != "" %}

{% if cats == "" %}
{% assign cats = cat | split:'|' %}
{% endif %}

{% unless cats contains cat %}
{% assign cats = cats | join:'|' | append:'|' | append:cat | split:'|' %}
{% endunless %}
{% endif %}
{% endfor %}

<h1 class="page-title">
  {{ page.title }}
</h1>
<br/>

<div class="posts">
<p>
{% for ct in cats %}
<a href="#{{ ct | slugify }}" class="codinfox-category-mark" style="color:#999;text-decoration: none;" onclick="showBox('{{ ct | slugify }}')"> {{ ct }} </a> &nbsp;&nbsp;
{% endfor %}
<a href="#no-category" class="codinfox-category-mark" style="color:#999;text-decoration: none;" onclick="showBox('no-category')"> No Category </a> &nbsp;&nbsp;
</p>

{% for ct in cats %}
<div class="hidebox {{ ct | slugify }}" style="display: none;">
<h2 id="{{ ct | slugify }}">{{ ct }}</h2>
<ul class="codinfox-category-list {{ ct | slugify }}">
  {% for post in site.posts %}
  {% if post.category contains ct %}
  <li>
    <h3>
      <a href="{{ post.url }}">
        {{ post.title }}
        <small>{{ post.date | date_to_string }}</small>
      </a>
    </h3>
  </li>
  {% endif %}
  {% endfor %}
</ul>
</div>
{% endfor %}

<div class="hidebox no-category">
<h2>No Category</h2>
<ul class="codinfox-category-list no-category">
  {% for post in site.posts %}
  {% unless post.category %}
  <li>
    <h3>
      <a href="{{ post.url }}">
        {{ post.title }}
        <small>{{ post.date | date_to_string }}</small>
      </a>
      {% for tag in post.tags %}
      <a class="codinfox-tag-mark" href="/blog/tag/#{{ tag | slugify }}">{{ tag }}</a>
      {% endfor %}
    </h3>
  </li>
  {% endunless %}
  {% endfor %}
</ul>
</div>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/showBox.js"></script>
</div>
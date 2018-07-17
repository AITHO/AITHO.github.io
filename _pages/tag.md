---
layout: default
title: Tag
---
{% comment%}
Here we generate all the tags.
{% endcomment%}



<h1 class="page-title">
  {{ page.title }}
</h1>
<div class="posts">

{% capture site_tags %}{% for tag in site.tags %}{{ tag | first }}{% unless forloop.last %},{% endunless %}{% endfor %}{% endcapture %}
{% assign tags = site_tags | split:',' | sort %}
{% assign tag_count = 0 %}
{% for tag in site_tags %}
{% assign tag_count = tag_count | plus: site.tags[tag].size %}
{% endfor %}

{% for tag in tags %}
{% assign rel_tag_size = site.tags[tag].size | times: 4.0 | divided_by: tag_count | plus: 1 %}
<span style="white-space: nowrap; font-size: {{ rel_tag_size }}em; padding: 0.6em;">	<a href="#{{ tag | slugify }}" class="tag" onclick="showBox('{{ tag | slugify }}')">{{ tag | slugize }} ({{ site.tags[tag].size }})</a>
</span>
{% endfor %}

{% for tag in tags %}
<div class="hidebox {{ tag | slugify }}" style="display: none;">
<h2 id="{{ tag | slugify }}">{{ tag }}</h2>
<ul class="codinfox-category-list">
  {% for post in site.posts %}
  {% if post.tags contains tag %}
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
  {% endif %}
  {% endfor %}
</ul>
</div>
{% endfor %}

<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/showBox.js"></script>

</div>
---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: home
---
{% for category in site.categories %}
    <li><a href="{{category.url}}"><strong>{{category|first}}</strong></a></li>
{% endfor %}
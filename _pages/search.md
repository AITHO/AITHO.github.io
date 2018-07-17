---
layout: page
title: Search
permalink: /search/
---

Powered by <a href="http://elasticlunr.com/">elasticlunr.js</a>.

<br/>&nbsp;
<form action="get" id="site_search">
<center>
  <input style="font-size:20px;" type="text" id="search_box">
  <input style="font-size:20px;" type="submit" value="Go!">
</center>
</form>
<br/>&nbsp;
<br/>&nbsp;

<ul id="search_results"></ul>


<script src="/js/elasticlunr.min.js"></script>
<script src="/js/lunr.stemmer.support.min.js"></script>
<script src="/js/lunr.it.min.js"></script>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/search.js"></script>
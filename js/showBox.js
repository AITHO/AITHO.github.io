function showBox(box) {
    $(".hidebox").each(function( index ) {
      $(this).hide() ;
    });
    $("."+box).show();
    
    }
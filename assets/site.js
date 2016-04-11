(function($){
  $(function(){
    // hideables
    $("pre.src-javacc, pre.src-java").each(function(i, h){
      var $h = $(h);

      var type = $h.attr("class").replace(/.*src\-([a-z]+).*/, "$1");

      var $showLink = $( "<a href='#'>show " + type + "</a>" );

      $showLink.click(function(event){
        if( $h.is(":hidden") ) {
          $h.show();
          $showLink.text($showLink.text().replace("show", "hide"));
        } else {
          $h.hide();
          $showLink.text($showLink.text().replace("hide", "show"));
        }

        event.preventDefault();
      });
      $h.hide();
      $h.before($showLink)
    });
  });
})(window.jQuery)

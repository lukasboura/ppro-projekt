
$(document).ready(function(){
		
	(function($) {
	    $.fn.textfill = function(maxFontSize) {
	        maxFontSize = parseInt(maxFontSize, 10);
	        return this.each(function(){
	            var ourText = $("span", this),
	                parent = ourText.parent(),
	                maxHeight = parent.height(),
	                maxWidth = parent.width(),
	                fontSize = parseInt(ourText.css("fontSize"), 10),
	                multiplier = maxWidth/ourText.width(),
	                newSize = (fontSize*(multiplier-0.1));
	            ourText.css(
	                "fontSize", 
	                (maxFontSize > 0 && newSize > maxFontSize) ? 
	                    maxFontSize : 
	                    newSize
	            );
	        });
	    };
	})(jQuery);
	
	
	$('#jmenofill').textfill(22);
	 $('#usernamefill').textfill(13);
	//Get height
	var divHeight = $(document).height();;

	//Apply css rule
	$('#content').css('height', divHeight);
	$('body').css('height', divHeight);
	$(function() {
	    $( "#tabs" ).tabs();
	});
	$('#tab1').addClass('active');
	$('#tab1').click(function() {
	      $(this).addClass('active');
	      $('#tab2').removeClass('active');
	      $('#tab3').removeClass('active');
	      $('#tab4').removeClass('active');
	  });
	$('#tab2').click(function() {
		$(this).addClass('active');
	      $('#tab1').removeClass('active');
	      $('#tab3').removeClass('active');
	      $('#tab4').removeClass('active');
	  });
	$('#tab3').click(function() {
		$(this).addClass('active');
	      $('#tab1').removeClass('active');
	      $('#tab2').removeClass('active');
	      $('#tab4').removeClass('active');
	  });
	$('#tab4').click(function() {
		$(this).addClass('active');
	      $('#tab1').removeClass('active');
	      $('#tab2').removeClass('active');
	      $('#tab3').removeClass('active');
	  });
	

	$('#txtJQuery').focus(function(){
	$('#txtJQuery').animate({width:150},500);
});
	

	$('#txtJQuery').focusout(function(){
	if(!$('#txtJQuery').val()){
	$('#txtJQuery').animate({width:110},500);}
});
	
});




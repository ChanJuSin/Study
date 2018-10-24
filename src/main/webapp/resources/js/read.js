$(function() {
	$(".read-page_attachments input[type='button']").on("click", () => {
		if($(".read-page_attachments-items").css("display") === "none") {
			return $(".read-page_attachments-items").show();	
		} 
		
		return $(".read-page_attachments-items").hide();
	});

	$(".read-page_attachments-itmes_close").on("click", () => {
		$(this).parent().parent().hide();
	});
});

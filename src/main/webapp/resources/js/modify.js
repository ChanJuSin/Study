$(function() {
	$(".modify-page_attachments > input").on("click", () => {
		if($(".modify-page_attachments-items").css("display") === "none") {
			let height = $(".modify-page_attachments-items").outerHeight();
			$(".form-group:nth-child(4)").css("margin-top", height + 15);
			return $(".modify-page_attachments-items").show();
		}
		
		$(".form-group:nth-child(4)").css("margin-top", 0);
		return $(".modify-page_attachments-items").hide();
	});

	$(".modify-page_attachments-itmes_close").on("click", () => {
		$(this).parent().parent().hide();
	});
	$(".modify-page_attachments-item").on("click", ".deleteFile", function(){
		let formData = new FormData();
		
		formData.append("idx", $("#idx").val());
		formData.append("writer",$("#writer").val());
		formData.append("filePath",$(this).prev().val());
		
		let parent = $(this).parent();
		let height = parent.outerHeight();
		
		$.ajax({
			type: "post",
			url: "/board/modifyDeleteFile",
			data: formData,
			processData: false,
			contentType: false,
			success: function(result) {
				parent.remove();
				
				let target_attachments_length  = $(".modify-page_attachments .target_attachments_length");
				let attachments_length = target_attachments_length.val() - result;
				
				target_attachments_length.val(attachments_length);
				$(".attachments_length").val("첨부파일(" + attachments_length + ")");
				
				if (attachments_length === 0) {
					$(".modify-page_attachments-items").hide();
					$(".form-group:nth-child(4)").css("margin-top", 0);
					return $(".modify-page_attachments").remove();
				}
				
				let currentHeight = $(".form-group:nth-child(4)").css("margin-top").split("px")[0];
				$(".form-group:nth-child(4)").css("margin-top", currentHeight - height);
			}
		});
	});
});

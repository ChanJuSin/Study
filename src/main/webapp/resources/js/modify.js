function addImageTag(imageFilePaths) {
	$(".content img").each(function(index, item) {
		$(item).after("<a href=" + imageFilePaths[index] + ">삭제</a>");
	});
}

$(function() {
	// 첨부파일 목록 show, hide 
	$(".modify-page_attachments > input").on("click", () => {
		if($(".modify-page_attachments-items").css("display") === "none") {
			// 첨부파일 목록 높이
			let height = $(".modify-page_attachments-items").outerHeight();
			// 첨부파일 목록의 높이만큼 그다음 div 요소에 높이 적용
			$(".form-group:nth-child(4)").css("margin-top", height + 15);
			return $(".modify-page_attachments-items").show();
		}
		
		// 첨부파일 목록이 사라지면서 그 다음 div 요소의 높이도 0으로 적용
		$(".form-group:nth-child(4)").css("margin-top", 0);
		return $(".modify-page_attachments-items").hide();
	});

	// 첨부파일 목록 hide 
	$(".modify-page_attachments-itmes_close").on("click", function(){
		$(this).parent().parent().hide();
		// 첨부파일 목록이 사라지면서 그 다음 div 요소의 높이도 0으로 적용
		$(".form-group:nth-child(4)").css("margin-top", 0);
	});
	
	// 첨부파일 삭제
	$(".modify-page_attachments-item").on("click", ".deleteFile", function(){
		let formData = new FormData();
		
		formData.append("idx", $("#idx").val());
		formData.append("writer",$("#writer").val());
		formData.append("filePath",$(this).prev().val());
		
		let parent = $(this).parent();
		// 첨부파일 목록의 요소 높이값 33
		let height = parent.outerHeight();
		
		$.ajax({
			type: "post",
			url: "/board/modifyDeleteFile",
			data: formData,
			processData: false,
			contentType: false,
			success: function(result) {
				parent.remove();
				
				// 게시글 수정시의 첨부파일 갯수
				let target_attachments_length  = $(".modify-page_attachments .target_attachments_length");
				// 게시글 수정시 첨부파일을 삭제하고 원래 갯수에서 -1
				let attachments_length = target_attachments_length.val() - result;
				
				// 첨부파일 갯수 연산에 필요한 target 값 수정
				target_attachments_length.val(attachments_length);
				// 첨부파일 갯수 수정
				$(".attachments_length").val("첨부파일(" + attachments_length + ")");
				
				// 첨부파일이 하나도 없을경우 첨부파일 목록 없앰
				if (attachments_length === 0) {
					$(".form-group:nth-child(4)").css("margin-top", 0);
					return $(".modify-page_attachments").remove();
				}
				
				// margin-top 속성의 px 값을 꺼낸후 px 문자로 나눔 ex)) 94px -> 94, px 이 경우에는 [0]이 94, [1]이 px
				let currentHeight = $(".form-group:nth-child(4)").css("margin-top").split("px")[0];
				// 첨부파일 삭제후 첨부된 div요소를 삭제하면서 다음 div에 현재높이에서 삭제된 요소의 높이만큼 빼줌
				$(".form-group:nth-child(4)").css("margin-top", currentHeight - height);
			}
		});
	});
});

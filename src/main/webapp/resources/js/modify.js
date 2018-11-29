let deleteImageForm = new FormData();
deleteImageForm.append("distinction", "board");
let imageTagIndex = 0;
let file = [];

// 돔 이벤트 막기
function eventPrevent(event) {
	event.stopPropagation();
	event.preventDefault();
}

//이미지 파일 영역 리셋
function dropZoneReset(dropZone) {
	dropZone.css('background-color','#FFFFFF');
    $(".content h3").remove();
}

//이미지 파일 업로드
function imageFileUpload(file) {
	let formData = new FormData();
	
	file.forEach(function(value, index) {
		formData.append("files", file[index]);
	});
	
	$.ajax({
		method: "post",
		url: "/board/image/uploadBoardImage",
		data: formData,
		processData: false,
		contentType: false,
		success: function(result) {
			$(".content .delete-board_image").remove();
			
			$(".content img").each(function(index, value) {
				$(this).attr("src", "/board/image/displayBoardImage?imagePath=" + result[index]);
				let thumbnail_image_path = "" + result[index].substring(0, 12) + "s_" + result[index].substring(12);
				$(".board-original_image-path_list").append(`<input type="hidden" name="board_original_image_paths" value=${result[index]} />`);
				$(".board-thumbnail_image-path_list").append(`<input type="hidden" name="board_thumbnail_image_paths" value=${thumbnail_image_path} />`);
			}).promise().done(() => {
				$("#content").val($(".content").html());
				$("#writeForm").get(0).submit();
			});
		}
	});
}

// 이미지 파일 삭제
function imageFileDelete() {
	$.ajax({
		type: "post",
		url: "/board/image/deleteBoardFile",
		data: deleteImageForm,
		dataType: "text",
		processData: false,
		contentType: false
	});
}

// 이미지 드랍 이벤트
function fileDropDown() {
	 let dropZone = $(".content");
   
     dropZone.on('dragover',function(event){
    	 eventPrevent(event);
         
         dropZone.css('background-color','#E3F2FC');
     });
     
     
     dropZone.on("dragenter", function(event) {
    	 eventPrevent(event);
    	
    	let fileDropMsg = "<h3>이미지 파일을 해당 영역으로 Drag & Drop 해주세요.</h3>";
    	dropZone.append(fileDropMsg);
    	
    	$(".content").css("position", "relative");
    	$(".content h3").css({
    		"padding": "0",
    		"margin": "0",
    		"position": "absolute",
    		"left": "276px",
    		"top": "237px"
    	});
     });
     
     dropZone.on('dragleave',function(event){
    	 eventPrevent(event);
    	 dropZoneReset(dropZone);
     });
     
     dropZone.on("drop", function(event) {
         eventPrevent(event);
         dropZoneReset(dropZone);

         let files = event.originalEvent.dataTransfer.files;
         
         if (!checkFileType(files[0].name)) {
             alert("이미지 파일만 등록가능합니다.");
             return;
         } else if (files.length > 1) {
             alert("이미지 파일을 하나씩 등록해주세요.");
             return;
         }
         
         imageTagIndex += 1;
         file.push(files[0]);

         let reader = new FileReader();
         reader.readAsDataURL(files[0]);

         reader.onload = () => {
             let imageTag = `<img src=${reader.result} name=image class=addImage${imageTagIndex} style="vertical-align:bottom;" />`;
             $(".content").append(imageTag);


             let html = 
             `
             	<input type="button" class="delete-board_image btn btn-default" value="삭제"/>
             	<br/><br/>
             `;
             $(".content .addImage" + imageTagIndex).after(html);

             let tempImage = new Image();
             tempImage.src = reader.result;

             tempImage.onload = function() {
                 if (tempImage.width > 1000) {
                     let canvas = document.createElement('canvas');
                     let canvasContext = canvas.getContext("2d");

                     let imageWidth = tempImage.width / 2;
                     let imageHeight = tempImage.height / 2;

                     canvas.width = imageWidth;
                     canvas.height = imageHeight;

                     canvasContext.drawImage(this, 0, 0, imageWidth, imageHeight);

                     let dataURI = canvas.toDataURL("image/jpeg");

                     document.querySelector(".content .addImage" + imageTagIndex).src = dataURI;
                 }
             };
         }
     });
}

// 이미지 삭제 태그 생성
function addDeleteImageTag(imageFilePaths) {
	$(".content img").each(function(index, item) {
		$(item).after("<a class='modifyDeleteImage btn btn-default' data-filePath=" + imageFilePaths[index] + ">삭제</a>");
		$(item).next().css("cursor", "pointer");
	});
}

let videoLinks = [];
// 영상 삭제 태그 생성
function addDeleteVideoTag(videoPaths) {
	videoLinks = videoPaths;
	
	$(".content .youtube-video_item").each(function(index, item) {
		 let deleteYouTuBeVideoTag = 
         `	
			 <div class="delete-youtube_list">
			     <span>작성시 올렸던 동영상 입니다. ${videoPaths[index]}</span>
			     <a href="#" class="delete-youtube_video">삭제</a>
			 </div>
        `;
		 
		 $(".delete-youtube_videos-lists").append(deleteYouTuBeVideoTag);
	});
}

// 첨부파일 목록 리스트 컨트롤
function attachmentsListDisplay() {
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
}

function modifyAttachmentsDelete() {
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
}

function modifyFormSubmit() {
	// 게시글 수정
	$("#modifyForm").on("submit", function(event) {
		event.preventDefault();
		
		if(document.getElementById("content").value.length > 50000) {
			console.log(document.getElementById("content").value.length);
			alert("작성 글내용의 데이터가 너무 큽니다. 다시 입력해주세요.");
			return;
		}
		
		$(".content a").remove();
		
		if (file.length > 0) {
			if (deleteImageForm.getAll("board_idx").length >= 1) {
				imageFileDelete();
			}
			imageFileUpload(file);
		} else {
			$("#content").val($(".content").html());
			$("#writeForm").get(0).submit();
		}
	});
}


$(function() {
	fileDropDown();
	attachmentsListDisplay();
	modifyAttachmentsDelete();
	modifyFormSubmit();
	
	deleteImageForm.append("board_idx", $("#idx").val());
	deleteImageForm.append("user_idx", $("#user_idx").val());
	
	// 파일 폼 추가
	$("#addFileForm").on("click", function() {
		$("#addFileForm").before("<input type='file' name='files' style='margin-bottom:15px' />");
	});
	
	// 파일 폼 삭제
	$("#delFileForm").on("click", function() {
		let prevForm = $(this).prev();
		
		if (prevForm.prevAll().length === 1) {
			alert("파일 폼이 2개 이상일 때만 삭제 가능합니다.");
			return;
		}
		
		prevForm.prev().remove();
	});
	
	// 기존에 업로드했던 이미지 삭제 
	$(".content").on("click", "a", function() {
		let currentTag = $(this);
		let prevTag = currentTag.prev();
		
		let filePath = currentTag.attr("data-filePath");

		deleteImageForm.append("originalImagePaths", filePath);
		
		let thumbnailFilePath = filePath.substring(0, 12) + "s_" + filePath.substring(12);
		deleteImageForm.append("thumbnailImagePaths", thumbnailFilePath);
		
		currentTag.remove();
		prevTag.remove();
	});
	
	// 수정 페이지에서 업로드한 이미지 삭제 
	$(".content").on("click", ".delete-board_image", function() {
		let currentTag = $(this);
		let currentImageTagIndex = $(this).prev().index((".content img[name=image]"));
		
		console.log(currentImageTagIndex);
		
		file.splice(currentImageTagIndex, 1);
		
		console.log(file);
		
		currentTag.prev().remove();
		currentTag.remove();
	});
	
	// 유튜브 영상 추가
	$(".add-youtube_video").on("click", () => {
        let videoLink = prompt("유튜브 영상 링크를 입력해주세요.");
        
        if (videoLink === null || videoLink === "")
        	return;
        
        videoLink = videoLink.trim();
        if (videoLinks.length > 0) {
        	for (let i = 0; i < videoLinks.length; i++) {
            	if (videoLink === videoLinks[i].trim()) {
            		return alert("이미 등록된 영상입니다.");
            	} 
            }
        }
        
        $(".board-youtube_video-modify-delete_list > input[value = '"+ videoLink +"']").remove();
        
        let deleteYouTuBeVideoTag = 
        `	
        	<div class="delete-youtube_list">
        		<span>동영상 첨부가 완료되었습니다. ${videoLink}</span>
		        <a href="#" class="delete-youtube_video">삭제</a>
	        </div>
         `;
        
        let youTuBeVideoTag = 
        `
        	<div class="youtube-video_item">
	        	<iframe id="${videoLink}" type="text/html" height="500" src=${"http://www.youtube.com/embed/" + videoLink.substring(17)} frameborder="0" style="display: inline-block; width: 100%;"></iframe>
	        	<br><br>
        	</div>
	    `;
        
        $(".content").append(youTuBeVideoTag);
        
        let youTuBeVideoPathTag = 
		`
			<input type="hidden" name="video_paths" value="${videoLink}">
		`;

		$(".board-youtube_viedo-path_list").append(youTuBeVideoPathTag);
        
      
        $(".delete-youtube_videos-lists").append(deleteYouTuBeVideoTag);
        
        videoLinks.push(videoLink);
    });
	
	// 유튜브 영상 삭제
	$(".delete-youtube_videos-lists").on("click", ".delete-youtube_list .delete-youtube_video", function() {
		let deleteIndex = $(this).index(".delete-youtube_list .delete-youtube_video");
		
		$(this).parent().remove();
		
		$(".youtube-video_item").eq(deleteIndex).remove();
		
		$(".board-youtube_viedo-path_list > input[value ='"+ videoLinks[deleteIndex] +"']").remove();
		$(".board-youtube_video-modify-delete_list").append(`<input type="hidden" name="delete_video_paths" value="${videoLinks[deleteIndex]}" />`);
		
		videoLinks.splice(deleteIndex, 1);
	});
});
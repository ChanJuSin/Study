// 돔 이벤트 막기
function eventPrevent(event) {
	event.stopPropagation();
	event.preventDefault();
}

// 이미지 파일 영역 리셋
function dropZoneReset(dropZone) {
	dropZone.css('background-color','#FFFFFF');
    $(".content h3").remove();
}

// 이미지 파일 업로드
function imageFileUpload(file) {
	let formData = new FormData();
	formData.append("file", file);
	
	$.ajax({
		type: "post",
		url: "/upload/uploadFile?distinction=board",
		data: formData,
		processData: false,
		contentType: false,
		success: function(filePath) {
			filePath = filePath.substring(0, 12) + filePath.substring(14);
			
			let target = $(".content");
			let imageTag = "<img src=/upload/displayFile?filePath=" + filePath + "&distinction=board style=vertical-align:bottom>";
			let deleteImageTag = "<a data-filePath='"+ filePath +"'>삭제</a>";
			
			$(".content").on("mouseover", "a", function() {
				$(this).css("cursor", "default");
			});
			
			target.append(imageTag);
			target.append(deleteImageTag);
			target.append("<br><br>");
		}
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
    	 let file = files[0];
    	 
    	 if (!checkFileType(file.name)) {
             alert("이미지 파일만 등록가능합니다.");
             return;
         } else if (files.length > 1) {
             alert("이미지 파일을 하나씩 등록해주세요.");
             return;
         }
    	 
    	 imageFileUpload(file);
     });
}

// 게시글 작성
function writeFormSubmit() {
	$("#writeForm").on("submit", function(event) {
		eventPrevent(event);
		
		if ($("#title").val() === "") {
			alert("제목을 입력하세요.");
			return $("#title").focus();
		} else if ($(".content").html() === "") {
			alert("내용을 입력하세요.");
			return $(".content").focus();
		}
		
		let str = "";
		let imageSelect = $(".content img");
		
		imageSelect.each(function() {
			src = $(this).attr("src").split("&")[0].substring(29);
			str += "<input type=hidden name='images' value='"+src+"'>";
		});
		
		console.log($(".content").html());
		
		$(".content").append(str);
		$(".content a").remove();
		$("#content").val($(".content").html());
		
		if(document.getElementById("content").value.length > 50000) {
			console.log(document.getElementById("content").value.length);
			alert("작성 글내용의 데이터가 너무 큽니다. 다시 입력해주세요.");
			return;
		}
		
		$("#writeForm").get(0).submit();
	});
}

$(function() {
	fileDropDown();
	writeFormSubmit();
	
	// 이미지 삭제 
	$(".content").on("click", "a", function() {
		let currentTag = $(this);
		let prevTag = currentTag.prev();
		
		let filePath = currentTag.attr("data-filePath");

		let formData = new FormData();
		formData.append("filePath", filePath); 
		formData.append("distinction", "board");
		
		$.ajax({
			type: "post",
			url: "/upload/deleteFile",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			success: function() {
				currentTag.remove();
				prevTag.remove();
			}
		}); 
	});
	
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
});
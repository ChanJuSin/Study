<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공부용 게시판 글작성 페이지</title>
<link rel="stylesheet" href="/resources/css/reset.css">
<link rel="stylesheet" href="/resources/css/write.css">
<%@ include file="../include/import.jsp" %>
<script src="/resources/js/fileCheck.js"></script>
<script src="/resources/js/write.js"></script>
</head>
<body>

<div class="container-fluid">
	<%@ include file="../include/header.jsp" %>
	
	<div class="container">
		<div class="writeForm">
			<form id="writeForm" action="/board/write" method="post" enctype="multipart/form-data" id="writeForm">
				<div class="form-group">
			        <label for="writer">작성자</label>
			        <input type="text" class="form-control" id="writer" value="${loginInfo.name }" name="writer" readonly />
			    </div>
			
				<div class="form-group">
			        <label for="title">제목</label>
			        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요." />
			    </div>
			    
				<div class="form-group">
					<label for="content">내용 (이미지 파일은 Drag & Drop 형식으로 등록가능합니다.)</label>
					<div class="content" contentEditable="true"></div>
					<!-- <textarea style="display:none;" rows="20" cols="163" id="content" name="content"></textarea>  -->
					<input type="hidden" name="content" id="content" />
				</div> 
				
				<input type="file" name="files" style="margin-bottom: 15px;" />
				
				<input type="submit" value="글작성" class="btn btn-sm" />
			</form>	
		</div>
	</div>
</div>

<script>
function eventPrevent(event) {
	event.stopPropagation();
	event.preventDefault();
}

function dropZoneReset(dropZone) {
	dropZone.css('background-color','#FFFFFF');
    $(".content h3").remove();
}

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
			let imageTag = "<img src=/upload/displayFile?filePath=" + filePath + "&distinction=board style=vertical-align:bottom>";
			$(".content").append(imageTag);
			$(".content").append("<br><br>");
		}
	});
}

function fileDropDown() {
	 let dropZone = $(".content");
   
     dropZone.on('dragover',function(event){
    	 eventPrevent(event);
         
         dropZone.css('background-color','#E3F2FC');
     });
     
     
     dropZone.on("dragenter", function(event) {
    	 eventPrevent(event);
    	
    	let fileDropMsg = `<h3>이미지 파일을 해당 영역으로 Drag & Drop 해주세요.</h3>`
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

function writeFormSubmit() {
	$("#writeForm").on("submit", function(event) {
		eventPrevent(event);
		
		let str = "";
		let imageSelect = $(".content img");
		
		imageSelect.each(function() {
			let src = $(this).attr("src").split("&")[0].substring(29);
			str += "<input type=hidden name='images' value='"+src+"'>";
		});
		
		console.log($(".content").html());
		
		$(".content").append(str);
		$("#content").val($(".content").html());
		
		$("#writeForm").get(0).submit();
	});
}

$(function() {
	fileDropDown();
	writeFormSubmit();
});
</script>

</body>
</html>
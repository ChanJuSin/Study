let formData = new FormData();
let file = [];
let imageTagIndex = 0;

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
	
	file.forEach(function(value, index) {
		console.log(index);
		console.log(value);
	});
	/*
	console.log(formData.getAll("files"));
	
	$.ajax({
		method: "post",
		url: "/board/image/uploadBoardImage",
		data: formData,
		processData: false,
		contentType: false,
		success: function(result) {
			$(".content img").each(function(index, value) {
				$(this).attr("src", "/board/image/displayBoardImage?imagePath=" + result[index]);
			});
		}
	});*/
}

//이미지 드랍 이벤트
function fileDropDown() {
    let dropZone = $(".content");

    dropZone.on('dragover', function(event) {
        eventPrevent(event);

        dropZone.css('background-color', '#E3F2FC');
    });


    dropZone.on("dragenter", function(event) {
        eventPrevent(event);
    });

    dropZone.on('dragleave', function(event) {
        eventPrevent(event);
        dropZoneReset(dropZone);
    });

    dropZone.on("drop", function(event) {
        eventPrevent(event);
        dropZoneReset(dropZone);
        
        imageTagIndex += 1;

        let files = event.originalEvent.dataTransfer.files;
        file.push(files[0]);

        if (!checkFileType(files[0].name)) {
            alert("이미지 파일만 등록가능합니다.");
            return;
        } else if (files.length > 1) {
            alert("이미지 파일을 하나씩 등록해주세요.");
            return;
        }

        let reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onload = () => {
            let imageTag = `<img src=${reader.result} name=image class=image${imageTagIndex} style="vertical-align:bottom;" />`;
            $(".content").append(imageTag);


            let html = 
            `
            	<input type="button" class="delete-board_image" value="삭제"/>
            	<br/><br/>
            `;
            $(".content .image" + imageTagIndex).after(html);
            $(".content .data-file" + imageTagIndex).val(files[0]);
            console.dir(files[0]);

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

                    document.querySelector(".content .image" + imageTagIndex).src = dataURI;
                }
            };
        }
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
		
		imageFileUpload(file);
		
		/*let str = "";
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
		
		$("#writeForm").get(0).submit();*/
	});
}

$(function() {
	fileDropDown();
	writeFormSubmit();
	
	// 이미지 삭제 
	$(".content").on("click", ".delete-board_image", function() {
		let currentTag = $(this);
		let currentImageTagIndex = $(this).prev().index((".content img[name=image]"));
		
		console.log(currentImageTagIndex);
		
		file.splice(currentImageTagIndex, 1);
		
		console.log(file);
		
		currentTag.prev().remove();
		currentTag.remove();
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
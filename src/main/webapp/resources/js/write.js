let formData = new FormData();
let file = [];
let imageTagIndex = 0;
let videoLinks = [];

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
				let thumbnail_image_path = result[index].substring(0, 12) + "s_" + result[index].substring(12);
				$(".board-original_image-path_list").append(`<input type="hidden" name="board_original_image_paths" value=${result[index]} />`);
				$(".board-thumbnail_image-path_list").append(`<input type="hidden" name="board_thumbnail_image_paths" value=${thumbnail_image_path} />`);
			}).promise().done(() => {
				$("#content").val($(".content").html());
				$("#writeForm").get(0).submit();
			});
		}
	});
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
        
        console.log(file);

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
		
		if(document.getElementById("content").value.length > 50000) {
			console.log(document.getElementById("content").value.length);
			alert("작성 글내용의 데이터가 너무 큽니다. 다시 입력해주세요.");
			return;
		}
		
		if (file.length > 0) {
			imageFileUpload(file);
		} else {
			$("#content").val($(".content").html());
			$("#writeForm").get(0).submit();
		}
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
	
	// 유튜브 영상 추가
	$(".add-youtube_video").on("click", () => {
        let videoLink = prompt("유튜브 영상 링크를 입력해주세요.");
        
        if (videoLink === null || videoLink === "")
        	return;
        
        let deleteYouTuBeVideoTag = 
            `	
	          <div class="delete-youtube_list">
		          <span>동영상 첨부가 완료되었습니다. ${videoLink}</span>
		          <a href="#" class="delete-youtube_video">삭제</a>
	          </div>
            `;
        
        /*
         * 	https://youtu.be/0ixNzvJNoio
         *  유튜브 주소가 위와 같은 경우 0ixNzvJNoio 부분만 가져옴
         */
        videoLink = videoLink.substring(17);
        if (videoLinks.length > 0) {
        	for (let i = 0; i < videoLinks.length; i++) {
            	if (videoLink === videoLinks[i]) {
            		return alert("이미 등록된 영상입니다.");
            	} 
            }
        }
        
        let youTuBeVideoTag = 
        `
        	<div class="youtube-video_item">
	        	<iframe id="${videoLink}" type="text/html" height="500" src=${"http://www.youtube.com/embed/" + videoLink} frameborder="0" style="display: inline-block; width: 100%;"></iframe>
	        	<br><br>
        	</div>
	    `;
        
        $(".content").append(youTuBeVideoTag);
        
        let youTuBeVideoPathTag = 
		`
			<input type="hidden" name="video_paths" value="www.youtube.com/embed/${videoLink}">
		`;

		$(".board-youtube_viedo-path_list").append(youTuBeVideoPathTag);
        
      
        $(".delete-youtube_videos-lists").append(deleteYouTuBeVideoTag);
        
        videoLinks.push(videoLink);
    });
	
	// 유튜브 영상 삭제
	$(".delete-youtube_videos-lists").on("click", ".delete-youtube_list .delete-youtube_video", function() {
		let deleteIndex = $(this).index(".delete-youtube_list .delete-youtube_video");
		
		$(this).parent().remove();
		
		let target = "#" + videoLinks[deleteIndex];
		$(target).remove();
		
		videoLinks.splice(deleteIndex, 1);
	});
});
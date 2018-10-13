let emailCheck = false;

function checkForm() {
	if ($("#email").val() === "") {
		alert("이메일을 입력하세요.");
		$("#email").focus();
		return false;
	} else if ($("#pw").val() === "") {
		alert("비밀번호를 입력하세요.");
		$("#pw").focus();
		return false;
	} else if ($("#name").val() === "") {
		alert("이름을 입력하세요.");
		$("#name").focus();
		return false;
	} else if (!emailCheck) {
		alert("이메일 중복체크를 진행하세요.");
		return false;
	} 
		
	return true;
}

function getFileType(fileName) {
	let parts = fileName.split(".");
	return parts[parts.length -1];
}

function checkFileType(fileName) {
	let fileType = getFileType(fileName);
	
	switch (fileType.toUpperCase()) {
		case "JPG":
		case "GIF":
		case "BMP":
		case "PNG":
			
		return true;
	}
	
	return false;
}

$(function() {
	let imageUploadWhether = false;
	
	$(".profile_image_drop").on("dragenter dragover", (event) => {
        event.preventDefault();
    });	
	
	// 프로필 이미지 업로드
	$(".profile_image_drop").on("drop", (event) => {
		event.preventDefault();
		
		let files = event.originalEvent.dataTransfer.files;
		let file = files[0];
		
		if (!checkFileType(file.name)) {
			alert("이미지 파일만 등록가능합니다.");
			return;
		} else if (imageUploadWhether) {
			alert("프로필 이미지가 이미 등록되었습니다. 삭제 후 재등록 하세요.");
			return;
		} else if (files.length > 1) {
			alert("이미지 파일은 여러개 등록이 불가능합니다.");
			return;
		}
		
		let formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			type: "POST",
			url: "/upload/uploadFile",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false
		})
			.then((filePath) => {
				imageUploadWhether = true;
				
				console.log(filePath);
				
				let html = `
					<input type="button" class="deletePrfImg" value="삭제"/> 
					<input type="hidden" id="filePath" name="thumbnail_file_path" value=${filePath} />
					<input type="hidden" id="distinction" value="profile" />
					`;
				
				$(".profileImg").attr("src", "/upload/displayFile?filePath=" + filePath + "&distinction=profile");
				$(".profile_image_sumnail").append(html);
			})
			.fail((err) => {
				console.error(err);
			});
	});
	
	// 프로필 이미지 삭제
	$(".profile_image_sumnail").on("click", ".deletePrfImg", () => {
		$.ajax({
			type: "POST",
			url: "/upload/deleteFile",
			data: { 
				filePath: $("#filePath").val(),
				distinction: $("#distinction").val()
			},
			dataType: "text"
		})
			.then(() => {
				imageUploadWhether = false;
				
				alert("프로필 이미지가 삭제되었습니다.");
				
				$(".profileImg").attr("src", "/upload/displayFile?distinction=profile");
				
				$(".deletePrfImg").remove();
				$("#filePath").remove();
				$("#distinction").remove();
			})
			.fail((err) => {
				console.error(err);
			});
	});
	
	// 이메일 중복 체크
	$(".email_check").on("click", () => {
		$.ajax({
			type: "POST",
			url: "/user/emailCheck",
			data: {
				email: $("#email").val()
			},
			dataType: "text"
		})
			.then((message) => {
				alert(message);
				
				if (message === "이메일이 중복됩니다.") {
					emailCheck = false;
					return;
				}
				
				emailCheck = true;
			})
			.fail((err) => {
				console.error(err);
			});
	});
});
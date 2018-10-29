let emailCheck = false;

// 폼체크
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
			method: "post",
			url: "/user/profile/uploadProfileImage",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false
		})
			.then((thumbnail_image_path) => {
				imageUploadWhether = true;
				
				console.log(thumbnail_image_path);
				
				let original_image_path = thumbnail_image_path.substring(0, 12) + thumbnail_image_path.substring(14);
				
				let html = `
					<input type="button" class="delete-profile_image" value="삭제"/>
					<input type="hidden" name="original_image_path" value=${original_image_path} /> 
					<input type="hidden" name="thumbnail_image_path" value=${thumbnail_image_path} />
					`;
				
				$(".profile_image").attr("src", "/user/profile/displayProfileImage?imagePath=" + thumbnail_image_path);
				$(".profile_image_sumnail").append(html);
			})
			.fail((err) => {
				console.error(err);
			});
	});
	
	// 프로필 이미지 삭제
	$(".profile_image_sumnail").on("click", ".delete-profile_image", () => {
		$.ajax({
			method: "post",
			url: "/user/profile/deleteProfileImage",
			contentType: "application/json",
			data: JSON.stringify({ 
				original_image_path: $("input[name=original_image_path]").val(),
				thumbnail_image_path: $("input[name=thumbnail_image_path]").val()
			}),
			dataType: "text"
		})
			.then((result) => {
				imageUploadWhether = false;
				
				alert(result);
				
				$(".profile_image").attr("src", "/user/profile/displayProfileImage");
				
				$(".delete-profile_image").remove();
				$("input[name=original_image_path]").remove();
				$("input[name=thumbnail_image_path]").remove();
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
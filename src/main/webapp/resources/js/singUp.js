let emailCheck = false;
let formData = new FormData();


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
		
		let reader = new FileReader();
		// 파일의 URL 정보 읽음
		reader.readAsDataURL(files[0]);
		
		reader.onload = () => {
			// formData에 file값이 없을경우
			if (!formData.has("file")) {
				formData.append("file", file);
				console.dir(formData.get("file"));
				$(".profile_image").attr("src", reader.result);
				
				let html = `<input type="button" class="delete-profile_image" value="삭제"/>`;
				$(".profile_image_sumnail").append(html);
				imageUploadWhether = true;
			}
		};
	});
	
	// 프로필 이미지 삭제
	$(".profile_image_sumnail").on("click", ".delete-profile_image", () => {
		$(".profile_image_sumnail .delete-profile_image").remove();
		$(".profile_image_sumnail .profile_image").attr("src", "/user/profile/displayProfileImage");
		formData.delete("file");
		imageUploadWhether = false;
	});
	
	// 이메일 중복 체크
	$(".email_check").on("click", () => {
		$.ajax({
			method: "post",
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
					emailDuplicate = true;
					return;
				}
				
				emailCheck = true;
			})
			.fail((err) => {
				console.error(err);
			});
	});
	
	// 회원가입
	$(".singUpForm .singUpForm_Submit").on("submit", (event) => {
		event.preventDefault();
		
		// 입력 폼 체크
		if (!checkForm()) {
			return;
		}
		
		if (formData.has("file")) {
			// 회원가입시 프로필 등록
			$.ajax({
				method: "post",
				url: "/user/profile/uploadProfileImage",
				data: formData,
				dataType: "text",
				processData: false,
				contentType: false
			})
				.then((thumbnail_image_path) => {
					let original_image_path = thumbnail_image_path.substring(0, 12) + thumbnail_image_path.substring(14);
					
					let html = `
						<input type="hidden" name="original_image_path" value=${original_image_path} /> 
						<input type="hidden" name="thumbnail_image_path" value=${thumbnail_image_path} />
						`;
					
					$(".profile_image").attr("src", "/user/profile/displayProfileImage?imagePath=" + thumbnail_image_path);
					$(".profile_image_sumnail").append(html);
					
					console.log("이미지 업로드 완료");
					
					// 회원가입
					return $(".singUpForm .singUpForm_Submit").get(0).submit();
				})
				.fail((err) => {
					console.log(err);
					return alert("회원가입에 실패했습니다. 다시 시도해주세요.");
				});
		} else {
			// 회원가입
			return $(".singUpForm .singUpForm_Submit").get(0).submit();
		}
	});
});
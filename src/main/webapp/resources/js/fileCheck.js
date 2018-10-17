// 파일 타입 리턴
function getFileType(fileName) {
	let parts = fileName.split(".");
	return parts[parts.length -1];
}

// 파일 타입 체크
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
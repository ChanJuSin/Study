$(function() {
    function boardImageUpload(el, file) {
        let formData = new FormData();
        formData.append("file", file);

        $.ajax({
            type: "POST",
            url: "/upload/uploadFile?distinction=board",
            data: formData,
            dataType: "text",
            processData: false,
            contentType: false,
            success: function(filePath) {
                filePath = filePath.substring(0, 12) + filePath.substring(14);
                $("#summernote").summernote("insertImage", "/upload/displayFile?filePath=" + filePath + "&distinction=board", function($image) {
                	$image.css('width', $image.width() / 1.8);
                    $image.attr('name', 'images');
                });
            },
            error: (err) => {
                console.error(err);
            }
        });
    }

    $('#summernote').summernote({
        height: 500,
        lang: 'ko-KR',
        callbacks: {
            onImageUpload: function(files) {
                let file = files[0];

                if (!checkFileType(file.name)) {
                    alert("이미지 파일만 등록가능합니다.");
                    return;
                } else if (files.length > 1) {
                    alert("이미지 파일을 하나씩 등록해주세요.");
                    return;
                }

                boardImageUpload(this, file);
            },

            onMediaDelete: function(target) {
                let filePath = target[0].src;

                filePath = filePath.split("&")[0].substring(50);

                let formData = new FormData();
                formData.append("filePath", filePath);
                formData.append("distinction", "board");

                $.ajax({
                    type: "POST",
                    url: "/upload/deleteFile",
                    data: formData,
                    dataType: "text",
                    processData: false,
                    contentType: false,
                    success: function() {

                    },
                    error: (err) => {
                        console.error(err);
                    }
                });
            }
        }
    });
});
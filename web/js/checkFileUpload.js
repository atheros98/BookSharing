/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("cover1").addEventListener("change", checkFile);
document.getElementById("cover2").addEventListener("change", checkFile);
document.getElementById("cover3").addEventListener("change", checkFile);
document.getElementById("cover4").addEventListener("change", checkFile);
document.getElementById("cover5").addEventListener("change", checkFile);

function checkFile(event) {
    var file = document.getElementById(event.target.id);
    if (file.files[0].size === 0) { // file tải lên có dung lượng = 0
        console.log("file tải lên có dung lượng = 0");
    } else if (file.files[0].size > 1024 * 1024 * 4) { // file tải lên có dung lượng lớn hơn 4MB
        console.log("file tải lên có dung lượng lớn hơn 4MB");
    } else if (getTypeImage(file.value) === null) { // file tải lên không phải dạng ảnh (có đuôi jpg, png)
        console.log("file tải lên không phải dạng ảnh (có đuôi jpg, png)");
    }
}

function getTypeImage(input) {
    if (input.toLowerCase().endsWith(".jpg")) {
        return ".jpg";
    } else if (input.toLowerCase().endsWith(".png")) {
        return ".png";
    } else if (input.toLowerCase().endsWith(".jpeg")) {
        return ".png";
    } else {
        return null;
    }
}
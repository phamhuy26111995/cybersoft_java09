function loadCourse() {
    axios({
        url: 'http://localhost:8080/api/admin/course',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrCourse = resp.data;
            // Tạo danh sách thẻ option
            let strOption = "";
            for (let courseDto of arrCourse) {
                strOption += `<option value="${courseDto.id}">${courseDto.title}</option>`;
            }
            // Truy cập tới thẻ select có id là 'roleId'
            let courseIdTag = document.getElementById('courseId');
            // Thay thế các thẻ option cũ bằng danh sách thẻ option mới
            courseIdTag.innerHTML = strOption;
        })
        .catch(function (err) {
            console.log(err.response);
            

        })
}
loadCourse();

function addTarget() {

    let flag = true;
    let titleInput = document.getElementById('title').value;
    let courseInput = document.getElementById('courseId').value;


    if (flag === true) {
        // TẠO ĐỐI TƯỢNG USER
        let targetDto = {
            "title" : titleInput,
            "courseId": courseInput
            
        }

        // GỌI API THÊM MỚI
        axios({
            url: 'http://localhost:8080/api/admin/target',
            method: 'POST',
            data: targetDto,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
        })
            .then(function (resp) {
                console.log('Thành công! ' + resp.data);
                swal("Good job!", "Thêm Mới Thành Công!", "success");
                

            })
            .catch(function (err) {

                swal("Sorry", "Thêm mới Thất Bại!", "error");
            })
    }
}

function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}
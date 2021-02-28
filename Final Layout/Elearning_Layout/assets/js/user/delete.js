function deleteUser(id){


    axios({
        url: `http://localhost:8080/api/admin/user/${id}`,
        method: 'DELETE',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //Lấy ra mảng role
            console.log("Xóa thành công");
       
        })
        .catch(function (err) {
            console.log(err.response);
        })
}


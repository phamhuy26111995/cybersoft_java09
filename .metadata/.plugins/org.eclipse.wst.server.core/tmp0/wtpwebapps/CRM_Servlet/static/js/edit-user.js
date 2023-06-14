/**
 * 
 */




function validateForm() {
  var fullname = document.forms["edit-user-form"]["fullname"].value;
  var email = document.forms["edit-user-form"]["email"].value;
  var passwd = document.forms["edit-user-form"]["passwd"].value;
  var avatar = document.forms["edit-user-form"]["avatar"].value;
  
  if (fullname == "") {
    swal("Sorry", "Fullname is not empty", "error");
    return false;
    }else if(email == ""){
    	swal("Sorry", "Email is not empty", "error");
    	return false;
    }else if(passwd == ""){
    	swal("Sorry", "Password is not empty", "error");
    	return false;
    }else if(avatar == ""){
    	swal("Sorry", "Avatar is not empty", "error");
    	return false;
    }else{
    	console.log("Không có gì xảy ra");
    }
    
  
}

function submitForm(){
	document.getElementById("add-new-user").submit();
    //document.forms["upload-avatar"].submit();
    //document.getElementById("upload-avatar").submit();

}




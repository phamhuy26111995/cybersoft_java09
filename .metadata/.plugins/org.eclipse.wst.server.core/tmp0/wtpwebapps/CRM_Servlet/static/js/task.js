/**
 * 
 */

/**
 * 
 */

function validateForm() {
  var name = document.forms["task-form"]["taskName"].value;
  var startDate = document.forms["task-form"]["startDate"].value;
  var endDate = document.forms["task-form"]["endDate"].value;
  var regex = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/i;
  
  if (name == "") {
    swal("Sorry", "Fullname is not empty", "error");
    return false;
  }

	if(startDate == ""){
		swal("Sorry", "Start Date is not empty", "error");
    	return false;
	}else if(!regex.test(startDate)){
		swal("Sorry", "Start Date do not match with format", "error");
    	return false;
	}
	
	
   if(endDate == ""){
		swal("Sorry", "End Date is not empty", "error");
    	return false;
	}else if(!regex.test(endDate)){
		swal("Sorry", "End Date do not match with format", "error");
    	return false;
	}
	
	

}
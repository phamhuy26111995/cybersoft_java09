function showSweetAlern(){
	let successAlert = document.getElementById("toastrSuccess").value;
	let errorAlert = document.getElementById("toastrError").value;
	
	if(successAlert === "success"){
		swal("Good job!", "Successful", "success");
	}
	else if(errorAlert === "error"){
		swal("Sorry", "Fail", "error");
	}
	else{
		console.log("nothing happen");
	}

	
	
	
}
showSweetAlern()
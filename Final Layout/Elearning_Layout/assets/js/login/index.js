function sendRedirect(){
    if(localStorage.getItem("USER_TOKEN")===null){
        location.replace("/login.html");
    }
}
sendRedirect();
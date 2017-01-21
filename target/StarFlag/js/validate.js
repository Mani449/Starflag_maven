function validateLoginForm() {
    var username = document.forms["Login"]["username"].value;
	var password = document.forms["Login"]["password"].value;
    if (username == "") {
        alert("username must be filled");
        return false;
    }
	if (password == "") {
        alert("password must be filled");
        return false;
    }
}


function validateRegisterForm() {
    var name = document.forms["Register"]["name"].value;
	var regex_name=new RegExp("/^[a-zA-Z]+$/");
	var password = document.forms["Register"]["password"].value;
	var email = document.forms["Register"]["email"].value;
	var cnfpassword = document.forms["Register"]["confirmpassword"].value;
	var openBalance = document.forms["Register"]["openBalance"].value;
	var numbers = /^[0-9]+$/; 
	var address = document.forms["Register"]["address"].value;
	
    if (name == "") {
        alert("username must be filled");
        return false;
    }
	if (email == "") {
        alert("email must be filled");
        return false;
    }
	if (password == "" || cnfpassword == "") {
        alert("password fields must be filled");
        return false;
    }
	if (password != cnfpassword) {
        alert("password must match");
        return false;
    }
	if (address == "") {
        alert("address must be filled");
        return false;
    }
	if (openBalance == "") {
        alert("openBalance must be filled");
        return false;
    }
	if(!numbers.exec(openBalance)){
		alert("openBalance must numbers");
        return false;
	}
}
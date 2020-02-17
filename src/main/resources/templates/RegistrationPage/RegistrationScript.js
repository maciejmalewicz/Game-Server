var xhr = new XMLHttpRequest();
		
xhr.onreadystatechange = function() {
  	if (xhr.readyState == 4) {
    	var resp = xhr.responseText;
    	printCorrectMessage(parseInt(resp));
  	}
}

var passwordCondition = "<br></br>It should contain at least 8 characters, including:<br></br>"
                        + "- at least 1 upper case letter<br></br>"
                        + "- at least 1 lower case letter<br></br>"
                        + "- at least 1 digit";
	

function tryToRegister() {
	var login = document.getElementById("loginField").value;
	var password = document.getElementById("passwordField").value;
	var passwordRepeat = document.getElementById("passwordRepeatField").value;
	var email = document.getElementById("mailField").value;
	console.log(login);
	console.log(password);
	console.log(passwordRepeat);
	console.log(email);
	var loginAttempt = {login: login, password: password, email: email};
	
	if (password != passwordRepeat){
		console.log("Passwords don't match!");
		printMessage("Passwords don't match!");
	} else {
		printMessage("");
		xhr.open("POST", "http://127.0.0.1:8080//api/activationLink", true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(loginAttempt));
	}
}

function printCorrectMessage(signal){
	switch(signal) {
  	case 0:
    	printMessage("Well done! Your activation link has been sent to your e-mail!");
    	break;
  	case 1:
    	printMessage("Login is already occupied!");
    	break;
    case 2:
    	printMessage("There already exists an account with selected e-mail");
    	break;
    case 3:
    	printMessage("Password is too short!\n" + passwordCondition);
    	break;
    case 4:
    	printMessage("Password doesn't contain any lower case letter!\n" + passwordCondition);
    	break;
    case 5:
    	printMessage("Password doesn't contain any upper case letter!\n" + passwordCondition);
    	break;
    case 6:
    	printMessage("Password doesn't contain any digit!\n" + passwordCondition);
    	break;				
  	default:
    	printMessage("Something went wrong!");
}
}

function printMessage(string){
	document.getElementById("errorMessageField").innerHTML = string;
}
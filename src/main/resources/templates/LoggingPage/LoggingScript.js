var xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function() {
  		if (xhr.readyState == 4) {
    		var resp = xhr.responseText;
    		console.log(resp);
  		}
  		
		}
	function tryToLogIn(){
		var login = document.getElementById("loginVal").value;
		console.log(login);
		var password = document.getElementById("passwordVal").value;
		console.log(password);
		xhr.open("GET", "http://127.0.0.1:8080//api/user/" + login + "|" + password, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send();
	};
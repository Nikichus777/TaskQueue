/**
 * 
 */
/**
 * Created by Nikichus on 18.01.2016.
 */
var req;
var button1 = 0;
var button2 = 0;
var password = 0;
function loadXMLDoc(url, input) {
    // branch for native XMLHttpRequest object
	//alert("loadXMLDoc started");
    if (window.XMLHttpRequest) {
    	req = new XMLHttpRequest();
    	//alert("before proccess");
    	req.onreadystatechange = processReqChange;
    	//alert("after proccess");
    	//req.setRequestHeader('userlogin',input);
        //alert("after set header");
    	req.open("GET", url, true);
         
        req.send(null);
      // alert("loadXmlDoc done");
        // branch for IE/Windows ActiveX version
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = processReqChange;
           // req.setRequestHeader('userlogin',input);
            req.open("POST", url, true);
            req.send();
        }
    }
}

function checkLogin(input, response)
{
	
    if (response != '') {
    		//alert("Response !=0");
            // Response mode
            message1 = document.getElementById('nameCheckFailed');
            
            if (response == '1') {
                message1.className = 'error';
                message1.textContent = 'Логин уже занят';
                disableButton();
                button1 = 0;
            } 
            else if (response == '2'){
            	message1.className = 'error';
                message1.textContent = 'В логине можно использовать только буквы цифры и тире';
                disableButton();
                button1 = 0;
            }
            else {
                message1.className = 'ok';
                message1.textContent = 'логин можно применять';
                button1 = 1;
                checkAndEnableButton();
            }
        }
        else if (input.length > 3) {
            // Input mode
        	//alert("checkLogin launched");
        	
            url = 'validation?userlogin='+ input;
            //alert("url = " + url);
            loadXMLDoc(url, input);
        }
        else {
        	message1.className = 'error';
            message1.textContent = 'Длина логина не меньше 4х символов';
        	button1 = 0;
        	disableButton();
        }
    
}

function checkEmail(input, response2)
{
	
    if (response2 != '') {
    		//alert("Response !=0");
            // Response mode
            message2 = document.getElementById('emailCheckFailed');
            
            if (response2 == '1') {
                message2.className = 'error';
                message2.textContent = 'Email уже занят';
                disableButton();
                button2 = 0;
            }
            else if (response2 == '2'){
            	message2.className = 'error';
                message2.textContent = 'Неверный email';
                disableButton();
                button2 = 0;
            }
            else {
                message2.className = 'ok';
                message2.textContent = 'Email можно применять';
                button2 = 1;
                checkAndEnableButton();
            }
        }
        else if (input.length > 3) {
            // Input mode
        	//alert("checkLogin launched");
        	
            url = 'validation?useremail='+ input;
            //alert("url = " + url);
            loadXMLDoc(url, input);
        }
        else {
        	message2.className = 'error';
            message2.textContent = 'Длина email не меньше 4х символов';
        	button2 = 0;
        	disableButton();
        }
}

function processReqChange()
{
	//alert("processReqChange started");
    // only if req shows "complete"
    if (req.readyState == 4) {
        // only if "OK"
        if (req.status == 200) {
            // ...processing statements go here...
            response = req.getResponseHeader('userlogin');
        	response2 = req.getResponseHeader('useremail');
            //response = req.responseText;
          //  alert("response status ok: " + response);
            if (response != null) checkLogin('',response);
            if (response2 != null) checkEmail('',response2);
           // eval(method + '(\'\', result)');
        } else {
            alert("Возникла проблема получения данных:\n" + req.statusText);
            alert("response: " + req.getResponseHeader('userlogin'));
            alert("response text:" + req.responseText);
        }
    }
}

function passwordChange(){
	password1 = document.getElementById('userpassword1').value;
	password2 = document.getElementById('userpassword2').value;
	message3 = document.getElementById('passCheckFailed');
	message4 = document.getElementById('pass2CheckFailed');
	
	if (password1 == '' || password1.length < 6){
		message3.className = 'error';
		message3.textContent = 'Пароль меньше 6 символов';
		password = 0;
	}else
	if (password1 == password2){
		message3.className = 'ok';
		message3.textContent = 'Длина пароля нормальная';
		message4.className = 'ok';
		message4.textContent = 'Пароли совпадают';
		password = 1;
		checkAndEnableButton();
	}
	else {
		message3.className = 'ok';
		message3.textContent = 'Длина пароля нормальная';
		message4.className = 'error';
		message4.textContent = 'Пароли не совпадают';
		password = 0;
	}
}

function checkAndEnableButton(){
	if (button1 ==1 && button2 ==1 && password ==1){
		button = document.getElementById('sub');
		button.removeAttribute('disabled');
				
	}
} 

function disableButton(){	
	button = document.getElementById('sub');
	button.setAttribute('disabled','disabled');
}

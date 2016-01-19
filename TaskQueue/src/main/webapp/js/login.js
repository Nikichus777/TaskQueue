/**
 * 
 */
/**
 * Created by Nikichus on 18.01.2016.
 */
var req;
function loadXMLDoc(url) {
  
    if (window.XMLHttpRequest) {
    	req = new XMLHttpRequest();    
    	req.onreadystatechange = processReqChange;
       	req.open("GET", url, true);
        req.send(null);
        
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = processReqChange;
            req.open("GET", url, true);
            req.send();
        }
    }
}

function checkLogin(response)
{

		
    if (response != '') {
    	
            message1 = document.getElementById('loginCheckFailed');
            
            if (response == '1') {
                message1.className = 'error';
                message1.textContent = 'Неверный логин или пароль';
                
            } 
            else if(response = '2') {
            	document.location.href = 'index.jsp';
            	}
    }
    else {
    	login =  document.getElementById('login').value;
    	password = document.getElementById('userpass').value;
    	url = 'login?login=' + login + '&password=' + password;
    	loadXMLDoc(url);
    }
            
            
    
}

function processReqChange()
{
	if (req.readyState == 4) {
     // alert('req.status ' + req.status);
        if (req.status == 200) {
        	
            response = req.getResponseHeader('login');
           // alert('response login ' + response);
           // alert('test ' + req.getResponseHeader('test'));
        	if (response != null) checkLogin(response);
           
        } else {
            alert("Возникла проблема получения данных:\n" + req.statusText + "response: " + req.getResponseHeader('userlogin') + "response text:" + req.responseText);
        }
    }
}

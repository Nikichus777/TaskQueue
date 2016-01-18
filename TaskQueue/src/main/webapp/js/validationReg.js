/**
 * 
 */
/**
 * Created by Nikichus on 18.01.2016.
 */
var req;
var message;
function loadXMLDoc(url, input) {
    // branch for native XMLHttpRequest object
    if (window.XMLHttpRequest) {

        req = new XMLHttpRequest();
        req.onreadystatechange = processReqChange;
        req.setRequestHeader('userlogin',input);
        req.open("GET", url, true);
        req.send(null);

        // branch for IE/Windows ActiveX version
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = processReqChange;
            req.setRequestHeader('userlogin',input);
            req.open("GET", url, true);
            req.send();
        }
    }
}

function checkLogin(input, response)
{
    if (response != '') {
            // Response mode
            message = document.getElementById('nameCheckFailed');
            
            if (response == '1') {
                message.className = 'error';
            } else {
                message.className = 'hidden';
                message.textContent = 'имя можно применять';
            }
        }
        else if (input.length > 3) {
            // Input mode
            url = '/validation';
            loadXMLDoc(url, input);
        }
}

function processReqChange()
{
    // only if req shows "complete"
    if (req.readyState == 4) {
        // only if "OK"
        if (req.status == 200) {
            // ...processing statements go here...
            response = req.getResponseHeader('userlogin');
            checkLogin('',response);
           // eval(method + '(\'\', result)');
        } else {
            alert("Возникла проблема получения данных:\n" + req.statusText);
        }
    }
}
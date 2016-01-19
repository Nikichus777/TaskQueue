<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kz.iskst.model.User, java.util.List, kz.iskst.model.UserRequest" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" http-equiv="Cache-Control" content="no-cache" /> 
<title>Очередь задач сисадмина КДП</title>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body >
<div id="top_bar_black"> <div id="logo_container"> <div id="logo_image"> </div>  <div id="nav_block"> 
		<div class="nav_button"><a href ="index.jsp" style="color: white"> Главная</a></div>
		<div class="nav_button"><a href ="news.jsp" style="color: white"> Новости</a></div>
		
		<% if (session.getAttribute("login") == null) { %>
		<div class="nav_button"><a href ="login.html" style="color: white"> Войти </a></div>
		<% } else {%>
		<div class="nav_button"><a href ="logout" style="color: white"> Выйти </a></div>
		<%} %>
		
		<div class="nav_button"><a href="registration.html" style="color: white"> Регистрация</a></div>
	  </div>
</div> </div>
        <div id="content_container">
       	  <div id="header"> <div class="header_content_mainline"> Очередь задач системного администратора </div> <div id="header_content_tagline">Здесь Вы можете добавить 
			свою задачу для системного администратора</div>
          
          
</div>	      
                 <div id="header_lower">  <div id="header_content_boxline">Здравствуйте <% 
                 String login = (String) session.getAttribute("login");
                 if (login != null) out.write(login); out.flush(); %> <div id="header_content_boxcontent">
				  
					 <%@ include file="/addRequest.jsp"%>
				<!-- TODO: Сделать данную страницу базой для всех остальных -->
				
 </div></div> 
          </div>    
        </div>
        
        
        
<div id="bottom_bar_black"> <div id="main_container">
	<div id="header_lower">  <div id="header_content_lowerline">Контакты
	    <div id="header_content_lowerboxcontent">Хакимжановой, 7<br />
	      Казахстан<br />
	      Костанай<br />
        городской номер: (7142) 50-77-70<br />
        kdp_it@mail.ru<br />
        www.dorproject.kz<br />
        <br /> 
        </div>
	</div> 
          </div>
          
          <div id="header_lower">  <div id="header_content_lowerline">Еще один блок
          <div id="header_content_lowerboxcontent">
            Какая то информация
            </div>
	</div> 
          </div>


</div> 
</div>
 <div id="copywriteblock"> Designed by <a href="http://www.is-kst.kz">www.is-kst.kz </a></div>

</body>
</html>

<%@ page import="kz.iskst.model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Очередь задач сисадмина КДП</title>
<link href="css/stylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="top_bar_black"> <div id="logo_container"> <div id="logo_image"> </div>  <div id="nav_block"> 
		<div class="nav_button"> Главная</div>
		<div class="nav_button"> Новости</div>
		<div class="nav_button"> Войти</div>
		
	  </div>
</div> </div>
        <div id="content_container">
       	  <div id="header"> <div class="header_content_mainline"> Очередь задач системного администратора </div> <div id="header_content_tagline">Здесь Вы можете добавить 
			свою задачу для системного администратора</div>
          
          
</div>
	      
                 <div id="header_lower">  <div id="header_content_boxline">About Us <div id="header_content_boxcontent">
				 <p style="color:RED">Чтобы добавлять свои задачи в очередь необходимо войти</p>
				 <table border = "1" align = "center" width = "790">
		<br><br><br><caption>Таблица задач сисадмина</caption>
		<tr><th>Номер</th><th>ФИО</th><th>Проблема</th><th>Срочность</th><th>Время</th></tr>
		<% for (int x=10; x>0; x--) { %>
		<tr><td>1</td><td>USER</td><td>Autocad</td><td>priority</td><td>07.01.2016:18:10</td></tr>
		<%} %>
	</table>

 </div></div> 
          </div>    
        </div>
        
        
        
<div id="bottom_bar_black"> <div id="main_container">
	<div id="header_lower">  <div id="header_content_lowerline">Контакты
	    <div id="header_content_lowerboxcontent">Хакимжановой, 7<br />
	      Казахстан<br />
	      Костанай<br />
        городской номер: (7142) 50-77-70<BR />
        kdp_it@mail.ru<br />
        www.dorproject.kz<br />
        <BR /> 
        </div>
	</div> 
          </div>
          
          <div id="header_lower">  <div id="header_content_lowerline">Clients
            <div id="header_content_lowerboxcontent">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est </div>
	</div> 
          </div>


</div> 
</div>
 <div id="copywriteblock"> Designed by <a href="http://www.is-kst.kz">www.is-kst.kz </a></div>

</body>
</html>
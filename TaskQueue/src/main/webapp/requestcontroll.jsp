<%@ page import="kz.iskst.model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Trololosha</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<body background = "red">
<h2>Очередь задачъ</h2>
<table border =1>
<caption>Таблица задач сисадмина</caption>
<tr><th>Номер</th><th>ФИО</th><th>Проблема</th><th>Срочность</th><th>Время</th></tr>
<% for (int x=10; x>0; x--) { %>
<tr><td>1</td><td>USER</td><td>Autocad</td><td>priority</td><td>07.01.2016:18:10</td></tr>
<%} %>
</table>


<%=  request.getAttribute("JJJ") %>
<%=  ((User)request.getAttribute("us")).getLogin() %>
<% 
	int x = 10;
	while (x-- > 0){

%>
Mazafakabitch <br>
<%
	} %>

#{us.name} <br>
${us.email} <br>
${us.name} <br>
${JJJ}
</body>
</html>

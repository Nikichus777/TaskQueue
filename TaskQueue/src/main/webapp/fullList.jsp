<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kz.iskst.model.User, java.util.List, kz.iskst.model.UserRequest" isELIgnored="false" %>
<p style="color:RED">Чтобы добавлять свои задачи в очередь необходимо войти</p>
				 
		<table border = "1" align = "center" width = "790">				
		<caption>Таблица задач сисадмина</caption>
		<tr ><th>Номер</th><th>ФИО</th><th>Проблема</th><th>Срочность</th><th>Время</th></tr>
		<% List<UserRequest> list =  (List<UserRequest>) request.getAttribute("allRequestList");
			for (UserRequest req : list) { %>
		<tr><td><%=req.getUser().getId() %></td><td><%=req.getUser().getLogin() %></td><td><%= req.getProblemString() %></td><td><%=req.getPriority() %></td><td><%=req.getTime() %></td></tr>
		<%} %>
				
	</table>
	${error}
	<br>${transactionErrors} <br></br>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="kz.iskst.model.User, java.util.List, kz.iskst.model.UserRequest" isELIgnored="false" %>
<p style="color:RED">
<%if (session.getAttribute("login") == null) {%>Чтобы добавлять свои задачи в очередь необходимо войти</p>
	<% } else {%>Здравствуйте ${session.login} <%= session.getAttribute("login") %> <%} %>
	
		<table border = "1" align = "center" width = "790">				
		<caption>Таблица задач сисадмина</caption>
		<tr ><th>Номер</th><th>Логин</th><th>Проблема</th><th>Срочность</th><th>Время</th></tr>
		<% List<UserRequest> list =  (List<UserRequest>) request.getAttribute("allRequestList");
			for (UserRequest req : list) { %>
		<tr><td><%=req.getUser().getId() %></td><td><%=req.getUser().getLogin() %></td><td><%= req.getProblemString() %></td><td><%=req.getPriority() %></td><td><%=req.getTime() %></td></tr>
		<%} %>
				
	</table>
	<br />
	
<%
    if (session.getAttribute("login") != null) {
%>
<form action="addreq" method="post">
	<p style= "color :brown">Выберите свою проблему:
		<select name="userproblem">
			<option disabled>Выберите свою проблему:</option>
			<option selected value="NETWORK_NOT_WORK">Не работает сеть</option>
			<option value="PC_NOT_LOAD">Не загружается компьютер</option>
			<option value="INDORCAD">Индоркад</option>
			<option value="AUTOCAD">Автокад</option>
			<option value="OFFICE">Ворд и ексель</option>
			<option value="PRINTER">Принтер</option>
			<option value="OTHER">Другое</option>
		</select>
	</p>
	<p style= "color :brown"> Выберите срочность запроса:
		<select name="userpriority">
			<option disabled>Выберите срочность запроса:</option>
			<option value="1">самая низкая</option>
			<option selected value="3">низкая</option>
			<option value="5">средняя</option>
			<option value="6">выше средней</option>
			<option value="8">высокая</option>
			<option value="10">очень высокая</option>
		</select>
	</p>
	<p>
		<input type="submit" value="Отправить">
	</p>

</form>
<%
    }
%>

ADDREQUEST <br />
	${error}
	<br>${transactionErrors} <br></br>
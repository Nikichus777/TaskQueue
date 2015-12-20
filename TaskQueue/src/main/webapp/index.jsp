<%@page import="kz.iskst.model.User"%>

<html>
<body>
<h2>Hello World!</h2>
<% User us = new User("lohoho","alkash@maks.dd");
	System.out.println(us);
%>
${us.name}
</body>
</html>

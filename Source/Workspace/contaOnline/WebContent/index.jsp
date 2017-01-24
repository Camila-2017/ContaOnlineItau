<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conta Online</title>
</head>
<body>
<h1>Conta Online</h1>
<br>
<h2><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%></h2>
<form action="buscar" method="post">
	<fieldset>
	CPF: <input type="text" name="cpf" size="15" /> 
	<input type="submit" value="buscar"/>
</fieldset>
</form>
</body>
</html>
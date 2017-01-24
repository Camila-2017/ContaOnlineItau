<%@page import="com.contaonline.dto.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%Cliente c = (Cliente)request.getAttribute("cliente"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conta Online</title>
</head>
<body>
<h1><%=((Integer)request.getAttribute("tipo")).intValue() != 1 ? "Inclusão" : "Edição" %></h1>
<br>
<form action="<%=((Integer)request.getAttribute("tipo")).intValue() == 1 ? "./editar" : "./incluir"%>" method="post">

	<%if (((Integer)request.getAttribute("tipo")).intValue() == 1 ) {%>
	<fieldset>
		Agência: <strong><%=c.getCc().getAgencia() %></strong> <br>
		Conta: <strong><%=c.getCc().getConta() %></strong> <br>
	</fieldset>
	<br>
	<fieldset>
		CPF: <strong><%=c.getCpf() %> <br>
		<input type="hidden" name="cpf" value="<%=c.getCpf() %>"/>
	<%}  else {%>
	<fieldset>
		CPF: <input type="text" name="cpf" /> <br>
	<%}%>		
		Nome: <input type="text" name="nome" value="<%=c == null ? "" : c.getNome() %>"/> <br>
		Endereço: <textarea rows="4" cols="20" name="endereco"><%=c == null ? "" : c.getEndereco() %></textarea> <br>
		<input type="submit" value="Salvar" />
</form>
	<%if (((Integer)request.getAttribute("tipo")).intValue() == 1 ) {%>
<form action="./excluir" method="post">
		<input type="hidden" name="cpf" value="<%=c.getCpf() %>"/> <br>
		<input type="submit" value="Excluir" />
</form>
	<%}%>		
	</fieldset>
</form>
</body>
</html>
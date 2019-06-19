<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito mais -
	Casa do Código</title>
</head>

<body>

	<form:form action="${s:mvcUrl('PC#save').build()}" method="POST" commandName="produto" enctype="multipart/form-data">
		<div>
			<label>Titulo</label> <form:input path="titulo"/>
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao"/>
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Paginas</label> <form:input path="paginas"/>
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento"/>
		</div>
		<c:forEach items="${tipos}" var="tipo" varStatus="status">

			<div>
				<label>${tipo}</label>
				<form:input path="precos[${status.index}].valor"/>
				<form:hidden path="precos[${status.index}].tipoPreco" value="${tipo}"/>
			</div>

		</c:forEach>
		<div>
			<label>Sumário</label>
			<input type="file" name="sumario">
		</div>
		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>
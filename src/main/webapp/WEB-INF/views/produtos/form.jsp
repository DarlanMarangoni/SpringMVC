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

	<form:form action="${s:mvcUrl('PC#save').build()}" method="POST" commandName="produto">
		<div>
			<label>Titulo</label> <input type="text" name="titulo">
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Paginas</label> <input type="text" name="paginas">
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de lançamento</label>
			<input type="text" name="dataLancamento">
			<form:errors path="dataLancamento"/>
		</div>
		<c:forEach items="${tipos}" var="tipo" varStatus="status">

			<div>
				<label>${tipo}</label>
				<input type="text" name="precos[${status.index}].valor">
				<input type="hidden" name="precos[${status.index}].tipoPreco" value="${tipo}">
			</div>

		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>
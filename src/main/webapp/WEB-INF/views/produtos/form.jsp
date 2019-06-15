<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Livros de Java, Android, iPhone, PHP, Ruby e muito mais -
	Casa do C�digo</title>
</head>

<body>

	<form action="/casadocodigo/produtos" method="POST">
		<div>
			<label>Titulo</label> <input type="text" name="titulo">
		</div>
		<div>
			<label>Descri��o</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
		</div>
		<div>
			<label>Paginas</label> <input type="text" name="paginas">
		</div>
		<c:forEach items="${tipos}" var="tipo" varStatus="status">

			<div>
				<label>${tipo}</label>
				<input type="text" name="precos[${status.index}].valor">
				<input type="hidden" name="precos[${status.index}].tipoPreco" value="${tipo}">
			</div>

		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form>

</body>
</html>
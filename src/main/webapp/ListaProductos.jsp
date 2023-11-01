<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<table>
<tr>
<th>Codigo Articulo</th>
<th>Seccion</th>
<th>Nombre Articulo</th>
<th>Fecha</th>
<th>Precio</th>
<th>Importado</th>
<th>Pais de Origen</th>
</tr>

<c:forEach var="productos" items="${LISTAPRODUCTOS}">

<tr>

<td>${productos.cArt}</td>
<td>${productos.seccion}</td>
<td>${productos.nArt}</td>
<td>${productos.fecha}</td>
<td>${productos.precio}</td>
<td>${productos.importado}</td>
<td>${productos.pOrigen}</td>


</tr>

</c:forEach>
</table>

<div id="contenedorBoton">
<input type="button" value="Insertar Registro" onclick="window.location.href='insertar_producto.jsp'">
</div>
</body>
</html>
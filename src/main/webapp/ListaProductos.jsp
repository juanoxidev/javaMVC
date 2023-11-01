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
<th>Acción</th>
</tr>

<c:forEach var="producto" items="${LISTAPRODUCTOS}">

<!--  Link para cada producto con su campo clave  video 259 -->
<c:url var="btnActualizar" value="ControladorProductos">
<c:param name="instruccion" value="traerProducto"></c:param> 
<c:param name="CArticulo" value="${producto.cArt}"></c:param>
</c:url>

<c:url var="btnEliminar" value="ControladorProductos">
<c:param name="instruccion" value="eliminarProducto"></c:param> 
<c:param name="CArticulo" value="${producto.cArt}"></c:param>
</c:url>
<tr>

<td>${producto.cArt}</td>
<td>${producto.seccion}</td>
<td>${producto.nArt}</td>
<td>${producto.fecha}</td>
<td>${producto.precio}</td>
<td>${producto.importado}</td>
<td>${producto.pOrigen}</td>
<td><a href="${btnActualizar}">Actualizar</a>&nbsp; &nbsp; <a href="${btnEliminar}">Eliminar</a></td>


</tr>

</c:forEach>
</table>


<div id="contenedorBoton">
<input type="button" value="Insertar Registro" onclick="window.location.href='insertar_producto.jsp'">
</div>

</body>
</html>
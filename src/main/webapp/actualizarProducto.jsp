<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar Registro</title>
  <style>
    body {
      text-align: center;
    }

    form {
      width: 500px;
      margin: 0 auto;
    }

    .campo {
      display: flex;
      justify-content: space-between;
      margin-bottom: 15px;
    }

    label {
      width: 150px;
    }
  </style>
</head>
<body>

  <h1 style="text-align:center">Actualizar Registro</h1>

  <form name="form1" method="get"  action="ControladorProductos">
<!--   datos que le pasamos al controlador, la instruccion y el codigo articulo que el usuario no podra modificar. -->
<input type="hidden" name="instruccion" value="actualizarBBDD">
<input type="hidden" name="cArt" value="${PRODUCTO.cArt}">
<!--     <div class="campo">
      <label for="cArt">Código artículo</label>
      <input type="text" name="cArt" id="cArt">
    </div> -->

<!-- Llenamos los values de los campos con los atributos de la clase Productos -->
    <div class="campo">
      <label for="seccion">Sección</label>
      <input type="text" name="seccion" id="seccion" value="${PRODUCTO.seccion}">
    </div>

    <div class="campo">
      <label for="nArt">Nombre artículo</label>
      <input type="text" name="nArt" id="nArt" value="${PRODUCTO.nArt}">
    </div>

    <div class="campo">
      <label for="fecha">Fecha</label>
      <input type="text" name="fecha" id="fecha"  value="${PRODUCTO.fecha}">
    </div>

    <div class="campo">
      <label for="precio">Precio</label>
      <input type="text" name="precio" id="precio" value="${PRODUCTO.precio}">
    </div>

    <div class="campo">
      <label for="importado">Importado</label>
      <input type="text" name="importado" id="importado" value="${PRODUCTO.importado}">
    </div>

    <div class="campo">
      <label for="pOrigen">País de origen</label>
      <input type="text" name="pOrigen" id="pOrigen" value="${PRODUCTO.pOrigen}">
    </div>

    <input type="submit" value="Actualizar">
    <input type="reset" value="Restablecer">

  </form>

</body>
</html>
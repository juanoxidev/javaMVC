<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insertar Registros</title>
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

  <h1 style="text-align:center">Insertar Registros</h1>

  <form name="form1" method="get"  action="ControladorProductos">
  
<input type="hidden" name="instruccion" value="insertarBBDD">
    <div class="campo">
      <label for="cArt">Código artículo</label>
      <input type="text" name="cArt" id="cArt">
    </div>

    <div class="campo">
      <label for="seccion">Sección</label>
      <input type="text" name="seccion" id="seccion">
    </div>

    <div class="campo">
      <label for="nArt">Nombre artículo</label>
      <input type="text" name="nArt" id="nArt">
    </div>

    <div class="campo">
      <label for="fecha">Fecha</label>
      <input type="text" name="fecha" id="fecha" placeholder="dd/mm/aaaa">
    </div>

    <div class="campo">
      <label for="precio">Precio</label>
      <input type="text" name="precio" id="precio">
    </div>

    <div class="campo">
      <label for="importado">Importado</label>
      <input type="text" name="importado" id="importado">
    </div>

    <div class="campo">
      <label for="pOrigen">País de origen</label>
      <input type="text" name="pOrigen" id="pOrigen">
    </div>

    <input type="submit" value="Enviar">
    <input type="reset" value="Restablecer">

  </form>

</body>
</html>
</body>
</html>
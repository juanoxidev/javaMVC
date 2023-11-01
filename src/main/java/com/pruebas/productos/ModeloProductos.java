package com.pruebas.productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class ModeloProductos {
	// Va a tener que conectar con la base de datos 
	private DataSource origenDatos;

	public ModeloProductos(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}

	public List<Productos> getProductos() throws Exception {
		List<Productos> productos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultSet = null;
try {
		// Establecer la conexion
		miConexion = origenDatos.getConnection();
		// Crear la sentencia SQL y Statement
		String sentenciaSql = "SELECT * FROM productos";
		miStatement = miConexion.createStatement();
		// Ejecutar sentenciaSQL
		miResultSet = miStatement.executeQuery(sentenciaSql);
		// Recorrer el ResultSet
		while(miResultSet.next()) {
			String c_art = miResultSet.getString("CodigoArticulo");
			String seccion = miResultSet.getString("Seccion");
			String n_art = miResultSet.getString("NombreArticulo");
			double precio = miResultSet.getDouble("Precio");
			Date fecha = miResultSet.getDate("Fecha");
			String importado = miResultSet.getString("Importado");
			String p_orig = miResultSet.getString("PaisDeOrigen");
			productos.add(new Productos(c_art,seccion,n_art,precio,fecha,importado,p_orig));

		}
} 	finally {
	miStatement.close();
	miConexion.close();
}
		return productos;




	}

	public void agregarNuevoProducto(Productos nuevoProducto) throws SQLException {
		Connection miConexion = null;
		PreparedStatement miStatement = null;

		try {
			// obtener la conexion con la bd
			miConexion = origenDatos.getConnection();
			// instruccion sql que inserte el producto
			String sentenciaSql = "INSERT INTO productos (CodigoArticulo,Seccion,NombreArticulo,Precio,Fecha,Importado,PaisDeOrigen)"
					+ "VALUES (?,?,?,?,?,?,?)";
			miStatement = miConexion.prepareStatement(sentenciaSql);
			// establecer los parametros para el producto inser into debe ser capaz de
			// almacenar en su interior las propiedades del producto ej seccion, precio,
			// fecha
			miStatement.setString(1, nuevoProducto.getcArt());
			miStatement.setString(2, nuevoProducto.getSeccion());
			miStatement.setString(3, nuevoProducto.getnArt());
			miStatement.setDouble(4, nuevoProducto.getPrecio());
			// el date que tenemos es del paquete java.util y el que se usa con los statment
			// es el date del paquete java.sql
			// tenemos que convertir ese date de java.util a uno de java.sql, la diferencia
			// de uno a otro es que el primero tiene fecha y hora y el segundo solo tiene
			// fecha

			java.util.Date utilDate = nuevoProducto.getFecha();
			java.sql.Date fechaSQL = new java.sql.Date(utilDate.getTime());
			miStatement.setDate(5, fechaSQL);

			miStatement.setString(6, nuevoProducto.getImportado());
			miStatement.setString(7, nuevoProducto.getpOrigen());

			// ejecutar la instruccion SQL
			miStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	finally {
			miStatement.close();
			miConexion.close();
		}

	}

	public Productos getProducto(String codigoArticulo) throws Exception {
		Productos productoAModificar = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultSet = null;
		String cArticulo=codigoArticulo;
		
		
		try {
			// Establecer la conxecion con la BBDD
			miConexion=origenDatos.getConnection();
			// Crear sql que busque el producto
			String sqlBusquedad ="SELECT * FROM productos WHERE CodigoArticulo=?";
			// Crear la consulta preparada
			miStatement=miConexion.prepareStatement(sqlBusquedad);
			//establecer los parametros
			miStatement.setString(1, cArticulo);
			//ejecutar la consulta
			miStatement.executeQuery();
			//obtenr los datos de respuesta
			if(miResultSet.next()) {
				String c_art = miResultSet.getString("CodigoArticulo");
				String seccion = miResultSet.getString("Seccion");
				String n_art = miResultSet.getString("NombreArticulo");
				double precio = miResultSet.getDouble("Precio");
				Date fecha = miResultSet.getDate("Fecha");
				String importado = miResultSet.getString("Importado");
				String p_orig = miResultSet.getString("PaisDeOrigen");
				//Constructor sin campo clave "CODIGOARTICULO";
				productoAModificar = new Productos(c_art,seccion,n_art,precio,fecha,importado,p_orig);
			} else {
				// si no hay ningun registroe en el ResultSet lanzamos un error
				throw new Exception("No hemos encontrado el producto con codigo articulo : " + cArticulo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	finally {
			miStatement.close();
			miConexion.close();
		}
		
		return productoAModificar;
	}

	public void actualizarProducto(Productos productoActualizado) throws Exception{
		Connection miConexion = null;
		PreparedStatement miStatement = null;
	try {
		// Estalecer la conexion
	miConexion=origenDatos.getConnection();
		//Crear Sentencia SQL
		
		String UpdateSql="UPDATE productos SET Seccion=?,NombreArticulo=?,Precio=?,Fecha=?,Importado=?,PaisDeOrigen=? WHERE CodigoArticulo=?";
		// Crear la consulta preparada
		miStatement = miConexion.prepareStatement(UpdateSql);
		// Establecer los parametros
		miStatement.setString(1, productoActualizado.getSeccion());
		miStatement.setString(2, productoActualizado.getnArt());
		miStatement.setDouble(3, productoActualizado.getPrecio());
		// el date que tenemos es del paquete java.util y el que se usa con los statment
		// es el date del paquete java.sql
		// tenemos que convertir ese date de java.util a uno de java.sql, la diferencia
		// de uno a otro es que el primero tiene fecha y hora y el segundo solo tiene
		// fecha

		java.util.Date utilDate = productoActualizado.getFecha();
		java.sql.Date fechaSQL = new java.sql.Date(utilDate.getTime());
		miStatement.setDate(4, fechaSQL);

		miStatement.setString(5, productoActualizado.getImportado());
		miStatement.setString(6, productoActualizado.getpOrigen());
		miStatement.setString(7, productoActualizado.getcArt());
		//Ejecutar la instruccion SQL
		miStatement.execute();
	} finally {
		miStatement.close();
		miConexion.close();
	}
	}
	public void eliminarProducto(String codArticulo) throws SQLException {
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
			// Establecer la conexion con BBDD
			miConexion=origenDatos.getConnection();
			// Crear instruccon SQL de eliminacion
			String deleteSql="DELETE FROM productos WHERE CodigoArticulo=?";
			// Preparar la consulta
			miStatement = miConexion.prepareStatement(deleteSql);
			// establecer parametros de consulta
			miStatement.setString(1, codArticulo);
			// Ejecutar instruccion sql.
			miStatement.execute();
			//cerrar conexion
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			miStatement.close();
			miConexion.close();
		}
	
		
	}
}

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
		return productos;




	}

	public void agregarNuevoProducto(Productos nuevoProducto) {
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
		}

	}
}

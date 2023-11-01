package com.pruebas.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletPruebas
 */
@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1- Definir o establecer el DataSource
	@Resource(name="jdbc/Productos")
	private DataSource miPool;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2 -Crear el Objeto printWritter
		
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		//3- Crear conxion con BBDD
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultSet = null;
		
		try {
			
			miConexion=miPool.getConnection();
			String instrucionSQL = "SELECT * FROM PRODUCTOS";
			miStatement= miConexion.createStatement();
			miResultSet = miStatement.executeQuery(instrucionSQL);
			while (miResultSet.next()) {
				System.out.println(miResultSet.getString("NOMBREDELPRODUCTO"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.pruebas.productos;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModeloProductos modeloProductos;
	@Resource(name = "jdbc/productos")
	private DataSource miPool;



	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			super.init();
			modeloProductos = new ModeloProductos(this.miPool);

		}catch (Exception e) {
			throw new ServletException(e);
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// Leer parametro del formulario
		String elComando = request.getParameter("instruccion");
		// Si no se envia el parametro, listar productos
		if (elComando == null) {
			elComando = "listarProducto";
		}
		// Redirigir el flujo de ejecucion al metodo adecuado.
		switch (elComando) {
		case "listarProducto":
			obtenerProductos(request, response);
			break;
		case "insertarBBDD":
			agregarProducto(request, response);
			break;
		case "traerProducto":
			try {
				traerProducto(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "actualizarBBDD":
			try {
				actualizarProducto(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "eliminarProducto":
			try {
				eliminarProducto(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			obtenerProductos(request, response);
		}


	}



	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Capturar el codigo de articulo
		String codArticulo = request.getParameter("CArticulo");
		
		
		
		// Borrar producto de la BBDD 
		
		
		this.modeloProductos.eliminarProducto(codArticulo);
		
		
		// Volver a la lista de productos
		
		obtenerProductos(request,response);
		
		
	}



	private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Leer los datos que le vienen del formulario de actualizar  

		String codArticulo = request.getParameter("cArt");
		String seccion = request.getParameter("seccion");
		String nombreArticulo = request.getParameter("nArt");
		// SimpleDateFormat define el formato de la fecha String
		SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
		Date fecha= null;
		// Le pasamos a la fecha Date el parametro String y lo parseamos con el metodo .parse que retorna un Date por el SimpleDateFormat
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		double precio = Double.parseDouble(request.getParameter("precio"));
		String importado = request.getParameter("importado");
		String paisOrigen = request.getParameter("pOrigen");
		//Crear un objeto de tipo Producto.
		Productos productoActualizado = new Productos(codArticulo, seccion, nombreArticulo, precio, fecha, importado,
				paisOrigen);
		//Actualizar la BBDD con la info del objeto producto 
		modeloProductos.actualizarProducto(productoActualizado);
		//Volver el listado con la info actualizada
		obtenerProductos(request,response);
		
	}



	private void traerProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Leer el codigo articulo que viene del listado
		String codigoArticulo=request.getParameter("CArticulo");
		// comunicar con el modelo para que aquel haga una consulta a la BBDD sobre el codigo del articulo que le pasamos y que el modelo nos lo retorne
		Productos productoAModificar = this.modeloProductos.getProducto(codigoArticulo);
		// Colocar atributos al C articulo
		request.setAttribute("PRODUCTO", productoAModificar);
		//Enviar producto con toda su informacion al formulario de actualizar .jsp
		// REQUEST DISPATCHER
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProducto.jsp");
		dispatcher.forward(request, response);
		
	
	}



	private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
		// leer la informacion del producto que viene del formulario. getParameter siempre devuelve un tipo String

		String codArticulo = request.getParameter("cArt");
		String seccion = request.getParameter("seccion");
		String nombreArticulo = request.getParameter("nArt");
		// SimpleDateFormat define el formato de la fecha String
		SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
		Date fecha= null;
		// Le pasamos a la fecha Date el parametro String y lo parseamos con el metodo .parse que retorna un Date por el SimpleDateFormat
		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		double precio = Double.parseDouble(request.getParameter("precio"));
		String importado = request.getParameter("importado");
		String paisOrigen = request.getParameter("pOrigen");

		// crear un objeto de tipo Producto

		Productos nuevoProducto = new Productos(codArticulo, seccion, nombreArticulo, precio, fecha, importado,
				paisOrigen);


		// Enviar el objeto al modelo y despues insertar el Producto en la BBDD
		System.out.println(nuevoProducto);
		try {
			modeloProductos.agregarNuevoProducto(nuevoProducto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Volver al listado de Productos

		obtenerProductos(request,response);

	}



	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
		// Obtener la lista de productos desde el modelo
		try {
			List<Productos> productos = this.modeloProductos.getProductos();

			//agregar la lista de productos al request

			request.setAttribute("LISTAPRODUCTOS",productos);

			// enviar ese request a la pagina JSP 

			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaProductos.jsp");

			miDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

}

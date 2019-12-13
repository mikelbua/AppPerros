package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.model.ArrayPerroDAO;
import com.ipartek.formacion.model.pojo.Perro;

/**
 * Servlet implementation class PerrosController
 */
@WebServlet("/perros")
public class PerrosController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// Declaramos el Logger para poder recopilar informacion de errores.
	private final static Logger LOG = Logger.getLogger(PerrosController.class);
	private static ArrayPerroDAO dao = ArrayPerroDAO.getinstance();

	private String mensaje = "";

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		/*
		 * PRUEBAS DE MENSAJES EN LOG LOG.trace("TRACE"); LOG.warn("WRAN");
		 * LOG.error("EROR"); LOG.info("INFO");
		 */
		LOG.trace("Se ejecuta la primera vez que se llama a este servlet y nunca mas.");
		try {
			dao.create(new Perro("beltz",
					"https://comodibujar.club/wp-content/uploads/2019/03/dibujar-perro-kawaii-1.jpg"));
		
			dao.create(new Perro("toki", "https://cdn5.dibujos.net/dibujos/pintar/cachorrito-de-perro.png"));
			dao.create(new Perro("lagun",
					"https://www.perrosamigos.com/Uploads/perrosamigos.com/ImagenesGrandes/m-dibujos-de-perros.html-5.jpg"));
			dao.create(new Perro("txiki",
					"https://t1.uc.ltmcdn.com/images/7/6/6/img_como_dibujar_un_perro_adorable_38667_600.jpg"));
			dao.create(new Perro("koki",
					"http://practicarte.com/blog/wp-content/uploads/2019/04/Aprende-C%C3%B3mo-Dibujar-Un-Perro-Comiendo-Paso-A-Paso-6.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//init

	@Override
	public void destroy() {
		LOG.trace("Se ejecuta solo una vez, cuando se para el servidor de aplicaciones.");
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Se ejecuta antes del doGet o doPost.");

		// ...

		super.service(request, response); // Ejecuta doGet o doPost

		mensaje = "";

		LOG.trace("se ejecuta despues del doGet o doPost");

		// ....
		
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("perros", dao.getAll());
		request.getRequestDispatcher("perros.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// listar perros
		LOG.trace("doGET");

		int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
		boolean adoptar = (request.getParameter("adoptar") == null) ? false : true;
		boolean editar = (request.getParameter("editar") == null) ? false : true;

		LOG.debug("id= " + id + " adoptar=" + adoptar + " editar=" + editar);

		if (id > 0) {
			// borrar
			Perro perro = dao.getById(id);

			if (adoptar) {
				try {
					dao.delete(id);
					mensaje = "Perro adoptado, enhorabuena!!";
				} catch (Exception e) {

					mensaje ="o se a podido adoptar!";
				}
				
			}

			if (editar) {
				
				try {
					dao.update(id, perro);
				} catch (Exception e) {

					mensaje = "no se a podido modificar";
				}
				request.getRequestDispatcher("perros.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.trace("doPost");

		// Recibir datos del form
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String foto = request.getParameter("foto");
		//TODO validar parametros
		if (id > 0) {

			LOG.trace("Modificar el perro");
			Perro perro = new Perro();			
			perro.setNombre(nombre);
			perro.setFoto(foto);
			
			try {
				dao.update(id, perro);
				mensaje = "Perro modificado con exito";
			} catch (Exception e) {
				mensaje = "No se puede modificar";
			}			
			
			// TODO arreglar error al modificar
		} else {
			
			LOG.trace("Crear perro nuevo");
			
			//crear perro
			Perro p = new Perro();		
			p.setNombre(nombre);
			p.setFoto(foto);
			try {
				dao.create(p);
				mensaje = "Gracias por dar de alta un nuevo perro";
				
			} catch (Exception e) {
				mensaje = "No se puede crear";
			}	
			
		}
		// listar perros
		request.setAttribute("perros", dao.getAll());
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("perros.jsp").forward(request, response);

	}

}

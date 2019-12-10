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

import com.ipartek.formacion.model.pojo.Perro;

/**
 * Servlet implementation class PerrosController
 */
@WebServlet("/perros")
public class PerrosController2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// Declaramos el Logger para poder recopilar informacion de errores.
	private final static Logger LOG = Logger.getLogger(PerrosController.class);

	private ArrayList<Perro> perros = new ArrayList<Perro>();
	private int indice = 6;
	private String mensaje = "";

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		/*
		 * PRUEBAS DE MENSAJES EN LOG LOG.trace("TRACE"); LOG.warn("WRAN");
		 * LOG.error("EROR"); LOG.info("INFO");
		 */
		LOG.trace("Se ejecuta la primera vez que se llama a este servlet y nunca mas.");
		perros.add(new Perro(1,"beltz","https://comodibujar.club/wp-content/uploads/2019/03/dibujar-perro-kawaii-1.jpg"));
		perros.add(new Perro(2,"toki", "https://cdn5.dibujos.net/dibujos/pintar/cachorrito-de-perro.png"));
		perros.add(new Perro(3,"lagun", "https://www.perrosamigos.com/Uploads/perrosamigos.com/ImagenesGrandes/m-dibujos-de-perros.html-5.jpg"));
		perros.add(new Perro(4,"txiki","https://t1.uc.ltmcdn.com/images/7/6/6/img_como_dibujar_un_perro_adorable_38667_600.jpg"));
		perros.add(new Perro(5,"koki", "http://practicarte.com/blog/wp-content/uploads/2019/04/Aprende-C%C3%B3mo-Dibujar-Un-Perro-Comiendo-Paso-A-Paso-6.jpg"));
	}

	@Override
	public void destroy() {
		LOG.trace("Se ejecuta solo una vez, cuando se para el servidor de aplicaciones.");
		super.destroy();
		perros = null;
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
		request.setAttribute("perros", perros);
		request.getRequestDispatcher("perros-jstl.jsp").forward(request, response);
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

			// buscar perro en array
			Perro perro = null;
			for (Perro p : perros) {
				if (p.getId() == id) {
					perro = p;
					break;
				}
			}

			if (adoptar) {
				perros.remove(perro);
				mensaje = "Perro adoptado, enhorabuena!!";
			}

			if (editar) {
				request.setAttribute("perroEditar", perro);
			}

		} else {
			LOG.trace("solo listar perros");
			request.setAttribute("perros", perros);
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("perros-jstl.jsp").forward(request, response);

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

		if (id > 0) {

			LOG.trace("Modificar el perro");
			Perro perro = null;
			for (Perro p : perros) {
				if (p.getId() == id) {
					perro = p;
					break;
				}
			}
			perro.setNombre(nombre);
			perro.setFoto(foto);

			mensaje = "Los datos del perro han sido modificados.";

		} else {

			LOG.trace("Crear perro nuevo");

			// crear perro
			Perro p = new Perro();
			p.setNombre(nombre);
			p.setFoto(foto);
			p.setId(indice);
			indice++;

			mensaje = "Gracias por dar de alta un nuevo perro";

			// guardar en lista
			perros.add(p);

		}
		// listar perros
		request.setAttribute("perros", perros);
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("perros-jstl.jsp").forward(request, response);

	}

}

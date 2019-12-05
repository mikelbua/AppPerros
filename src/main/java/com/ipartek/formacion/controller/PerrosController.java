package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.model.pojo.Perro;

/**
 * Servlet implementation class PerrosController
 */
@WebServlet("/perros")
public class PerrosController extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
    private ArrayList<Perro> perros = new ArrayList<Perro>();
    
   
	

	public PerrosController() {
		super();
		perros.add( new Perro("bubba") );
		perros.add( new Perro("rataplan") );
		perros.add( new Perro("mosca") );
		perros.add( new Perro("txakur") );
		perros.add( new Perro("lagun") );
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//listar perros
		
		request.setAttribute("perros", perros);
		request.getRequestDispatcher("perros.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//recibir datos del form
		
		String nombre = request.getParameter("nombre");
		
				
		//crear perro
		Perro p = new Perro();
		p.setNombre(nombre);
		
		//guardar en lista
		perros.add(p);
		
		//listar perros
		request.setAttribute("perros", perros);
		request.getRequestDispatcher("perros.jsp").forward(request, response);
	
	}

}

package com.ipartek.formacion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.ipartek.formacion.model.pojo.Perro;

public class ArrayPerroDAO implements IDAO<Perro> {

	private ArrayList<Perro> registros;
	private static int indice = 1;

	private static ArrayPerroDAO INSTANCE = null;

	// prvate , capar para que nadie pueda hacer new de esta clase
	private ArrayPerroDAO() {
		super();
		registros = new ArrayList<Perro>();
	}

	public synchronized static ArrayPerroDAO getinstance() {

		if (INSTANCE == null) {
			INSTANCE = new ArrayPerroDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Perro> getAll() {

		return null;
	}

	@Override
	public Perro getById(int id) {
		Perro resul = null;

		for (Perro perro : registros) {
			if (id == perro.getId()) {
				resul = perro;
				break;
			}
		}

		return resul;
	}

	@Override
	public Perro delete(int id) throws Exception {
		Perro resul = null;

		for (Perro perro : registros) {
			if (id == perro.getId()) {
				resul = perro;
				registros.remove(perro);
				break;
			}
		}
		if (resul == null) {
			throw new Exception("no ha encontrado el perro qeu habia qeu borrar" + id);
		}

		return resul;
	}

	@Override
	public Perro update(int id, Perro pojo) throws Exception {
		Perro resul = null;
		for (int i = 0; i < registros.size(); i++) {
			if (id == registros.get(i).getId()) {
				registros.remove(i);
				registros.add(pojo);
				resul = pojo;
				break;
			}
		}
		if (resul == null) {
			throw new Exception("no ha encontrado el perro qeu habia qeu borrar" + id);
		}
		return resul;
	}

	@Override
	public Perro create(Perro pojo) throws Exception {
		Perro resul = null;
		if (pojo != null) {
			pojo.setId(++indice);
			registros.add(pojo);
		} else {
			throw new Exception("perro NULL sin datos");
		}
		// TODO comprobar campos del pojo.
		return resul;
	}

}

package com.exprivia.concessionario.API.services;

import java.util.List;

import com.exprivia.concessionario.API.model.Modello;

public interface ModelloServices {
	List<Modello> getAllModelli();

	public Modello getModelloById(Integer id);

	public Modello addModello(Modello modello);

	public Modello updateModello(Integer id, Modello modello);

}

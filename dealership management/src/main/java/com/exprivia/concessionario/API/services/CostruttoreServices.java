package com.exprivia.concessionario.API.services;

import java.util.List;
import com.exprivia.concessionario.API.model.Costruttore;

public interface CostruttoreServices {
	List<Costruttore> getAllCostruttori();

	public Costruttore getCostruttoreById(Integer id);

	public Costruttore addCostruttori(Costruttore costruttore);

	public Costruttore updateCostruttori(Integer id, Costruttore costruttore);

}

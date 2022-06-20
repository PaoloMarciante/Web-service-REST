package com.exprivia.concessionario.API.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exprivia.concessionario.API.model.Costruttore;
import com.exprivia.concessionario.API.repositories.CostruttoriRepository;

@Service
@Transactional
public class CostruttoreServicesImplementation implements CostruttoreServices {

	@Autowired
	private CostruttoriRepository costruttoriRepository;

	@Override
	public List<Costruttore> getAllCostruttori() {
		return costruttoriRepository.findAll();
	}

	@Override
	public Costruttore getCostruttoreById(Integer id) {

		Optional<Costruttore> costruttore = costruttoriRepository.findById(id);
		return costruttore.orElseThrow(() -> new EntityNotFoundException("Costruttore con id: " + id + " non trovato"));
	}

	@Override
	public Costruttore addCostruttori(Costruttore costruttore) {

		return costruttoriRepository.save(costruttore);
	}

	@Override
	public Costruttore updateCostruttori(Integer id, Costruttore costruttore) {
		Costruttore costruttoreToUpdate = this.getCostruttoreById(id);
		costruttoreToUpdate.setMarchio(costruttore.getMarchio());
		return costruttoriRepository.save(costruttoreToUpdate);
	}

}

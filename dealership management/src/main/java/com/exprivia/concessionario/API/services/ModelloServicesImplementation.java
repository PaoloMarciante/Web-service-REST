package com.exprivia.concessionario.API.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exprivia.concessionario.API.model.Modello;
import com.exprivia.concessionario.API.repositories.ModelliRepository;

@Service
@Transactional
public class ModelloServicesImplementation implements ModelloServices {

	@Autowired
	private ModelliRepository modelliRepository;

	@Override
	public List<Modello> getAllModelli() {

		return modelliRepository.findAll();
	}

	@Override
	public Modello getModelloById(Integer id) {
		Optional<Modello> modello = modelliRepository.findById(id);
		return modello.orElseThrow(() -> new EntityNotFoundException("Modello con id: " + id + " non trovato"));
	}

	@Override
	public Modello addModello(Modello modello) {

		return modelliRepository.save(modello);
	}

	@Override
	public Modello updateModello(Integer id, Modello modello) {
		Modello modelloToUpdate = this.getModelloById(id);
		modelloToUpdate.setDescrizione(modello.getDescrizione());
		modelloToUpdate.setAnnoProduzione(modello.getAnnoProduzione());
		return modelliRepository.save(modelloToUpdate);
	}

}

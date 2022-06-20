package com.exprivia.concessionario.API.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exprivia.concessionario.API.model.Configurazione;
import com.exprivia.concessionario.API.repositories.ConfigurazioniRepository;

@Service
@Transactional
public class ConfigurazioneServicesImplementation implements ConfigurazioneServices {

	@Autowired
	private ConfigurazioniRepository configurazioniRepository;

	@Override
	public List<Configurazione> getAllConfigurazioni() {
		return configurazioniRepository.findAll();
	}

	@Override
	public Configurazione getConfigurazioneById(Integer id) {

		Optional<Configurazione> configurazione = configurazioniRepository.findById(id);
		return configurazione.orElseThrow(() -> new EntityNotFoundException("Configurazione con id: " + id + " non trovata"));
	}

	@Override
	public Configurazione addConfigurazione(Configurazione configurazione) {
		return configurazioniRepository.save(configurazione);
	}

	@Override
	public Configurazione updateConfigurazione(Integer id, Configurazione configurazione) {
		Configurazione configurazioneToUpdate = this.getConfigurazioneById(id);

		configurazioneToUpdate.setNome(configurazione.getNome());
		return configurazioniRepository.save(configurazioneToUpdate);
	}

}

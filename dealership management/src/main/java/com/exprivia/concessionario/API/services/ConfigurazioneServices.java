package com.exprivia.concessionario.API.services;

import java.util.List;

import com.exprivia.concessionario.API.model.Configurazione;

public interface ConfigurazioneServices {
	List<Configurazione> getAllConfigurazioni();

	public Configurazione getConfigurazioneById(Integer id);

	public Configurazione addConfigurazione(Configurazione configurazione);

	public Configurazione updateConfigurazione(Integer id, Configurazione configurazione);

}

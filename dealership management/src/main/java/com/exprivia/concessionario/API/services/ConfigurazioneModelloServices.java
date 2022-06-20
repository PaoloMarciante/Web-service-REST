package com.exprivia.concessionario.API.services;

import java.util.List;


import com.exprivia.concessionario.API.dto.ConfigurazioneModelloDTO;
import com.exprivia.concessionario.API.model.ConfigurazioneModello;

public interface ConfigurazioneModelloServices {
	
	public ConfigurazioneModello getConfigurazioneModelloById(Integer id);

	public ConfigurazioneModello addConfigurazioneModello(ConfigurazioneModello configurazioneModello);

	public ConfigurazioneModello updateConfigurazioneModello(Integer id, ConfigurazioneModello configurazioneModello);

	List<ConfigurazioneModelloDTO> findAllCarInside();
	
	List<ConfigurazioneModelloDTO> findAllCar();

	ConfigurazioneModelloDTO addConfigurazioneModello(ConfigurazioneModelloDTO configurazioneModelloDTO);

	ConfigurazioneModelloDTO convertEntityToDto(ConfigurazioneModello configurazioneModello);

	ConfigurazioneModelloDTO findAllCarInsideById(Integer id);

//	ConfigurazioneModelloDTO findCarByTelaio(String n_telaio);

}
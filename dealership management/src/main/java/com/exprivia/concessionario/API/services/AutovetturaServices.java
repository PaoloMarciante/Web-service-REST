package com.exprivia.concessionario.API.services;

import java.util.List;

import com.exprivia.concessionario.API.dto.AutovetturaDTO;
import com.exprivia.concessionario.API.model.Autovettura;

public interface AutovetturaServices {
	List<Autovettura> getAllAutovetture();

	public Autovettura getAutovetturaById(String id);

	public Autovettura addAutovettura(Autovettura autovettura);

	public AutovetturaDTO updateAutovettura(String id, AutovetturaDTO autovettura) throws Exception;

	void deleteAutovettura(String id);
}

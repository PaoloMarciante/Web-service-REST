package com.exprivia.concessionario.API.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exprivia.concessionario.API.dto.ConfigurazioneModelloDTO;
import com.exprivia.concessionario.API.model.ConfigurazioneModello;
import com.exprivia.concessionario.API.repositories.ConfigurazioniModelliRepository;

@Service
@Transactional
public class ConfigurazioneModelloServicesImplementation implements ConfigurazioneModelloServices {

	@Autowired
	private ConfigurazioniModelliRepository configurazioniModelliRepository;

	@Autowired
	private ConfigurazioneServices configurazioneServices;

	@Autowired
	private ModelloServices modelloServices;
		

	@Override
	public ConfigurazioneModello getConfigurazioneModelloById(Integer id) {
		Optional<ConfigurazioneModello> configurazioneModello = configurazioniModelliRepository.findById(id);
		return configurazioneModello
				.orElseThrow(() -> new EntityNotFoundException("ConfigurazioneModello con id: " + id + " non trovata"));
	}
	
	@Override
	public ConfigurazioneModelloDTO findAllCarInsideById(Integer id) {
		
			return convertEntityToDto(getConfigurazioneModelloById(id));
	}
	
/*	@Override
	public ConfigurazioneModelloDTO findCarByTelaio(String n_telaio) {

		return convertEntityToDto(configurazioniModelliRepository.findCarByTelaio(n_telaio));
				

	}*/
	
	
	
	
	@Override
	public List<ConfigurazioneModelloDTO> findAllCarInside() {
		return configurazioniModelliRepository.findAllCarInside().stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());

	}
	
	@Override
	public List<ConfigurazioneModelloDTO> findAllCar() {
		return configurazioniModelliRepository.findAllCar().stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());

	}
	

	@Override
	public ConfigurazioneModello addConfigurazioneModello(ConfigurazioneModello configurazioneModello) {

		return configurazioniModelliRepository.save(configurazioneModello);
	}

	@Override
	public ConfigurazioneModello updateConfigurazioneModello(Integer id, ConfigurazioneModello configurazioneModello) {

		if (configurazioneModello.getAlimentazione().isEmpty() || configurazioneModello.getCilindrata() == null
				|| configurazioneModello.getTipoCambio().isEmpty() || configurazioneModello.getColore().isEmpty()
				|| configurazioneModello.getKW_T() == null || configurazioneModello.getUrl().isEmpty() ) {
			throw new NullPointerException("Impossibile effettuare l'operazione, parametri vuoti");

		}

		ConfigurazioneModello configurazioneModelloToUpdate = configurazioniModelliRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"UPDATE impossibile, la configurazione con id: " + id + " non è presente"));
		configurazioneModelloToUpdate.setAlimentazione(configurazioneModello.getAlimentazione());
		configurazioneModelloToUpdate.setCilindrata(configurazioneModello.getCilindrata());
		configurazioneModelloToUpdate.setColore(configurazioneModello.getColore());
		configurazioneModelloToUpdate.setKW_T(configurazioneModello.getKW_T());
		configurazioneModelloToUpdate.setTipoCambio(configurazioneModello.getTipoCambio());
		configurazioneModelloToUpdate.setUrl(configurazioneModello.getUrl());
		return configurazioniModelliRepository.save(configurazioneModelloToUpdate);
	}

	/*
	 * @Override public ConfigurazioneModelloDTO
	 * updateQtaConfigurazioneModello(Integer id, ConfigurazioneModelloDTO
	 * configurazioneModelloDTO) { ConfigurazioneModello
	 * configurazioneModelloToUpdateQta = this.getConfigurazioneModelloById(id);
	 * configurazioneModelloToUpdateQta.setQta(configurazioneModelloDTO.getQta());
	 * return convertEntityToDto(configurazioneModelloToUpdateQta); }
	 */



	@Override
	public ConfigurazioneModelloDTO convertEntityToDto(ConfigurazioneModello configurazioneModello) {
		
		ConfigurazioneModelloDTO confDto = ConfigurazioneModelloDTO.builder()
				.idConfigurazioneModello(configurazioneModello.getIdConfigurazioneModello())
				.alimentazione(configurazioneModello.getAlimentazione())
				.cilindrata(configurazioneModello.getCilindrata()).tipoCambio(configurazioneModello.getTipoCambio())
				.KW_T(configurazioneModello.getKW_T()).colore(configurazioneModello.getColore())
				.Modello(configurazioneModello.getModello().getDescrizione())
				.annoProduzione(configurazioneModello.getModello().getAnnoProduzione())
				.configurazione(configurazioneModello.getConfigurazione().getNome())
				.marchio(configurazioneModello.getConfigurazione().getCostruttore().getMarchio())
				.idConfigurazione(configurazioneModello.getConfigurazione().getIdConfigurazione())
				.idModello(configurazioneModello.getModello().getIdModello())
				.url(configurazioneModello.getUrl())
				
				.build();
		try {
			confDto.setN_telai(configurazioneModello.getAutovetture().stream().map(auto -> auto.getNTelaio()).collect(Collectors.toList()));
		} catch (Exception e) {
			confDto.setN_telai(new ArrayList<String>());
		} 

		return confDto;
	

	}

	private ConfigurazioneModello convertDtoToEntity(ConfigurazioneModelloDTO configurazioneModelloDTO) {
		ConfigurazioneModello configurazioneModello = new ConfigurazioneModello(
				configurazioneModelloDTO.getAlimentazione(), configurazioneModelloDTO.getCilindrata(),
				configurazioneModelloDTO.getTipoCambio(), configurazioneModelloDTO.getKW_T(),
				configurazioneModelloDTO.getColore(), configurazioneModelloDTO.getUrl(),
				configurazioneServices.getConfigurazioneById(configurazioneModelloDTO.getIdConfigurazione()),
				modelloServices.getModelloById(configurazioneModelloDTO.getIdModello()));
		return configurazioneModello;
	}

	@Override
	public ConfigurazioneModelloDTO addConfigurazioneModello(ConfigurazioneModelloDTO configurazioneModelloDTO) {
		return convertEntityToDto(configurazioniModelliRepository.save(convertDtoToEntity(configurazioneModelloDTO)));
	}

}

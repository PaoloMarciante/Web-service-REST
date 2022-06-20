package com.exprivia.concessionario.API.services;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exprivia.concessionario.API.dto.AutovetturaDTO;
import com.exprivia.concessionario.API.model.Autovettura;
import com.exprivia.concessionario.API.repositories.AutovettureRepository;

@Service
@Transactional
public class AutovetturaServicesImplementation implements AutovetturaServices {

	@Autowired
	private AutovettureRepository autovettureRepository;

	@Autowired
	private ConfigurazioneModelloServices configurazioneModelloServices;

	@Override
	public List<Autovettura> getAllAutovetture() {

		return autovettureRepository.findAll();
	}

	@Override
	public Autovettura getAutovetturaById(String id) {

		Optional<Autovettura> autovettura = this.autovettureRepository.findById(id);
		return autovettura.orElseThrow(() -> new EntityNotFoundException("Autovettura con numero di telaio: " + id + " non trovata"));
	}

	@Override
	public Autovettura addAutovettura(Autovettura autovettura) {

		return autovettureRepository.save(autovettura);
	}

	@Override
	public AutovetturaDTO updateAutovettura(String id, AutovetturaDTO autovetturaDTO) throws Exception {
		if (!autovetturaDTO.getN_telaio().isPresent() 
				& !autovetturaDTO.getX_id_configurazione_modello().isPresent()) {
			throw new NullPointerException("Impossibile effettuare l'operazione, parametri vuoti");
		}
		
		Autovettura oldAutovettura = autovettureRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("UPDATE impossibile, autovettura con numero di telaio: " + id + " non trovata"));
		Autovettura newAutovettura = convertDtoToEntity(autovetturaDTO);

		if (newAutovettura.getNTelaio() == null || newAutovettura.equals(oldAutovettura)) {
			// Faccio solo update
			if (newAutovettura.getConfigurazioniModelli() != null) {
				oldAutovettura.setConfigurazioniModelli(newAutovettura.getConfigurazioniModelli());
			}
			
			return convertEntityToDto(oldAutovettura);
		} else {
			// Rimuovo la vecchia autovettura, salvo la nuova
			// Workaround per aggiornare la chiave primaria{
			if (newAutovettura.getConfigurazioniModelli() == null) {
				newAutovettura.setConfigurazioniModelli(oldAutovettura.getConfigurazioniModelli());
			}
		
			
			deleteAutovettura(id);
			Autovettura updatedAutovettura = autovettureRepository.save(newAutovettura);
			oldAutovettura.getVendite().stream().filter(vendite -> vendite.getAutovettura() == oldAutovettura)
					.forEach(vendita -> vendita.setAutovettura(updatedAutovettura));
			return convertEntityToDto(updatedAutovettura);
		}
	}

	@Override
	public void deleteAutovettura(String id) {
		autovettureRepository.deleteById(id);

	}

	private Autovettura convertDtoToEntity(AutovetturaDTO autovetturaDTO) {
		// Dichiaro autovettura

		Autovettura autovettura;
		Optional<String> id = autovetturaDTO.getN_telaio();
		if (id.isPresent()) {
			// Instanzio autovettura{
			autovettura = autovettureRepository.findById(id.get()).orElse(new Autovettura());
			autovettura.setNTelaio(id.get());
		} else {
			// Instanzo autovettura{
			autovettura = new Autovettura();
		}

		// Controlla se x_id_configurazione_modello e url sono  presenti su autovetturaDTO (nel
		// JSON in input)
		// Se presenti setta   l'istanza di configurazione modello nell'oggetto
		// autovettura 
		// L'istanza di configurazione modello viene ottenuta dal service tramite l'id

		
		autovetturaDTO.getX_id_configurazione_modello().ifPresent(idConf -> {
			autovettura.setConfigurazioniModelli(configurazioneModelloServices.getConfigurazioneModelloById(idConf));
		});

		return autovettura;

	}

	private AutovetturaDTO convertEntityToDto(Autovettura autovettura) {
		return AutovetturaDTO.builder().n_telaio(Optional.of(autovettura.getNTelaio()))
				.x_id_configurazione_modello(Optional.of(autovettura.getConfigurazioniModelli().getIdConfigurazioneModello()))
				.build();
	}

}

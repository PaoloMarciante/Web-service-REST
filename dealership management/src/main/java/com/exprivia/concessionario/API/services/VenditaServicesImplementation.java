package com.exprivia.concessionario.API.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exprivia.concessionario.API.dto.VenditaDTO;
import com.exprivia.concessionario.API.model.Vendita;
import com.exprivia.concessionario.API.repositories.VenditeRepository;

@Service
@Transactional
public class VenditaServicesImplementation implements VenditaServices {

	@Autowired
	public VenditeRepository venditeRepository;

	@Autowired
	private ClienteServices clienteServices;

	@Autowired
	private AutovetturaServices autovetturaServices;

	@Override
	public Vendita getVenditeByid(Integer id) {
		Optional<Vendita> vendita = this.venditeRepository.findById(id);
		return vendita.orElseThrow(() -> new EntityNotFoundException("Vendita con id: " + id + " non trovata"));
	}
	
	@Override
	public List<VenditaDTO> findAllVendite() {
		return venditeRepository.FindAllVendite().stream().map(this::convertEntityToDto).collect(Collectors.toList());

	}
	
	@Override
	public VenditaDTO findVenditaById(Integer id) {
		return convertEntityToDto(getVenditeByid(id));

	}

	@Override
	public Vendita addVendita(Vendita vendita) {

		return venditeRepository.save(vendita);
	}

	@Override
	public Vendita updateVendite(Integer id, Vendita vendita) {
		Vendita venditaToUpdate = this.getVenditeByid(id);
		venditaToUpdate.setData(vendita.getData());
		venditaToUpdate.setPrezzo(vendita.getPrezzo());
		venditaToUpdate.setTarga(vendita.getTarga());
		return venditeRepository.save(venditaToUpdate);
	}


	@Override
	public VenditaDTO convertEntityToDto(Vendita vendita) {
		return VenditaDTO.builder().idVendita(vendita.getIdVendita()).data(vendita.getData())
				.prezzo(vendita.getPrezzo()).nome(vendita.getCliente().getNome())
				.cognome(vendita.getCliente().getCognome()).codiceFiscale(vendita.getCliente().getCodiceFiscale())
				.targa(vendita.getTarga())
				.nTelaio(vendita.getAutovettura().getNTelaio()).build();
	}

	@Override
	public VenditaDTO addVenditaCliente(VenditaDTO venditaDTO) {
		return convertEntityToDto(venditeRepository.save(convertDtoToEntity(venditaDTO)));
	}

	private Vendita convertDtoToEntity(VenditaDTO venditaDTO) {
		Vendita vendita = new Vendita();
		vendita.setData(venditaDTO.getData());
		vendita.setPrezzo(venditaDTO.getPrezzo());
		vendita.setTarga(venditaDTO.getTarga());
		vendita.setCliente(clienteServices.getClienteByid(venditaDTO.getCodiceFiscale()));
		vendita.setAutovettura(autovetturaServices.getAutovetturaById(venditaDTO.getNTelaio()));
		return vendita;
	}

}

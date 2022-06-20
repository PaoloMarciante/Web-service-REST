package com.exprivia.concessionario.API.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exprivia.concessionario.API.dto.ClienteDTO;
import com.exprivia.concessionario.API.model.Cliente;
import com.exprivia.concessionario.API.repositories.ClientiRepository;

@Service
@Transactional
public class ClientiServicesImplementation implements ClienteServices {

	@Autowired
	private ClientiRepository clientiRepository;

	@Override
	public List<Cliente> getAllClienti() {

		return clientiRepository.findAll();
	}

	@Override
	public Cliente getClienteByid(String id) {
		Optional<Cliente> cliente = this.clientiRepository.findById(id);
		return cliente.orElseThrow(() -> new EntityNotFoundException("Cliente con CF: " + id + " non trovato"));
	}

	@Override
	public Cliente addCliente(Cliente cliente) {

		return clientiRepository.save(cliente);
	}

	@Override
	public ClienteDTO updateCliente(String id, ClienteDTO clienteDTO) throws Exception {

		if (!clienteDTO.getCodiceFiscale().isPresent() & !clienteDTO.getNome().isPresent()
				& !clienteDTO.getCognome().isPresent()) {
			throw new NullPointerException("Impossibile effettuare l'operazione, parametri vuoti");
		}
		Cliente oldCliente = clientiRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("UPDATE impossibile, cliente con CF: " + id + " non trovato"));
		Cliente newCliente = convertDtoToEntity(clienteDTO);

		if (newCliente.getCodiceFiscale() == null || newCliente.equals(oldCliente)) {

			if (newCliente.getCognome() != null) {
				oldCliente.setCognome(newCliente.getCognome());
			}
			if (newCliente.getNome() != null) {
				oldCliente.setNome(newCliente.getNome());
			}
			return convertEntityToDto(oldCliente);

		} else {
			// Rimuovo il vecchio cliente, salvo il nuovo
			// Workaround per aggiornare la chiave primaria{
			if (newCliente.getNome() == null) {
				newCliente.setNome(oldCliente.getNome());
			}
			if (newCliente.getCognome() == null) {

				newCliente.setCognome(oldCliente.getCognome());
			}
			deleteCliente(id);
			Cliente updatedCliente = clientiRepository.save(newCliente);
			oldCliente.getVendite().stream().filter(vendite -> vendite.getCliente() == oldCliente)
					.forEach(vendita -> vendita.setCliente(updatedCliente));
			return convertEntityToDto(updatedCliente);
		}
	}

	@Override
	public void deleteCliente(String id) {
		clientiRepository.deleteById(id);

	}

	private Cliente convertDtoToEntity(ClienteDTO clienteDTO) {
		// Dichiaro cliente

		Cliente cliente;
		Optional<String> id = clienteDTO.getCodiceFiscale();
		if (id.isPresent()) {
			// Instanzio cliente{
			cliente = clientiRepository.findById(id.get()).orElse(new Cliente());
			cliente.setCodiceFiscale(id.get());

		} else {
			// Instanzo cliente{
			cliente = new Cliente();
		}

		clienteDTO.getNome().ifPresent(value -> {
			cliente.setNome(value);
		});

		clienteDTO.getCognome().ifPresent(value -> {
			cliente.setCognome(value);
		});

		return cliente;

	}

	private ClienteDTO convertEntityToDto(Cliente cliente) {
		return ClienteDTO.builder()
				.codiceFiscale(Optional.of(cliente.getCodiceFiscale()))
				.nome(Optional.of(cliente.getNome()))
				.cognome(Optional.of(cliente.getCognome()))
				.build();
	}
	
/*	private ClienteDTO convertEntityToDto(Cliente cliente){
		return modelMapper.map(ClienteDTO);
		} funziona anch'esso con gli optional,importare il modelMapper tramite pom */ 

}

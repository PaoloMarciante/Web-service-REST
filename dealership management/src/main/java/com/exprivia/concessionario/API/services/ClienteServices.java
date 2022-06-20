package com.exprivia.concessionario.API.services;

import java.util.List;

import com.exprivia.concessionario.API.dto.ClienteDTO;
import com.exprivia.concessionario.API.model.Cliente;

public interface ClienteServices {
	public List<Cliente> getAllClienti();

	public Cliente getClienteByid(String id);

	public Cliente addCliente(Cliente cliente);

	public ClienteDTO updateCliente(String id, ClienteDTO clienteDTO) throws Exception;

	void deleteCliente(String id);

}

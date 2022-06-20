package com.exprivia.concessionario.API.services;

import java.util.List;

import com.exprivia.concessionario.API.dto.VenditaDTO;
import com.exprivia.concessionario.API.model.Vendita;

public interface VenditaServices {

	public Vendita getVenditeByid(Integer id);

	public Vendita addVendita(Vendita vendite);

	public Vendita updateVendite(Integer id, Vendita vendite);

	public List<VenditaDTO> findAllVendite();

	public VenditaDTO convertEntityToDto(Vendita vendita);

	public VenditaDTO addVenditaCliente(VenditaDTO venditeDTO);

	public VenditaDTO findVenditaById(Integer id);

}

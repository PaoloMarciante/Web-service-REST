package com.exprivia.concessionario.API.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.exprivia.concessionario.API.model.Vendita;

public interface VenditeRepository extends JpaRepository<Vendita, Integer> {

	@Query(value = "SELECT id_vendita,targa, x_codice_fiscale, x_ntelaio, nome,cognome,data,prezzo,descrizione  FROM clienti,vendite,autovetture,configurazioni_modelli,modelli where codice_fiscale = x_codice_fiscale AND x_id_configurazione_modello = id_configurazione_modello AND id_modello = x_id_modello AND x_ntelaio = n_telaio ;", nativeQuery = true)
	public List<Vendita> FindAllVendite();

}

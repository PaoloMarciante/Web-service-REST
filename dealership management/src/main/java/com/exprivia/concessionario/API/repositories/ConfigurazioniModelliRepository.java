package com.exprivia.concessionario.API.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.exprivia.concessionario.API.model.ConfigurazioneModello;

public interface ConfigurazioniModelliRepository extends JpaRepository<ConfigurazioneModello, Integer> {

	//-------------------Query JPQL,è possibile utilizzare anchce le query native sql aggiungendo alla fine nativeQuery = true------------------------------------
	
	// trovo tutte le auto all'interno del concessionario non ancora vendute
	@Query(value = "SELECT confMod FROM Autovettura A, ConfigurazioneModello confMod where confMod.idConfigurazioneModello = A.configurazioniModelli.idConfigurazioneModello and not A.nTelaio in (SELECT V.autovettura.nTelaio FROM Vendita V) ")
	List<ConfigurazioneModello> findAllCarInside();
	
	@Query(value = "SELECT * FROM concessionario.configurazioni_modelli,concessionario.modelli,concessionario.configurazioni,concessionario.costruttori where id_modello = x_id_modello and id_configurazione = x_id_configurazione and id_costruttore = x_id_costruttore" ,nativeQuery = true)
	List<ConfigurazioneModello> findAllCar();
	
	
	//Restituisce l'auto venduta filtrandola per n_telaio
/*	@Query(value = "SELECT confMod FROM Autovettura A, ConfigurazioneModello confMod where confMod.idConfigurazioneModello = A.configurazioniModelli.idConfigurazioneModello AND A.nTelaio = :n_telaio ")
	ConfigurazioneModello findCarByTelaio(@Param("n_telaio") String n_telaio) ;*/
	

}

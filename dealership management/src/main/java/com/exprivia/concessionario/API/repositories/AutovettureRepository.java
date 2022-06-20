package com.exprivia.concessionario.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exprivia.concessionario.API.model.Autovettura;


public interface AutovettureRepository extends JpaRepository<Autovettura, String> {
	

}

package com.exprivia.concessionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.exprivia.concessionario.API.repositories.ConfigurazioniModelliRepository;
import com.exprivia.concessionario.API.repositories.VenditeRepository;


@SpringBootApplication
public class ConcessionarioApplication implements CommandLineRunner {

	@Autowired
	VenditeRepository venditaRepository;

	@Autowired
	ConfigurazioniModelliRepository configurazioniModelliRepository;

	public static void main(String[] args) {
		SpringApplication.run(ConcessionarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(venditaRepository.FindAllVenditeClienti());
		// System.out.println(configurazioniModelliRepository.findAllCarInside());

	}


}

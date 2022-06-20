package com.exprivia.concessionario.API.dto;

import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {

	@Schema(description = "Codice Fiscale,identificatore univoco del Cliente .",example = "MRCPLA92S22I544C",required = true)
	private Optional<String> codiceFiscale;
	
	@Schema(description = "Nome del cliente.",example = "Paolo",required = true)
	private Optional<String> nome;
	
	@Schema(description = "Cognome del cliente.",example = "Marciante",required = true)
	private Optional<String> cognome;
}

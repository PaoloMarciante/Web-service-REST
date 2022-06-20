package com.exprivia.concessionario.API.dto;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutovetturaDTO {
	
	@Schema(description = "Numero del telaio dell'autovettura venduta (17 CARATTERI)",example= "1HGBH41JXMN109186", required = true)
	@JsonProperty(value = "ntelaio")
	private Optional<String> n_telaio;
	
	@Schema(description = "Identificatore univoco della Configurazione/Modello.",example = "1",required = true)
	private Optional<Integer> x_id_configurazione_modello;
}

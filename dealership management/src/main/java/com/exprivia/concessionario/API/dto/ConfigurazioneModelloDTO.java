package com.exprivia.concessionario.API.dto;


import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigurazioneModelloDTO {
	
	@Schema(description = "Identificatore univoco della Configurazione/Modello autogenerato.",example = "1")
	private Integer idConfigurazioneModello;
	
	@Schema(description = "Alimentazione dell'autovettura",example = "Benzina",required = true)
	@NotNull(message = "Inserire l'alimentazione dell'autovettura")
	@Size(max = 15 , message = "Il campo deve contenere un massimo di 15 caratteri")
	private String alimentazione;
	
	@Schema(description = "Cilindrata dell'autovettura espressa in cm³",example = "1600",required = true)
	@NotNull(message = "Inserire la cilindrata")
	private Integer cilindrata;
	
	@Schema(description = "Tipologia del cambio dell'autovettura",example = "Automatico",required = true)
	@NotNull(message = "Inserire la tipologia del cambio")
	@Size(max = 15 , message = "Il campo deve contenere un massimo di 15 caratteri")
	private String tipoCambio;
	
	@Schema(description = "Rapporto peso potenza dell'autovettura espresso in KW",example = "90",required = true)
	@NotNull(message = "Inserire un rapporto Peso/Potenza (kw_t)")
	private Integer KW_T;
	
	@Schema(description = "Colore dell'autovettura",example = "Rosso Etna",required = true)
	@NotNull(message = "Inserire un colore")
	@Size(max = 20 , message = "Il campo deve contenere un massimo di 20 caratteri")
	private String colore;
	
	@Schema(description = "Nome del modello dell'autovettura",example = "Giulietta")
	private String Modello;
	
	@Schema(description = "Anno di produzione del veicolo",example = "2022-03-10",required = true)
	private Integer annoProduzione;
	
	@Schema(description = "Nome della configurazione dell'autovettura ",example = "Sport line")
	private String configurazione;
	
	@Schema(description = "Marchio dell'autovettura",example = "Alfa Romeo")
	private String marchio;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<String> n_telai;
	
	@Schema(description = "Url dell'immagine da visuliazzira nella dashbord ",example = "https://i.postimg.cc/qqXThVVm/volkswagen-tiguan-removebg-preview.png",required = true)
	private String url;

	@Schema(description = "Chiave Esterna del modello,inserire oltre ai campi richiesti inserire un id di riferimento.",example = "1",required = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer idModello;

	@Schema(description = "Chiave Esterna della configurazione,inserire oltre ai campi richiesti inserire un id di riferimento.",example = "1",required = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer idConfigurazione;

}

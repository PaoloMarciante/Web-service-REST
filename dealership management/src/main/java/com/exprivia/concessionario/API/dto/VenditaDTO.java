package com.exprivia.concessionario.API.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenditaDTO {

	@Schema(description = "Identificatore univoco della vendita autogenerato.",example = "1")
	private Integer idVendita;

	@Schema(description = "Data della vendita in formato Americano.",example = "2022-12-30",required = true)
	private Date data;
	
	@Schema(description = "Prezzo della vendita in Euro",example = "18000",required = true)
	@NotNull(message = "Inserire un  prezzo di vendita")
	private Integer prezzo;
	
	@Schema(description = "Nome del cliente.",example = "Paolo",required = true)
	@Size(max = 10, message = "Inserire massimo 15 caratteri")
	private String nome;
	
	@Schema(description = "Cognome del cliente.",example = "Marciante",required = true)
	@Size(max = 10, message = "Inserire massimo 15 caratteri")
	private String cognome;
	
	@Schema(description = "Codice Fiscale del cliente che ha acquistato l'autovettura (16 CARATTERI)",example ="MRCPLG76A22I544C",required = true)
	@Size(min = 16, max = 16, message = "La lunghezza del codice fiscale è errata (inserire 16 caratteri)")
	@Pattern(regexp = "^[A-Za-z]{6}[0-9]{2}[A-Za-z][0-9]{2}[A-Za-z][0-9]{3}[A-Za-z]$", message = "Codice fiscale errato.")
	private String codiceFiscale;
	
	@Schema(description = "Numero del telaio dell'autovettura venduta (17 CARATTERI)",example= "1HGBH41JXMN109186", required = true)
	@Size(max = 17 , min = 17, message = "Il numero di telaio deve avere una sequenza di 17 caratteri")
	private String nTelaio;
	
	@Schema(description = "Targa dell'auto venduta",example = "DB877SA",required = true)
	@Size(max = 10, message = "Inserire massimo 10 caratteri")
	private String targa;

}

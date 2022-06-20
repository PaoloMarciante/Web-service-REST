package com.exprivia.concessionario.API.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


@ToString
@Entity
@Table(name = "Clienti")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Cliente  {

	@Schema(description = "Codice Fiscale,identificatore univoco del Cliente .",example = "MRCPLA92S22I544C",required = true)
	@Id
	@Column(name="codice_fiscale")
	@Length(min=16, max=16, message = "Lunghezza codice fiscale errata,inserire 16 caratteri.")
	@Pattern(regexp = "^[A-Za-z]{6}[0-9]{2}[A-Za-z][0-9]{2}[A-Za-z][0-9]{3}[A-Za-z]$", message = "Codice fiscale errato.")
	@NonNull	
	private String codiceFiscale;
	
	@Schema(description = "Nome del cliente.",example = "Paolo",required = true)
	@NonNull
	@Column(nullable = false)
	@Length(max = 15 , min = 5)
	private String Nome;
	
	@Schema(description = "Cognome del cliente.",example = "Marciante",required = true)
	@NonNull
	@Column(nullable = false)
	@Length(max = 15, min = 5)
	private String Cognome;
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	private List<Vendita> vendite;
	
	
	

}

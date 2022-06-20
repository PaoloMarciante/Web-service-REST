package com.exprivia.concessionario.API.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "configurazioni_modelli")
@NoArgsConstructor
@RequiredArgsConstructor
public class ConfigurazioneModello {
	
	@Schema(description = "Identificatore univoco della Configurazione/Modello autogenerato.",example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_configurazione_modello", nullable = false)
	private Integer idConfigurazioneModello;

	@Schema(description = "Alimentazione dell'autovettura",example = "Benzina",required = true)
	@NonNull
	@Column(nullable = false)
	@Length(max = 15)
	private String alimentazione;
	
	@Schema(description = "Cilindrata dell'autovettura espressa in cm³",example = "1600",required = true)
	@NonNull
	@Column(nullable = false)
	private Integer cilindrata;

	@Schema(description = "Tipologia del cambio dell'autovettura",example = "Automatico",required = true)
	@NonNull
	@Column(nullable = false)
	@Length(max = 15)
	private String tipoCambio;

	@Schema(description = "Rapporto peso potenza dell'autovettura espresso in KW",example = "90",required = true)
	@JsonProperty(value = "kw_t")
	@NonNull
	@Column(nullable = false)
	private Integer KW_T;

	@Schema(description = "Colore dell'autovettura",example = "Rosso Etna",required = true)
	@NonNull
	@Column(nullable = false)
	@Length(max = 20)
	private String colore;  
	  
	@Schema(description = "Url dell'immagine da visualizzare nella dashbord ",example = "https://i.postimg.cc/qqXThVVm/volkswagen-tiguan-removebg-preview.png",required = true)
	@NonNull
	@Column(nullable = false)
	private String url;

	@Schema(description = "Chiave primaria della configurazione,assegnare un valore di riferimento ",example = "1",required = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "x_id_configurazione", nullable = false)
	@NonNull
	private Configurazione configurazione;

	@Schema(description = "Chiave primaria del modello,assegnare un valore di riferimento ",example = "1",required = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "x_id_modello", nullable = false)
	@NonNull
	private Modello modello;

	
	@JsonBackReference
	@OneToMany(mappedBy = "configurazioniModelli", cascade = CascadeType.ALL)
	private List<Autovettura> autovetture;

	@Override
	public String toString() {
		return "Auto nel concessionario: [idConfigurazioneModello= " + idConfigurazioneModello + ", Marchio= "
				+ configurazione.getCostruttore().getMarchio() + ", Modello= " + modello.getDescrizione()
				+ ", Configurazione= " + configurazione.getNome() + ", Anno_Produzione= "
				+ modello.getAnnoProduzione() + ", alimentazione= " + alimentazione + ", cilindrata= " + cilindrata
				+ ", tipoCambio= " + tipoCambio + ", KW_T= " + KW_T + ", colore= " + colore +  "]" + "\n";
	}

}

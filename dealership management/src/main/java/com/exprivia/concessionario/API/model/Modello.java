package com.exprivia.concessionario.API.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Entity
@Table(name = "Modelli")
@NoArgsConstructor
@AllArgsConstructor
public class Modello  {

	@Schema(description = "Identificatore univoco del modello autogenerato.",example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modello", nullable = false)
	private Integer idModello;

	@Schema(description = "Nome del modello dell'autovettura",example = "Giulietta",required = true)
	@Column(nullable = false)
	@Length(max = 20 ,  min = 2)
	private String descrizione;

	@Schema(description = "Anno di produzione del veicolo",example = "2022-03-10",required = true)
	@Column(nullable = false)
	private Integer annoProduzione;

	@JsonIgnore
	@OneToMany(mappedBy = "modello", cascade = CascadeType.ALL)
	private List<ConfigurazioneModello> configurazioniModelli;

}

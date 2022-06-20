package com.exprivia.concessionario.API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "configurazioni")
@NoArgsConstructor
public class Configurazione {

	@Schema(description = "Identificatore univoco della configurazione autogenerato ",example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // assegnazione primary key
	@Id
	@Column(name = "id_configurazione")
	private Integer idConfigurazione;

	@Schema(description = "Nome della configurazione dell'autovettura ",example = "Sport line",required = true)
	@Column(nullable = false)
	@Length(max = 20 , min = 2)
	private String nome; // x_id_costruttore

	@Schema(description = "Chiave esterna del costruttore ",example = "1")
	@JsonBackReference // per evitare relazioni ricorsive in JSON. Questa annotazione in pratica dice
	// che un Costruttore non farà parte del JSON restituito per la configurazione
	// (ma ogni Costruttore conterrà l'elenco delle sue configurazioni nella
	// risposta)
	@ManyToOne(optional = false)
	@JoinColumn(name = "x_id_costruttore", nullable = false)
	private Costruttore costruttore;

	
	@JsonIgnore
	@OneToMany(mappedBy = "configurazione", cascade = CascadeType.ALL)
	private List<ConfigurazioneModello> configurazioniModelli;

}

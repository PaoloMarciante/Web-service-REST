package com.exprivia.concessionario.API.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@ToString
@Entity
@Table(name = "autovetture")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Autovettura {

	@Schema(description = "Numero del telaio dell'autovettura venduta (17 CARATTERI)",example= "1HGBH41JXMN109186", required = true)
	@Id
	@Column(name = "n_telaio")
	@Length(max = 17 , min = 17, message = "Il numero di telaio deve essere una sequenza di 17 caratteri")
	@NonNull
	private String nTelaio;
	
	
	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn(name = "x_id_configurazione_modello", referencedColumnName = "id_configurazione_modello")
	@NonNull
	private ConfigurazioneModello configurazioniModelli;

	/*
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "vendite", joinColumns = {@JoinColumn(name = "x_targa",
	 * referencedColumnName = "targa")}, inverseJoinColumns = {@JoinColumn(name =
	 * "x_codice_fiscale", referencedColumnName = "codice_fiscale")}) private
	 * List<Cliente> clienti;
	 */

	@JsonIgnore
	@OneToMany(mappedBy = "autovettura")
	private List<Vendita> vendite;

}

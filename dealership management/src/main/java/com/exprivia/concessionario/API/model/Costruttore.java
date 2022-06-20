package com.exprivia.concessionario.API.model;

import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "costruttori")
@NoArgsConstructor

public class Costruttore  {

	@Schema(description = "Identificatore univoco del costruttore autogenerato.",example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // assegnazione primary key
	@Column(name = "id_costruttore")
	private Integer idCostruttore;

	@Schema(description = "Marchio dell'autovettura",example = "Alfa Romeo",required = true)
	@Column(name = "marchio", nullable = false)
	@Length(max = 30 , min = 2)
	private String marchio;

	@JsonIgnore
	@OneToMany(mappedBy = "costruttore", cascade = CascadeType.ALL)
	private List<Configurazione> configurazioni;

}

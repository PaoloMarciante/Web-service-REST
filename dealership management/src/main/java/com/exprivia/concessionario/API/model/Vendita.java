package com.exprivia.concessionario.API.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Entity
@Table(name = "vendite")
@NoArgsConstructor
@AllArgsConstructor
public class Vendita  {


	@Schema(description = "Identificatore univoco della vendita autogenerato.",example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vendita", nullable = false)
	private Integer idVendita;

	@Schema(description = "Data della vendita in formato Americano.",example = "2022-12-30",required = true)
//	@JsonFormat(pattern = "dd/MM/yyyy")
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Schema(description = "Prezzo della vendita in Euro",example = "18000",required = true)
	@Column(nullable = false)
	private Integer prezzo;
	
	@Schema(description = "Targa dell'auto venduta",example = "DB877SA",required = true)
	@Length(max = 10)
	@Column(nullable = false)
	private String targa;
	
	@Schema(description = "Codice Fiscale del cliente che ha acquistato l'autovettura (16 CARATTERI)",example ="MRCPLG76A22I544C")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "x_codice_fiscale", nullable = false)
	private Cliente cliente;
	
	@Schema(description = "Numero del telaio dell'autovettura venduta (17 CARATTERI)",example= "1HGBH41JXMN109186")
	@ManyToOne
	@JoinColumn(name = "x_Ntelaio", nullable = false, unique = true)
	private Autovettura autovettura;

	@Override
	public String toString() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return "Vendite:  [idVendita=" + idVendita + ", cliente=" + cliente.getNome() + " " + cliente.getCognome()
				+ ", n_telaio= " + autovettura.getNTelaio() + ", targa= " + targa + ", Modello="
				+ autovettura.getConfigurazioniModelli().getModello().getDescrizione() + ", prezzo= $" + prezzo
				+ ", data=" + simpleDateFormat.format(data) + " ]";
	}

}

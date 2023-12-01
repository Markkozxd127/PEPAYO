package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="VUELO")
public class Vuelo {
	
	@Id
	@Column(name = "ID_VUELO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVuelo")
    @SequenceGenerator(name = "seqVuelo", allocationSize = 1, sequenceName = "SEQ_VUELO")
    private Integer id;
	
	@Column(name = "FECHA_SALIDA")	
	@NotBlank @NotNull
    private String fechasa;
	
	@Column(name = "HORA_SALIDA")	
	@NotBlank @NotNull          
    private String horasa;
	
	@Column(name = "FECHA_LLEGADA")	
	@NotBlank @NotNull
    private String fechallega;
	
	@Column(name = "HORA_LLEGADA")	
	@NotBlank @NotNull          
    private String horalle;
	
	@Column(name = "ORIGEN")	
	@NotBlank @NotNull          
    private String origen;
	
	@Column(name = "DESTINO")	
	@NotBlank @NotNull
    private String destino;
	
	@Column(name = "NUMERO_PLAZAS")	
	@NotBlank @NotNull
    private String numeropla;
	

//TODO RELACIONES 	
	
	
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vuelo")
		@JsonIgnore
		private Set<Reserva> reservas;

}
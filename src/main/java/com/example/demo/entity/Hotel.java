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
@Table(name="HOTEL")
public class Hotel {
	@Id
	@Column(name = "ID_HOTEL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHotel")
    @SequenceGenerator(name = "seqHotel", allocationSize = 1, sequenceName = "SEQ_HOTEL")
    private Integer id;
	
	//  ---------------------------------------------------------------NOMBRE DEL AUTOR----------------------    
	@Column(name = "NOMBREHOTEL")
	@NotNull @NotBlank    
    private String nombrehotel;
	//  ---------------------------------------------------------------APELLIDO_AUTOR DEL AUTOR--------------
	@Column(name = "DIRECCION")
	@NotNull @NotBlank    
    private String direccion;
	//  ---------------------------------------------------------------FECHA_NACIMIENTO DEL AUTOR-------------
	@Column(name = "LOCALIDAD")
	@NotNull @NotBlank    
    private String localidad;
	//  ---------------------------------------------------------------LUGAR_NACIMIENTO DEL AUTOR-------------
	@Column(name = "PROVINCIA")
	@NotNull @NotBlank    
    private String provincia;
	//  ---------------------------------------------------------------GENERO DEL AUTOR-----------------------
	@Column(name = "TELEFONO")
	@NotNull @NotBlank    
    private String telefono;
	//  ---------------------------------------------------------------GENERO DEL AUTOR-----------------------
	@Column(name = "NUMEROESTRELLA")
	@NotNull @NotBlank    
    private String numeroestrella;

								//TODO RELACIONES 	
	
		//----------RELACION DE AUTOR CON LIBRO----------
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
		@JsonIgnore
		private Set<Reserva> reservas;

}

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
@Table(name=" CLIENTE ")
public class Cliente {
	@Id
	@Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
    @SequenceGenerator(name = "seqCliente", allocationSize = 1, sequenceName = "SEQ_CLIENTE")
    private Integer id;
	
	//  ---------------------------------------------------------------NOMBRE DEL AUTOR----------------------    
	@Column(name = "DNI")
	@NotNull @NotBlank    
    private String dni;
	//  ---------------------------------------------------------------APELLIDO_AUTOR DEL AUTOR--------------
	@Column(name = "NOMBRECLIENTE")
	@NotNull @NotBlank    
    private String nombrecliente;
	//  ---------------------------------------------------------------FECHA_NACIMIENTO DEL AUTOR-------------
	@Column(name = "APELLIDOS")
	@NotNull @NotBlank    
    private String apellidos;
	//  ---------------------------------------------------------------LUGAR_NACIMIENTO DEL AUTOR-------------
	@Column(name = "TELEFONO")
	@NotNull @NotBlank    
    private String telefono;
	//  ---------------------------------------------------------------GENERO DEL AUTOR-----------------------
	@Column(name = "EMAIL")
	@NotNull @NotBlank    
    private String email;

								//TODO RELACIONES 	
	
		//----------RELACION DE AUTOR CON LIBRO----------
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
		@JsonIgnore
		private Set<Reserva> reservas;

}

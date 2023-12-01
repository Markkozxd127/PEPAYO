package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               
@AllArgsConstructor 
@NoArgsConstructor  

public class ReservaDto {
	
	//atributos
    private String clase;
   
	
	//foraneas
    					private int cliente;
    					private int sucursal;
    					private int hotel ;
    					private int vuelo;
}	

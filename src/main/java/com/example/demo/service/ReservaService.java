package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ReservaDto;
import com.example.demo.entity.Reserva;



public interface ReservaService  <T>{
	Reserva update(int id, ReservaDto reservaDto);
	void delete(int id);
	Optional<T> read(int id);
	List<T> readAll();
	Reserva guardarReserva(ReservaDto reservaDto);
}	



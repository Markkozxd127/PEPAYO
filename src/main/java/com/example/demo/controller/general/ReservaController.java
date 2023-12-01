package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReservaDto;
import com.example.demo.entity.Reserva;

import com.example.demo.serviceImpl.ReservaServiceImpl;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.demo.commons.GlobalConstans.API_RESERVA;;

@RestController
@RequestMapping(API_RESERVA)
@CrossOrigin({"*"})
public class ReservaController {
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
		
	@GetMapping("/ListRese")
	public ResponseEntity<List<Reserva>> listar() {
		try {
		      List<Reserva> lib = reservaServiceImpl.readAll();
		      if (lib.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lib, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	
	@GetMapping("/BuscarRese/{id}")
	public ResponseEntity<Reserva> getLibroById(@PathVariable("id") int id){
		Optional<Reserva> carData = reservaServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Reserva>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	  @PostMapping("/InsertRese")
	    public ResponseEntity<Reserva> crear(@Valid @RequestBody ReservaDto reservaDto ) {
	        try {
	        	Reserva libro = reservaServiceImpl.guardarReserva(reservaDto);
	            return new ResponseEntity<>(libro, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	@DeleteMapping("/DeleteRese/{id}")
	public ResponseEntity<Reserva> delete(@PathVariable("id") int id){
		try {
			reservaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
    @PutMapping("/EditRese/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable("id") int id, @Valid @RequestBody ReservaDto reservaDto) {
        try {
        	Reserva updatedLibro = reservaServiceImpl.update(id, reservaDto);
            return new ResponseEntity<>(updatedLibro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
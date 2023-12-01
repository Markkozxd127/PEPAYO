package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Vuelo;
import com.example.demo.serviceImpl.VueloServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_VUELO;;

@RestController
@RequestMapping(API_VUELO)
@CrossOrigin({"*"})
public class VueloController {
	@Autowired
	private VueloServiceImpl vueloServiceImpl;
	
	@GetMapping ("/ListVuelo")
	public ResponseEntity<List<Vuelo>> listar() {
		try {
		      List<Vuelo> cat = vueloServiceImpl.readAll();
		      if (cat.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cat, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	@GetMapping("/BuscarVuelo/{id}")
	public ResponseEntity<Vuelo> getCategoriaById(@PathVariable("id") int id){
		Optional<Vuelo> carData = vueloServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Vuelo>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertVuelo")
    public ResponseEntity<Vuelo> crear(@Valid @RequestBody Vuelo vuelo){
        try {
        	Vuelo _cat = vueloServiceImpl.create(vuelo);
            return new ResponseEntity<Vuelo>(_cat, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        
    }
	
	@DeleteMapping("/DeleteVuelo/{id}")
	public ResponseEntity<Vuelo> delete(@PathVariable("id") int id){
		try {
			vueloServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EditarVuelo/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") int id, @Valid @RequestBody Vuelo vuelo){
		Optional<Vuelo> carData = vueloServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	  Vuelo dbvuelo = carData.get();
	    	  dbvuelo.setFechasa(vuelo.getFechasa());
	    	  dbvuelo.setHorasa(vuelo.getHorasa());
	    	  dbvuelo.setFechallega(vuelo.getFechallega());
	    	  dbvuelo.setHoralle(vuelo.getHoralle());
	    	  dbvuelo.setOrigen(vuelo.getOrigen());
	    	  dbvuelo.setDestino(vuelo.getDestino());
	    	  dbvuelo.setNumeropla(vuelo.getNumeropla());

	        return new ResponseEntity<Vuelo>(vueloServiceImpl.update(dbvuelo), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

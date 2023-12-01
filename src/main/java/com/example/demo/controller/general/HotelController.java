package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Hotel;
import com.example.demo.serviceImpl.HotelServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_HOTEL;;

@RestController
@RequestMapping(API_HOTEL)
@CrossOrigin({"*"})
public class HotelController {
	@Autowired
	private HotelServiceImpl hotellServiceImpl;
	
	@GetMapping ("/ListHo")
	public ResponseEntity<List<Hotel>> listar() {
		try {
		      List<Hotel> cat = hotellServiceImpl.readAll();
		      if (cat.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cat, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	@GetMapping("/BuscarHo/{id}")
	public ResponseEntity<Hotel> getCategoriaById(@PathVariable("id") int id){
		Optional<Hotel> carData = hotellServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Hotel>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertHo")
    public ResponseEntity<Hotel> crear(@Valid @RequestBody Hotel hotel){
        try {
        	Hotel _cat = hotellServiceImpl.create(hotel);
            return new ResponseEntity<Hotel>(_cat, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        
    }
	
	@DeleteMapping("/DeleteHo/{id}")
	public ResponseEntity<Hotel> delete(@PathVariable("id") int id){
		try {
			hotellServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EditarHo/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") int id, @Valid @RequestBody Hotel hotel){
		Optional<Hotel> carData = hotellServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	  Hotel dbhotel = carData.get();
	    	  dbhotel.setNombrehotel(hotel.getNombrehotel());
	    	  dbhotel.setDireccion(hotel.getDireccion());
	    	  dbhotel.setLocalidad(hotel.getLocalidad());
	    	  dbhotel.setProvincia(hotel.getProvincia());
	    	  dbhotel.setTelefono(hotel.getTelefono());
	    	  dbhotel.setNumeroestrella(hotel.getNumeroestrella());


	        return new ResponseEntity<Hotel>(hotellServiceImpl.update(dbhotel), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

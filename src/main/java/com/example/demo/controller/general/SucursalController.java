package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sucursal;
import com.example.demo.serviceImpl.SucursalServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_SUCURSAL;;

@RestController
@RequestMapping(API_SUCURSAL)
@CrossOrigin({"*"})
public class SucursalController {
	@Autowired
	private SucursalServiceImpl sucursalServiceImpl;
	
	@GetMapping ("/ListSucu")
	public ResponseEntity<List<Sucursal>> listar() {
		try {
		      List<Sucursal> cat = sucursalServiceImpl.readAll();
		      if (cat.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cat, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	@GetMapping("/BuscarSucu/{id}")
	public ResponseEntity<Sucursal> getCategoriaById(@PathVariable("id") int id){
		Optional<Sucursal> carData = sucursalServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Sucursal>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertSucu")
    public ResponseEntity<Sucursal> crear(@Valid @RequestBody Sucursal sucursal){
        try {
        	Sucursal _cat = sucursalServiceImpl.create(sucursal);
            return new ResponseEntity<Sucursal>(_cat, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        
    }
	
	@DeleteMapping("/DeleteSucu/{id}")
	public ResponseEntity<Sucursal> delete(@PathVariable("id") int id){
		try {
			sucursalServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EditarSucu/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") int id, @Valid @RequestBody Sucursal sucursal){
		Optional<Sucursal> carData = sucursalServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	  Sucursal dbsucursal = carData.get();
	    	  dbsucursal.setDireccion(sucursal.getDireccion());
	    	  dbsucursal.setLocalidad(sucursal.getLocalidad());
	    	  dbsucursal.setProvincia(sucursal.getProvincia());
	    	  dbsucursal.setTelefono(sucursal.getTelefono());
	    

	        return new ResponseEntity<Sucursal>(sucursalServiceImpl.update(dbsucursal), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

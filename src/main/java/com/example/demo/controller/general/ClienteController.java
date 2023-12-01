package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.serviceImpl.ClienteServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CLIENTE;;

@RestController
@RequestMapping(API_CLIENTE)
@CrossOrigin({"*"})
public class ClienteController {
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping ("/ListCli")
	public ResponseEntity<List<Cliente>> listar() {
		try {
		      List<Cliente> cat = clienteServiceImpl.readAll();
		      if (cat.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cat, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	

	@GetMapping("/BuscarCli/{id}")
	public ResponseEntity<Cliente> getCategoriaById(@PathVariable("id") int id){
		Optional<Cliente> carData = clienteServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Cliente>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/InsertCli")
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente){
        try {
        	Cliente _cat = clienteServiceImpl.create(cliente);
            return new ResponseEntity<Cliente>(_cat, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        
    }
	
	@DeleteMapping("/DeleteCli/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable("id") int id){
		try {
			clienteServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("EditarCli/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") int id, @Valid @RequestBody Cliente cliente){
		Optional<Cliente> carData = clienteServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	  Cliente dbcliente = carData.get();
	    	  dbcliente.setDni(cliente.getDni());
	    	  dbcliente.setNombrecliente(cliente.getNombrecliente());
	    	  dbcliente.setApellidos(cliente.getApellidos());
	    	  dbcliente.setTelefono(cliente.getTelefono());
	    	  dbcliente.setEmail(cliente.getEmail());

	        return new ResponseEntity<Cliente>(clienteServiceImpl.update(dbcliente), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}

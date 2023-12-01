package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReservaDto;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Reserva;
import com.example.demo.entity.Sucursal;
import com.example.demo.entity.Vuelo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.repository.VueloRepository;
import com.example.demo.service.ReservaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaServiceImpl implements ReservaService<Reserva>{

	
	@Autowired
	private ReservaRepository reservaRepository ;
	
	@Autowired
	private HotelRepository hotelRepository ;

	@Autowired
	private SucursalRepository sucursalRepository ;

	@Autowired
	private ClienteRepository  clienteRepository;
	
	@Autowired
	private VueloRepository  vueloRepository;

	@Override
	public Reserva update(int id, ReservaDto reservaDto) {
	    Optional<Reserva> optionalReserva = reservaRepository.findById(id);

	    if (optionalReserva.isPresent()) {
	    	Reserva reserva = optionalReserva.get();

	        // Actualiza los campos del libro con los valores del DTO
	    	reserva.setClase(reservaDto.getClase());
	      

	        // Actualiza las relaciones con autor, editorial y categoría
	    	reserva.setHotel(hotelRepository.findById(reservaDto.getHotel()).orElse(null));
	    	reserva.setSucursal(sucursalRepository.findById(reservaDto.getSucursal()).orElse(null));
	    	reserva.setCliente(clienteRepository.findById(reservaDto.getCliente()).orElse(null));
	    	reserva.setVuelo(vueloRepository.findById(reservaDto.getVuelo()).orElse(null));


	        // Guarda el libro actualizado en la base de datos
	        return reservaRepository.save(reserva);
	    } else {
	        throw new ResourceNotFoundException("reserva no encontrado con ID: " + id);
	    }
	}
	


	@Override
	public void delete(int id) {
		reservaRepository.deleteById(id);
	}

	@Override
	public Optional<Reserva> read(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Reserva> readAll() {
		// TODO Auto-generated method stub
	    return reservaRepository.findAll();
	}
	
	
	
	
    public Reserva guardarReserva(ReservaDto reservaDto) {
        Hotel hotel = hotelRepository.findById(reservaDto.getHotel())
                .orElseThrow(() -> new EntityNotFoundException("HOTEL not found"));
        
        Sucursal sucursal = sucursalRepository.findById(reservaDto.getSucursal())
                .orElseThrow(() -> new EntityNotFoundException("SUCURSAL not found"));
        
        Cliente cliente = clienteRepository.findById(reservaDto.getCliente())
                .orElseThrow(() -> new EntityNotFoundException("CLIENTE not found"));
        
        
        Vuelo vuelo = vueloRepository.findById(reservaDto.getVuelo())
                .orElseThrow(() -> new EntityNotFoundException("VUELO not found"));

        Reserva reserva = new Reserva();
    
        reserva.setClase(reservaDto.getClase());

        reserva.setHotel(hotel);
        reserva.setSucursal(sucursal);
        reserva.setCliente(cliente);
        reserva.setVuelo(vuelo);
        
        return reservaRepository.save(reserva);
    }
}
package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.HotelService;



@Service
public class HotelServiceImpl implements HotelService<Hotel>{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel t) {
		// TODO Auto-generated method stub
		return hotelRepository.save(t);
	}

	@Override
	public Hotel update(Hotel t) {
		// TODO Auto-generated method stub
		return hotelRepository.save(t);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		hotelRepository.deleteById(id);
	}

	@Override
	public Optional<Hotel> read(int id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id);
	}

	@Override
	public List<Hotel> readAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

}
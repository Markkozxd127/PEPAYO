package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService<Cliente>{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente create(Cliente t) {
		// TODO Auto-generated method stub
		return clienteRepository.save(t);
	}

	@Override
	public Cliente update(Cliente t) {
		// TODO Auto-generated method stub
		return clienteRepository.save(t);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

	@Override
	public Optional<Cliente> read(int id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> readAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

}
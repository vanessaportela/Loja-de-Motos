package com.vanessaportela.lojamotos.dao;

import java.util.List;

import com.vanessaportela.lojamotos.model.Cliente;

public interface ClienteDao {

	List<Cliente> findAll();
	Cliente saveCliente(Cliente cliente);
	Cliente updateCliente(Cliente cliente);
	String deleteCliente(Integer id);
	Cliente findById(Integer id);	
}

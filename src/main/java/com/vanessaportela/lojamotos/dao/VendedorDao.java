package com.vanessaportela.lojamotos.dao;

import java.util.List;

import com.vanessaportela.lojamotos.model.Vendedor;

public interface VendedorDao {

	List<Vendedor> findAll();
	Vendedor saveVendedor(Vendedor vendedor);
	Vendedor updateVendedor(Vendedor vendedor);
	String deleteVendedor(Integer id);
	Vendedor findById(Integer id);	
}

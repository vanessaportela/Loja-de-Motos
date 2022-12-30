package com.vanessaportela.lojamotos.dao;

import java.util.List;

import com.vanessaportela.lojamotos.model.Venda;

public interface VendaDao {

	Venda findById(Integer id);	
	//List<Venda> findAll();
	Venda saveVenda(Venda venda);
	//Venda updateVenda(Venda venda);
	//String deleteVenda(Integer id);
}

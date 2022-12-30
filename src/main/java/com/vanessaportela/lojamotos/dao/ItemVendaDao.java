package com.vanessaportela.lojamotos.dao;

import java.util.List;

import com.vanessaportela.lojamotos.model.ItemVenda;
import com.vanessaportela.lojamotos.model.Venda;

public interface ItemVendaDao {

	ItemVenda findById(Integer id);	
	//List<ItemVenda> findAll();
	//Venda saveVenda(ItemVenda itemVenda);
	//Venda updateVenda(ItemVenda itemVenda);
	//String deleteVenda(ItemVenda id);
}

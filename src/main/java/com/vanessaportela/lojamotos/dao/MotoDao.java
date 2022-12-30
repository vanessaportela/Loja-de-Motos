package com.vanessaportela.lojamotos.dao;

import java.util.List;

import com.vanessaportela.lojamotos.model.Moto;

public interface MotoDao {

	List<Moto> findAll();
	Moto saveMoto(Moto moto);
	Moto updateMoto(Moto moto);
	String deleteMoto(Integer id);
	Moto findById(Integer id);	
}

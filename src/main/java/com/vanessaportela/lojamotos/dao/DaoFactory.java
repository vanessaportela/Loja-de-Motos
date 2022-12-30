package com.vanessaportela.lojamotos.dao;

import com.vanessaportela.lojamotos.dao.impl.ClienteDaoJDBC;
import com.vanessaportela.lojamotos.dao.impl.MotoDaoJDBC;
import com.vanessaportela.lojamotos.dao.impl.VendaDaoJDBC;
import com.vanessaportela.lojamotos.dao.impl.VendedorDaoJDBC;
import com.vanessaportela.lojamotos.db.DB;

public class DaoFactory {

	public static MotoDao createMotoDao() {
		return new MotoDaoJDBC(DB.getConnection());
	}
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static VendedorDao createVendedorDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	}
	
	public static VendaDao createVendaDao() {
		return new VendaDaoJDBC(DB.getConnection());
	}
}

/*package com.vanessaportela.lojamotos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vanessaportela.lojamotos.dao.VendaDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.db.DbException;
import com.vanessaportela.lojamotos.model.Cliente;
import com.vanessaportela.lojamotos.model.ItemVenda;
import com.vanessaportela.lojamotos.model.Moto;
import com.vanessaportela.lojamotos.model.Venda;
import com.vanessaportela.lojamotos.model.Vendedor;

public class ItemVendaDaoJDBC implements VendaDao {

	private Connection conn;

	public ItemVendaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Venda findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// "as NomeCliente" da um apelido a coluna
			ps = conn.prepareStatement("SELECT v.Id, v.Data, v.Valor, c.Nome as NomeCliente, ve.Nome as NomeVendedor "
					+ "FROM venda v "
					+ "LEFT JOIN cliente c ON c.Id = v.Idcliente "
					+ "LEFT JOIN vendedor ve ON ve.Id = v.Idvendedor "
					+ "WHERE v.Id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {				
				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("NomeCliente"));
				
				Vendedor vendedor = new Vendedor();
				vendedor.setNome(rs.getString("NomeVendedor"));
				
				PreparedStatement ps2 = conn.prepareStatement("SELECT iv.Id, iv.Quantidade, iv.ValorUnitario, m.Marca, m.Modelo "
						+ "FROM ItemVenda iv "
						+ "LEFT JOIN Moto m ON m.Id = iv.IdMoto "
						+ "WHERE IdVenda=?");
				ps2.setInt(1, id);
				ResultSet rs2 = ps2.executeQuery();
				
				List<ItemVenda> itensDeVenda = new ArrayList<>();
				while (rs2.next()) {
					Moto moto = new Moto();
					moto.setMarcaMoto(rs2.getString("Marca"));
					moto.setModeloMoto(rs2.getString("Modelo"));
					
					itensDeVenda.add(new ItemVenda(rs2.getInt("Id"), moto, rs2.getInt("Quantidade"), rs2.getDouble("ValorUnitario"), null));
				}
				
				Venda venda = new Venda(rs.getInt("Id"), rs.getDate("Data"), vendedor, cliente, rs.getDouble("Valor"), itensDeVenda);
				return venda;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
			DB.closeResultSet(rs);
		}
	}
}*/

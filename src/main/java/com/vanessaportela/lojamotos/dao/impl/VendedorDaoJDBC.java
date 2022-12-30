package com.vanessaportela.lojamotos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vanessaportela.lojamotos.dao.VendedorDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.db.DbException;
import com.vanessaportela.lojamotos.model.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;

	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM vendedor ORDER BY id");
			rs = ps.executeQuery();

			List<Vendedor> list = new ArrayList<>();

			while (rs.next()) {
				list.add(new Vendedor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM vendedor WHERE Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Vendedor novoVendedor = new Vendedor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				return novoVendedor;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Vendedor saveVendedor(Vendedor vendedor) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO vendedor " + "(matricula, nome, email, telefone) " + "VALUES " + "(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, vendedor.getMatricula());
			ps.setString(2, vendedor.getNome());
			ps.setString(3, vendedor.getEmail());
			ps.setString(4, vendedor.getTelefone());
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					vendedor.setId(id);
					return vendedor;
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpect error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
		}
		return null;
	}

	@Override
	public Vendedor updateVendedor(Vendedor vendedor) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"UPDATE vendedor " + "SET matricula = ?, nome = ?, email = ?, telefone = ? " + "WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, vendedor.getMatricula());
			ps.setString(2, vendedor.getNome());
			ps.setString(3, vendedor.getEmail());
			ps.setString(4, vendedor.getTelefone());
			ps.setInt(5, vendedor.getId());
			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				return vendedor;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
		}
		return null;
	}

	@Override
	public String deleteVendedor(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM vendedor WHERE Id = ?");

			ps.setInt(1, id);

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				return "Vendedor was deleted";
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e.getCause());
		} finally {
			DB.closePreparedStatement(ps);
		}
		return null;
	}
}

package com.vanessaportela.lojamotos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vanessaportela.lojamotos.dao.ClienteDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.db.DbException;
import com.vanessaportela.lojamotos.model.Cliente;

public class ClienteDaoJDBC implements ClienteDao {

	private Connection conn;

	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
//int id, String nome, String email, String telefone, String cpf, String dataNascimento
	@Override
	public List<Cliente> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM cliente ORDER BY id");
			rs = ps.executeQuery();

			List<Cliente> list = new ArrayList<>();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("Id"));
				cliente.setNome(rs.getString("Nome"));
				cliente.setEmail(rs.getString("Email"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setCpf(rs.getString("Cpf"));
				cliente.setDataNascimento(rs.getString("DataNascimento"));
				list.add(cliente);
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
	public Cliente findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("Id"));
				cliente.setNome(rs.getString("Nome"));
				cliente.setEmail(rs.getString("Email"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setCpf(rs.getString("Cpf"));
				cliente.setDataNascimento(rs.getString("DataNascimento"));
				return cliente;
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
	public Cliente saveCliente(Cliente cliente) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO cliente " + "(nome, email, telefone, cpf, dataNascimento) " + "VALUES " + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getCpf());
			ps.setString(5, cliente.getDataNascimento());
		
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					cliente.setId(id);
					return cliente;
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
	public Cliente updateCliente(Cliente cliente) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"UPDATE cliente " + "SET nome = ?, email = ?, telefone = ?, cpf = ?, dataNascimento = ? " + "WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getCpf());
			ps.setString(5, cliente.getDataNascimento());
			ps.setInt(6, cliente.getId());

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				return cliente;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
		}
		return null;
	}
	
	@Override
	public String deleteCliente(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");

			ps.setInt(1, id);

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				return "cliente was deleted";
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e.getCause());
		} finally {
			DB.closePreparedStatement(ps);

		}
		return null;
	}
}

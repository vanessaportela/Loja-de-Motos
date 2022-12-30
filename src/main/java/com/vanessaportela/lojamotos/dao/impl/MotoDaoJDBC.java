package com.vanessaportela.lojamotos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vanessaportela.lojamotos.dao.MotoDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.db.DbException;
import com.vanessaportela.lojamotos.model.Moto;

public class MotoDaoJDBC implements MotoDao {

	private Connection conn;

	public MotoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Moto> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM moto ORDER BY id");
			rs = ps.executeQuery();

			List<Moto> list = new ArrayList<>();

			while (rs.next()) {
				Moto moto = new Moto();
				moto.setId(rs.getInt("Id"));
				moto.setMarcaMoto(rs.getString("Marca"));
				moto.setModeloMoto(rs.getString("Modelo"));
				moto.setAnoMoto(rs.getInt("Ano"));
				moto.setValor(rs.getDouble("Valor"));
				list.add(moto);
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
	public Moto findById(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM moto WHERE Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Moto moto = new Moto();
				moto.setId(rs.getInt("Id"));
				moto.setMarcaMoto(rs.getString("Marca"));
				moto.setModeloMoto(rs.getString("Modelo"));
				moto.setAnoMoto(rs.getInt("Ano"));
				moto.setValor(rs.getDouble("Valor"));
				return moto;
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
	public Moto saveMoto(Moto moto) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO moto " + 
				"(marca, modelo, ano, valor) " + 
				"VALUES " + 
				"(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, moto.getMarcaMoto());
			ps.setString(2, moto.getModeloMoto());
			ps.setInt(3, moto.getAnoMoto());
			ps.setDouble(4, moto.getValor());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					moto.setId(id);
					return moto;
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
	public Moto updateMoto(Moto moto) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"UPDATE moto " + "SET marca = ?, modelo = ?, ano = ?, valor = ? " + "WHERE Id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, moto.getMarcaMoto());
			ps.setString(2, moto.getModeloMoto());
			ps.setInt(3, moto.getAnoMoto());
			ps.setDouble(4, moto.getValor());
			ps.setInt(5, moto.getId());

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				return moto;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
		}
		return null;
	}

	@Override
	public String deleteMoto(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM moto WHERE Id = ?");

			ps.setInt(1, id);

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				return "Moto was deleted";
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e.getCause());
		} finally {
			DB.closePreparedStatement(ps);

		}
		return null;
	}
}

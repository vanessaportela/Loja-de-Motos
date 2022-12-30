package com.vanessaportela.lojamotos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vanessaportela.lojamotos.dao.DaoFactory;
import com.vanessaportela.lojamotos.dao.VendedorDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.model.Vendedor;

@WebServlet(name = "VendedorController", urlPatterns = {"/vendedor"})
public class VendedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();

	VendedorDao vendedorDao = DaoFactory.createVendedorDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);

		Connection conn = DB.getConnection();
		DB.closeConnection(conn);

		PrintWriter pw = response.getWriter();

		pw.print(gson.toJson(vendedorDao.findAll()));

		pw.flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setStatus(201);

		StringBuffer sb = new StringBuffer();

		BufferedReader br = request.getReader();
		String atributos = null;

		while ((atributos = br.readLine()) != null) {
			sb.append(atributos);
		}

		Vendedor novoVendedor = gson.fromJson(sb.toString(), Vendedor.class);

		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(vendedorDao.saveVendedor(novoVendedor)));

		pw.flush();
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setStatus(201);

		StringBuffer sb = new StringBuffer();

		BufferedReader br = request.getReader();
		String atributos = null;

		while ((atributos = br.readLine()) != null) {
			sb.append(atributos);
		}

		Vendedor novoVendedor = gson.fromJson(sb.toString(), Vendedor.class);
		Vendedor vendedorResp = vendedorDao.updateVendedor(novoVendedor);

		PrintWriter pw = response.getWriter();

		if (vendedorResp == null) {
			response.setStatus(404);
			pw.print("Moto was not foud.");
			pw.flush();
		} else {
			pw.print(gson.toJson(vendedorResp));
			pw.flush();

		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setStatus(202);

		StringBuffer sb = new StringBuffer();

		BufferedReader br = request.getReader();
		String atributos = null;

		while ((atributos = br.readLine()) != null) {
			sb.append(atributos);
		}

		Vendedor novoVendedor = gson.fromJson(sb.toString(), Vendedor.class);
		String vendedorResp = vendedorDao.deleteVendedor(novoVendedor.getId());
		PrintWriter pw = response.getWriter();
		if (vendedorResp == null) {
			response.setStatus(404);
			pw.print("Moto was not foud.");
			pw.flush();
		} else {
			pw.print(gson.toJson(vendedorResp));
			pw.flush();
		}
	}
}

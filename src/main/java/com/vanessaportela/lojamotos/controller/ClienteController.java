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
import com.vanessaportela.lojamotos.dao.ClienteDao;
import com.vanessaportela.lojamotos.dao.DaoFactory;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.model.Cliente;

@WebServlet(name = "ClienteController", urlPatterns = {"/cliente"})
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();

	ClienteDao clienteDao = DaoFactory.createClienteDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);

		Connection conn = DB.getConnection();
		DB.closeConnection(conn);

		PrintWriter pw = response.getWriter();

		pw.print(gson.toJson(clienteDao.findAll()));

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

		Cliente cliente = gson.fromJson(sb.toString(), Cliente.class);

		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(clienteDao.saveCliente(cliente)));

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

		Cliente cliente = gson.fromJson(sb.toString(), Cliente.class);
		Cliente clienteResp = clienteDao.updateCliente(cliente);

		PrintWriter pw = response.getWriter();

		if (clienteResp == null) {
			response.setStatus(404);
			pw.print("Moto was not foud.");
			pw.flush();
		} else {
			pw.print(gson.toJson(clienteResp));
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

		Cliente cliente = gson.fromJson(sb.toString(), Cliente.class);
		String clienteResp = clienteDao.deleteCliente(cliente.getId());
		PrintWriter pw = response.getWriter();
		if (clienteResp == null) {
			response.setStatus(404);
			pw.print("Moto was not foud.");
			pw.flush();
		} else {
			pw.print(gson.toJson(clienteResp));
			pw.flush();
		}
	}
}

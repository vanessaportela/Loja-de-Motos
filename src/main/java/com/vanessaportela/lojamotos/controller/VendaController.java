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
import com.google.gson.GsonBuilder;
import com.vanessaportela.lojamotos.dao.DaoFactory;
import com.vanessaportela.lojamotos.dao.VendaDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.model.Venda;

@WebServlet(name = "VendaController", urlPatterns = {"/venda"})
public class VendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();
	
	Gson gsons = new GsonBuilder()
		    .excludeFieldsWithoutExposeAnnotation()
		    .create();

	VendaDao vendaDao = DaoFactory.createVendaDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);

		Connection conn = DB.getConnection();
		DB.closeConnection(conn);

		PrintWriter pw = response.getWriter();
		
		int vendaId = Integer.parseInt(request.getParameter("Id"));
		
		Venda venda = vendaDao.findById(vendaId);

		if (venda == null) {
			response.setStatus(404);
			pw.print("Venda nao encontrada.");
		} else {
			pw.print(gsons.toJson(venda));
		}

		pw.flush();
	}
	
	
	/*@Override
	protected void doGetAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);

		Connection conn = DB.getConnection();
		DB.closeConnection(conn);

		PrintWriter pw = response.getWriter();

		pw.print(gson.toJson(motoDao.findAll()));

		pw.flush();
	}*/
	
	
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

		Venda venda = gson.fromJson(sb.toString(), Venda.class);

		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(vendaDao.saveVenda(venda)));

		pw.flush();
	}
	
}

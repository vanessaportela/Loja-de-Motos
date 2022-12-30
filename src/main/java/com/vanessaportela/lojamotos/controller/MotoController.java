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
import com.vanessaportela.lojamotos.dao.MotoDao;
import com.vanessaportela.lojamotos.db.DB;
import com.vanessaportela.lojamotos.model.Moto;

@WebServlet(name = "MotoController", urlPatterns = {"/moto"})
public class MotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();

	MotoDao motoDao = DaoFactory.createMotoDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);

		Connection conn = DB.getConnection();
		DB.closeConnection(conn);

		PrintWriter pw = response.getWriter();

		pw.print(gson.toJson(motoDao.findAll()));

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

		Moto novaMoto = gson.fromJson(sb.toString(), Moto.class);

		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(motoDao.saveMoto(novaMoto)));

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

		Moto novaMoto = gson.fromJson(sb.toString(), Moto.class);
		Moto motoResp = motoDao.updateMoto(novaMoto);

		PrintWriter pw = response.getWriter();

		if (motoResp == null) {
			response.setStatus(404);
			pw.print("Moto was not foud.");
			pw.flush();
		} else {
			pw.print(gson.toJson(motoResp));
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

		Moto novaMoto = gson.fromJson(sb.toString(), Moto.class);
		String motoResp = motoDao.deleteMoto(novaMoto.getId());
		PrintWriter pw = response.getWriter();
		if (motoResp == null) {
			response.setStatus(404);
			pw.print("Moto was not foud.");
			pw.flush();
		} else {
			pw.print(gson.toJson(motoResp));
			pw.flush();
		}
	}
}

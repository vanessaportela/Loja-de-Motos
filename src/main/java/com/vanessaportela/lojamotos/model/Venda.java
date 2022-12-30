package com.vanessaportela.lojamotos.model;

import java.util.HashSet;
import java.util.Set;

public class Venda {

	private int id;
	private String data;
	private Vendedor vendedor; // idvendedor
	private Cliente cliente; // idcliente
	private Set<ItemVenda> itens = new HashSet<>();
	
	public Venda() {
	
	}
	
	public Venda(int id, String data, Vendedor vendedor, Cliente cliente) {
		super();
		this.id = id;
		this.data = data;
		this.vendedor = vendedor;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Set<ItemVenda> getItems() {
		return itens;
	}

	public Double getTotal() {
		double sum = 0.0;
		for(ItemVenda x : itens) {
			sum += x.getSubTotal();
		}
		return sum;
	}

}

package com.vanessaportela.lojamotos.model;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanessaportela.lojamotos.model.pk.itemVendaPK;

public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private itemVendaPK id = new itemVendaPK();
	private int quantidade;
	private Double preco;

	public ItemVenda() {

	}

	public ItemVenda(Venda venda, Moto moto, int quantidade, Double preco) {
		super();
		id.setVenda(venda);
		id.setMoto(moto);
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	
	public Venda getVenda() {
		return id.getVenda();
	}

	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}

	public Moto getMoto() {
		return id.getMoto();
	}
	
	Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	public void setMoto(Moto moto) {
		id.setMoto(moto);
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Double getSubTotal(){
		return preco * quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

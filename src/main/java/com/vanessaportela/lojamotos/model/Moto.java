package com.vanessaportela.lojamotos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Moto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Integer id;
	@Expose
	private String marcaMoto; 
	@Expose
	private String modeloMoto; 
	@Expose
	private Integer anoMoto;
	@Expose
	private Double valor;

	private  Set<ItemVenda> itens = new HashSet<>();

	public Moto() {
		
	}

	public Moto(Integer id, String marcaMoto, String modeloMoto, Integer anoMoto, Double valor) {
		super();
		this.id = id;
		this.marcaMoto = marcaMoto;
		this.modeloMoto = modeloMoto;
		this.anoMoto = anoMoto;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarcaMoto() {
		return marcaMoto;
	}

	public void setMarcaMoto(String marcaMoto) {
		this.marcaMoto = marcaMoto;
	}

	public String getModeloMoto() {
		return modeloMoto;
	}

	public void setModeloMoto(String modeloMoto) {
		this.modeloMoto = modeloMoto;
	}

	public Integer getAnoMoto() {
		return anoMoto;
	}

	public void setAnoMoto(Integer anoMoto) {
		this.anoMoto = anoMoto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	public Set<Venda> getVenda(){
		Set<Venda> set = new HashSet<>();
		for (ItemVenda x : itens) {
			set.add(x.getVenda());
		}
		return set;
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
		Moto other = (Moto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

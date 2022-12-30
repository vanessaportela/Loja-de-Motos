package com.vanessaportela.lojamotos.model.pk;

import java.io.Serializable;

import com.vanessaportela.lojamotos.model.Moto;
import com.vanessaportela.lojamotos.model.Venda;

public class itemVendaPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Venda venda; //idvenda
	private Moto moto; //idmoto
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Moto getMoto() {
		return moto;
	}
	public void setMoto(Moto moto) {
		this.moto = moto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moto == null) ? 0 : moto.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		itemVendaPK other = (itemVendaPK) obj;
		if (moto == null) {
			if (other.moto != null)
				return false;
		} else if (!moto.equals(other.moto))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}
}

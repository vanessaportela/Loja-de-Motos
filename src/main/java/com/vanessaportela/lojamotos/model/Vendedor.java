package com.vanessaportela.lojamotos.model;

public class Vendedor extends Pessoa {

	private int id;
	private int matricula;

	public Vendedor() {

	}

	public Vendedor(int id, int matricula, String nome, String email, String telefone) {
		super(nome, email, telefone);
		this.id = id;
		this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Vendedor other = (Vendedor) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

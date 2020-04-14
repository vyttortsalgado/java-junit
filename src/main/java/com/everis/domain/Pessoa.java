package com.everis.domain;

import java.util.Date;
import com.everis.*;
import com.everis.exceptions.WithoutSobrenomeException;

public class Pessoa {
	private Long id;
	private String name;
	private String sobrenome;

	public Pessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Pessoa(Long id, String name, String sobrenome) {
		super();
		this.id = id;
		this.name = name;
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() throws Exception {
		if ( this.sobrenome.isEmpty() ) {
			throw new Exception("Sem sobrenome, não é possível completar nome");
		}
		
		return this.name + " " + this.sobrenome;
	}

	public String getNomeCompleto2() throws WithoutSobrenomeException {
		if ( this.sobrenome.isEmpty() ) {
			throw new WithoutSobrenomeException("Sem sobrenome, não é possível completar nome");
		}		
		
		return this.name + " " + this.sobrenome;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", name=" + name + "]";
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
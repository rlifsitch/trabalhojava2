package br.org.serratec.contabancaria.exceptions;

public class ContaInvalidaException extends Exception{

	private static final long serialVersionUID = -5512578975055019270L;
	private int numero;
	
	public ContaInvalidaException(Integer numero) {
		this.numero = numero;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}

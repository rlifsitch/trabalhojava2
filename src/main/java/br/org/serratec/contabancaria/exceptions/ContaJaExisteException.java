package br.org.serratec.contabancaria.exceptions;

public class ContaJaExisteException extends Exception{

	private static final long serialVersionUID = 593984821509312900L;
	private Integer numero;
	
	public ContaJaExisteException(Integer numero) {
		super();
		this.numero = numero;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}

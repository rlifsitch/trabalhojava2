package br.org.serratec.contabancaria.exceptions;

public class OperacaoInvalidaException extends Exception {

	private static final long serialVersionUID = 7177403968520259131L;
	private String tipo;

	public OperacaoInvalidaException(String tipo) {
		super();
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



}

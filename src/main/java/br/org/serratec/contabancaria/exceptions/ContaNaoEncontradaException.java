package br.org.serratec.contabancaria.exceptions;

public class ContaNaoEncontradaException extends Exception{

	private static final long serialVersionUID = 6137671496258551344L;
	private Integer id;
	
	public ContaNaoEncontradaException(Integer id) {
		this.id = id;
	}
	
	public Integer getNumero() {
		return id;
	}
	
	public void setNumero(Integer id) {
		this.id = id;
	}

}

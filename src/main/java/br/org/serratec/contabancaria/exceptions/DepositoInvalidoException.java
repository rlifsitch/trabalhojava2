package br.org.serratec.contabancaria.exceptions;

public class DepositoInvalidoException extends Exception {

	private static final long serialVersionUID = 6039938381567322161L;
	private Double valor;
	public DepositoInvalidoException(double valor) {
		super();
		this.valor = valor;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	

}

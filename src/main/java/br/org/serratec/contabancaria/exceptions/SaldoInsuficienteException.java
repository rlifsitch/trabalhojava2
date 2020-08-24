package br.org.serratec.contabancaria.exceptions;

public class SaldoInsuficienteException extends Exception{

	private static final long serialVersionUID = 5529608030842911460L;
	private Double valor;
	public SaldoInsuficienteException(Double valor) {
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
package br.org.serratec.contabancaria.exceptions;

public class ValorNegativoException extends Exception{

	private static final long serialVersionUID = -1419062807850849569L;
	private Double valor;
	public ValorNegativoException(Double valor) {
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
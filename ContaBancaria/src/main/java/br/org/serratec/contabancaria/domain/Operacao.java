package br.org.serratec.contabancaria.domain;

public class Operacao {
	private Double valor;
	private String tipo;
	
	public Operacao(Double valor, String tipo) {
		super();
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String debito) {
		this.tipo = debito;
	}


	
}

package br.org.serratec.contabancaria.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.org.serratec.contabancaria.domain.Conta;
import br.org.serratec.contabancaria.exceptions.ContaInvalidaException;
import br.org.serratec.contabancaria.exceptions.ContaJaExisteException;
import br.org.serratec.contabancaria.exceptions.ContaNaoEncontradaException;
import br.org.serratec.contabancaria.exceptions.DepositoInvalidoException;
import br.org.serratec.contabancaria.exceptions.OperacaoInvalidaException;
import br.org.serratec.contabancaria.exceptions.SaldoInsuficienteException;
import br.org.serratec.contabancaria.exceptions.ValorNegativoException;

@Service
public class ContaService {
	private Map<Integer, Conta> cache;
	
	public ContaService() {
		cache = new HashMap<Integer, Conta>();
		cache.put(200, new Conta(200, "Eric", 10000.));
		cache.put(350, new Conta(350, "Jean", 5000.));
		cache.put(5045, new Conta(5045, "Rodrigo", 2500.));
		cache.put(4250, new Conta(4250, "Luana", 3000.));
	}

	public Map<Integer, Conta> listarContas(){
		return cache;
	}
	
	public Conta recuperarIdConta(Integer numConta) throws ContaInvalidaException, ContaNaoEncontradaException{
		
		validarConta(numConta);
		Conta conta = cache.get(numConta);
		
		if(conta == null) throw new ContaNaoEncontradaException(numConta);
		return conta;
	}

	public Conta incluir(Conta conta) throws ContaJaExisteException, ContaInvalidaException {
		numeroExiste(conta.getNumero());
		cache.put(conta.getNumero(), conta);
		return conta;
		
	}
	
	public Conta atualizarConta(Integer numAntigo, Conta conta) throws ContaInvalidaException, ContaNaoEncontradaException, ContaJaExisteException {
		validarConta(numAntigo);
		validarNumero(conta.getNumero());
		if(numAntigo != conta.getNumero()) numeroExiste(conta.getNumero());		
		Conta contaOld = cache.get(numAntigo);
		cache.remove(numAntigo);
		conta.setSaldo(contaOld.getSaldo());
		cache.put(conta.getNumero(), conta);
		return conta;
		
	}

	public void apagarPorNumero(Integer numero) throws ContaInvalidaException, ContaNaoEncontradaException{
		validarConta(numero);
		cache.remove(numero);
	}
	
	private void validarConta(Integer numero) throws ContaInvalidaException, ContaNaoEncontradaException{
		validarNumero(numero);
		if(!cache.containsKey(numero)) throw new ContaNaoEncontradaException(numero);
	}
	private void validarNumero(Integer numero) throws ContaInvalidaException {
		if(numero<0) throw new ContaInvalidaException(numero);
	}
	private void numeroExiste(Integer numero) throws ContaJaExisteException, ContaInvalidaException {
		validarNumero(numero);
		if(cache.containsKey(numero)) throw new ContaJaExisteException(numero);
	}
	
	public Conta operacao(Integer numero, String tipo, Double valor) throws ContaNaoEncontradaException, DepositoInvalidoException, ValorNegativoException, SaldoInsuficienteException, ContaInvalidaException, OperacaoInvalidaException {
		validarConta(numero);
		Conta conta = cache.get(numero);		
		if(conta == null) throw new ContaNaoEncontradaException(null);
		switch(tipo.toLowerCase()) {
			case "credito":
				if(!(valor > 50)) throw new DepositoInvalidoException(valor);
				conta.setSaldo(conta.getSaldo() + valor);
				break;
			case "debito":
				if(!(valor>0)) throw new ValorNegativoException(valor);
				if(valor > conta.getSaldo()) throw new SaldoInsuficienteException(valor);
				conta.setSaldo(conta.getSaldo() - valor);
				break;
			default:
				throw new OperacaoInvalidaException(tipo);
		}
		cache.put(conta.getNumero(), conta);
		return conta;
	}	
		
	
}

package br.org.serratec.contabancaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.contabancaria.domain.Conta;
import br.org.serratec.contabancaria.exceptions.ContaInvalidaException;
import br.org.serratec.contabancaria.exceptions.ContaJaExisteException;
import br.org.serratec.contabancaria.exceptions.ContaNaoEncontradaException;
import br.org.serratec.contabancaria.exceptions.DepositoInvalidoException;
import br.org.serratec.contabancaria.exceptions.OperacaoInvalidaException;
import br.org.serratec.contabancaria.exceptions.SaldoInsuficienteException;
import br.org.serratec.contabancaria.exceptions.ValorNegativoException;
import br.org.serratec.contabancaria.services.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	//@Value("${Conta-Bancaria}")
	//private String numeroConta;
		
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<Conta> getContas(){
		HttpHeaders cabecalho = new HttpHeaders();
		cabecalho.add("Conta Bancaria",  "numero da conta");
		return new ResponseEntity(contaService.listarContas(), cabecalho, HttpStatus.OK);
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<?> getConta(@PathVariable Integer numero) throws ContaInvalidaException, ContaNaoEncontradaException {
		Conta conta = contaService.recuperarIdConta(numero);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	
	
	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody Conta conta) throws ContaJaExisteException, ContaInvalidaException {
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.incluir(conta));
	}
	
	@PutMapping("/{numAntigo}")
	public ResponseEntity<?> atualizar(@PathVariable Integer numAntigo, @RequestBody Conta conta) throws ContaInvalidaException, ContaNaoEncontradaException, ContaJaExisteException {
		Conta contaAtualizada = contaService.atualizarConta(numAntigo, conta);
		return ResponseEntity.status(HttpStatus.OK).body(contaAtualizada);
	}
	
	@PostMapping("/{numero}/{tipo}/{valor}")
	public ResponseEntity<?> operacao(@PathVariable Integer numero, @PathVariable String tipo, @PathVariable Double valor) throws ContaNaoEncontradaException, DepositoInvalidoException, ValorNegativoException, SaldoInsuficienteException, ContaInvalidaException, OperacaoInvalidaException {
		return ResponseEntity.status(HttpStatus.OK).body(contaService.operacao(numero, tipo, valor));
	}
	
	
	@DeleteMapping("/{numero}")
	public ResponseEntity<?> apagar(@PathVariable Integer numero) throws ContaInvalidaException, ContaNaoEncontradaException{
		contaService.apagarPorNumero(numero);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	

}

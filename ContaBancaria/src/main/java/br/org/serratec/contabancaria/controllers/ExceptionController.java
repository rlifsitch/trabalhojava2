package br.org.serratec.contabancaria.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.org.serratec.contabancaria.exceptions.ContaInvalidaException;
import br.org.serratec.contabancaria.exceptions.ContaJaExisteException;
import br.org.serratec.contabancaria.exceptions.ContaNaoEncontradaException;
import br.org.serratec.contabancaria.exceptions.DepositoInvalidoException;
import br.org.serratec.contabancaria.exceptions.OperacaoInvalidaException;
import br.org.serratec.contabancaria.exceptions.SaldoInsuficienteException;
import br.org.serratec.contabancaria.exceptions.ValorNegativoException;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(ContaInvalidaException.class)
	public ResponseEntity<String> trataContaInvalida(ContaInvalidaException exception){
		String msg = String.format("O numero de conta %d é invalido, favor digitar um numero maior do que 0", exception.getNumero());
		return ResponseEntity.badRequest()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "NUMERO_CONTA_INVALIDO")
				.header("Value-Erro", exception.getNumero().toString())
				.build();
	}

	@ExceptionHandler(ContaNaoEncontradaException.class)
	public ResponseEntity<String> trataContaNaoEncontrada(ContaNaoEncontradaException exception){
		String msg = String.format("A conta de numero %d não existe. Favor digitar um numero de conta valido", exception.getNumero());
		return ResponseEntity.notFound()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "NUMERO_CONTA_INEXISTENTE")
				.header("Value-Erro", exception.getNumero().toString())
				.build();
	}
	
	@ExceptionHandler(DepositoInvalidoException.class)
	public ResponseEntity<String> trataDepositoInvalido(DepositoInvalidoException exception){
		String msg = String.format("O valor %.2f é inferior ao minimo permitido de 50, favor depositar um valor maior.", exception.getValor());
		return ResponseEntity.badRequest()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "NUMERO_CONTA_INVALIDO")
				.header("Value-Erro", exception.getValor().toString())
				.build();
	}
	
	@ExceptionHandler(ValorNegativoException.class)
	public ResponseEntity<String> trataValorNegativo(ValorNegativoException exception){
		String msg = String.format("O valor %.2f é invalido, favor digitar um numero maior do que 0", exception.getValor());
		return ResponseEntity.badRequest()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "VALOR_INVALIDO")
				.header("Value-Erro", exception.getValor().toString())
				.build();
	}
	
	@ExceptionHandler(SaldoInsuficienteException.class)
	public ResponseEntity<String> trataSaldoInsuficiente(SaldoInsuficienteException exception){
		String msg = String.format("O valor %.2f é superior ao saldo da conta", exception.getValor());
		return ResponseEntity.badRequest()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "VALOR_INVALIDO")
				.header("Value-Erro", exception.getValor().toString())
				.build();
	}
	
	@ExceptionHandler(OperacaoInvalidaException.class)
	public ResponseEntity<String> trataOperacaoInvalida(OperacaoInvalidaException exception){
		String msg = String.format("Operação invalida!");
		return ResponseEntity.badRequest()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "OPERACAO_INVALIDO")
				.header("Value-Erro", exception.getTipo())
				.build();
	}
	
	@ExceptionHandler(ContaJaExisteException.class)
	public ResponseEntity<String> trataContaJaExiste(ContaJaExisteException exception){
		String msg = String.format("O numero %d já está associado a uma conta existente, favor digitar outro numero", exception.getNumero());
		return ResponseEntity.badRequest()
				.header("Msg-Erro", msg)
				.header("Codigo-Erro", "CONTA_JA_EXISTE")
				.header("Value-Erro", exception.getNumero().toString())
				.build();
	}
}

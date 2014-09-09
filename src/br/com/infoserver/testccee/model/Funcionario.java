package br.com.infoserver.testccee.model;

public class Funcionario {

	Number id;
	
	String nomeCliente;
	
	Number valorSalarioBruto;
	
	Number valorSalarioLiquido;

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Number getValorSalarioLiquido() {
		return valorSalarioLiquido;
	}

	public void setValorSalarioLiquido(Number valorSalarioLiquido) {
		this.valorSalarioLiquido = valorSalarioLiquido;
	}
	
	public Number getValorSalarioBruto() {
		return valorSalarioBruto;
	}

	public void setValorSalarioBruto(Number valorSalarioBruto) {
		this.valorSalarioBruto = valorSalarioBruto;
	}
	
	
	
}

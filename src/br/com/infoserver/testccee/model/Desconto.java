package br.com.infoserver.testccee.model;

public class Desconto {

	Funcionario funcionario;
	
	Number id;
	
	Number valorDesconto;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public Number getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Number valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	
	
}

package br.com.infoserver.testccee.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.infoserver.testccee.dao.FuncionarioDAO;
import br.com.infoserver.testccee.model.Desconto;
import br.com.infoserver.testccee.model.Funcionario;

public class FuncionarioService {
	
	FuncionarioDAO dao = new FuncionarioDAO();
	
	DescontoService descontoService = new DescontoService();
	
	public FuncionarioDAO getDao() {
		return dao;
	}

	public void setDao(FuncionarioDAO dao) {
		this.dao = dao;
	}

	public DescontoService getDescontoService() {
		return descontoService;
	}

	public void setDescontoService(DescontoService descontoService) {
		this.descontoService = descontoService;
	}

	public List<Funcionario> selectAll() throws Exception { 
	    List<Funcionario> pojos = new ArrayList<Funcionario>();
	    Collection<Funcionario> funcionarios = getDao().selectAll() ;
	
	    pojos.addAll(funcionarios);
	
	    return pojos;    
    }
    
    public List<Funcionario> selectEmployeeByNetSalary() throws Exception {
    	List<Funcionario> pojos = new ArrayList<Funcionario>();
    	Collection<Funcionario> funcionarios = this.getDao().selectAll() ;
    	List<Desconto> descontos = this.getDescontoService().selectAll();
    	
    	for (Funcionario funcionario : funcionarios)  {
    		funcionario.setValorSalarioLiquido(funcionario.getValorSalarioBruto());
    		for (Desconto desconto : descontos) {
    			if(funcionario.getId() == desconto.getFuncionario().getId()){
    				funcionario.setValorSalarioLiquido(funcionario.getValorSalarioLiquido().doubleValue() - desconto.getValorDesconto().doubleValue());
	    		}
    		}
    		pojos.add(funcionario);   		
    	}   
    	return pojos;
    }
}

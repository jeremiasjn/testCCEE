package br.com.infoserver.testccee.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.infoserver.testccee.dao.DescontoDAO;
import br.com.infoserver.testccee.model.Desconto;

public class DescontoService {
	
	DescontoDAO dao;
	   
    public List<Desconto> selectAll() throws Exception { 
	    List<Desconto> pojos = new ArrayList<Desconto>();
	    this.dao = new DescontoDAO();
	    Collection<Desconto> descontos = dao.selectAll() ;
	
	    pojos.addAll(descontos);
	
	    return pojos;    
    }

}

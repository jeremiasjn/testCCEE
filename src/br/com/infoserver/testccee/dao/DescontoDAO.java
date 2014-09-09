package br.com.infoserver.testccee.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.infoserver.testccee.model.Desconto;
import br.com.infoserver.testccee.model.Funcionario;

public class DescontoDAO {
	
	public DescontoDAO(){
		super();
	}
	
	public Collection<Desconto> selectAll() {
		Set<Desconto> results = new HashSet<Desconto>();
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from descontos");
			while (rs.next()){
				Desconto vo = new Desconto();
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id_funcionario"));
				vo.setFuncionario(funcionario);
				vo.setId(rs.getInt("id_desconto"));			
				vo.setValorDesconto(rs.getBigDecimal("vl_desconto"));
				results.add(vo);
			}
            stmt.close();
            connection.close();
			return results;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

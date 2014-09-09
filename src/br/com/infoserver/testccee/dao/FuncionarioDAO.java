package br.com.infoserver.testccee.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.infoserver.testccee.model.Funcionario;

public class FuncionarioDAO {

	public FuncionarioDAO(){
		super();
	}
	
	public Collection<Funcionario> selectAll() {
		Set<Funcionario> results = new HashSet<Funcionario>();
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from funcionario");
			while (rs.next()){
				Funcionario vo = new Funcionario();
				vo.setId(rs.getInt("id_funcionario"));
				vo.setNomeCliente(rs.getString("nm_cliente"));
				vo.setValorSalarioBruto(rs.getBigDecimal("vl_salario_bruto"));
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

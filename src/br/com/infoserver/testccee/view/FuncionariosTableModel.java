package br.com.infoserver.testccee.view;

import javax.swing.table.AbstractTableModel;

import br.com.infoserver.testccee.model.Funcionario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FuncionariosTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
    private static final int COL_ID = 0;
    private static final int COL_NOME = 1;
    private static final int COL_VL_BRUTO = 2;
    private static final int COL_VL_LIQUIDO = 3;
    
	private List<Funcionario> linhas = new ArrayList<Funcionario>();  
    private String colunas[] = new String[] {"Funcionário", "Cliente", "Valor Bruto", "Valor Liquido"};  
      
    public List<Funcionario> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<Funcionario> linhas) {
		this.linhas = linhas;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
     
    public FuncionariosTableModel(List<Funcionario> lista){ 
       this.linhas = new ArrayList<Funcionario>(lista);
       ordenarPorSalarioLiquido();  
    }  
  
    public FuncionariosTableModel() {
    	setColunas( this.colunas );
    	setLinhas ( this.linhas );  
	}

	@Override  
    public int getRowCount() {  
       // return Integer.valueOf(linhas.size()) == null ? 0 : linhas.size();  
		 return linhas.size();
    }  
  
    @Override  
    public int getColumnCount() {  
        return colunas.length;  
    }  
      
    @Override  
    public String getColumnName(int columnIndex) {  
        return colunas[columnIndex];  
    }  
      
    @Override  
    public Class<?> getColumnClass(int columnIndex) {  
        switch(columnIndex) {  
            case COL_ID: 
                return Integer.class;  
            case COL_NOME: 
                return String.class;  
            case COL_VL_BRUTO:           	
                return Double.class;
            case COL_VL_LIQUIDO:           	
                return Double.class;                
            default:  
                throw new IndexOutOfBoundsException("Índice informado fora dos limites.");  
        }  
    }  
  
    @Override  
    public Object getValueAt(int rowIndex, int columnIndex) {  
        Funcionario vo = linhas.get(rowIndex);  
        switch(columnIndex) {  
            case COL_ID:   
                return vo.getId();  
            case COL_NOME:   
                return vo.getNomeCliente();  
            case COL_VL_BRUTO:   
                return vo.getValorSalarioBruto(); 
            case COL_VL_LIQUIDO:   
                return vo.getValorSalarioLiquido();                 
            default:  
                throw new IndexOutOfBoundsException("Índice informado fora dos limites.");  
        }  
    }  
      
//    @Override  
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
//        Funcionario vo = linhas.get(rowIndex);  
//        switch(columnIndex) {  
//            case 0:   
//                vo.setId(Integer.parseInt(aValue.toString()));  
//            case 1:   
//                vo.setNomeCliente(aValue.toString());  
//            case 2:   
//                vo.setValorSalarioBruto(Double.parseDouble(aValue.toString()));  
//            case 3:   
//                vo.setValorSalarioLiquido(Double.parseDouble(aValue.toString()));                
//        }  
//        fireTableCellUpdated(rowIndex, columnIndex);  
//    }  
      
    @Override  
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        return false;  
    }  
      
    /* 
     * Implementações particulares: 
     */  
      
    public Funcionario getFuncionario(int rowIndex) {  
        return linhas.get(rowIndex);  
    }  
      
    public void addFuncionario(Funcionario vo) {  
        linhas.add(vo);  
        int lastIndex = getRowCount()-1;  
        fireTableRowsInserted(lastIndex, lastIndex);  
    }  
      
    public void updateFuncionario(Funcionario vo, int index) {  
        linhas.set(index, vo);  
        fireTableRowsUpdated(linhas.indexOf(vo), linhas.indexOf(vo));  
    }  
      
    public void removeFuncionario(int rowIndex) {  
        linhas.remove(rowIndex);  
        fireTableRowsDeleted(rowIndex, rowIndex);  
    }  
      
    public void removeFuncionario(Funcionario vo) {  
        linhas.remove(vo);  
        fireTableRowsDeleted(linhas.indexOf(vo), linhas.indexOf(vo));  
    }  
      
    public void removeFuncionario(List<Funcionario> lista) {  
        int indice = 0;  
        for(Funcionario v : lista) {  
            indice = linhas.indexOf(v);  
            linhas.remove(v);  
            fireTableRowsDeleted(indice, indice);  
        }  
    }  
      
    public void addListaFuncionarios(List<Funcionario> lista) {  
        int tamanhoAntigo = getRowCount();  
        linhas.addAll(lista);  
        fireTableRowsInserted(tamanhoAntigo, getRowCount()-1);  
    }  
      
    public void limpar() {  
        if(!linhas.isEmpty()) {  
            linhas.clear();  
            fireTableDataChanged();  
        }  
    }  
      
    public boolean isEmpty() {  
        return linhas.isEmpty();  
    }  
      
//    public void ordenarPorId() {  
//        Collections.sort(linhas, new Comparator<Funcionario>(){  
//            
//            @Override  
//            public int compare(Funcionario o1, Funcionario o2) {  
//                return o1.getId().intValue()-o2.getId().intValue();  
//            }  
//        });  
//        fireTableDataChanged();  
//    }  
//      
//    public void ordenarPorNome() {  
//        Collections.sort(linhas, new Comparator<Funcionario>() {  
//  
//            @Override  
//            public int compare(Funcionario o1, Funcionario o2) {  
//                return o1.getNomeCliente().compareTo(o2.getNomeCliente());  
//            }  
//        });  
//        fireTableDataChanged();  
//    }  
      
    public void ordenarPorSalarioLiquido() {  
        Collections.sort(this.linhas, new Comparator<Funcionario>() {  
             
            @Override  
            public int compare(Funcionario o1, Funcionario o2) {              	
            	return Double.compare(o1.getValorSalarioLiquido().doubleValue(),o2.getValorSalarioLiquido().doubleValue());            	  
            }  
        });  
        fireTableDataChanged();  
    } 
	
}

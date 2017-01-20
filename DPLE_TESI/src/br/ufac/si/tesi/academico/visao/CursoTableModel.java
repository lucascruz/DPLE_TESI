package br.ufac.si.tesi.academico.visao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.si.tesi.academico.modelo.Curso;

@SuppressWarnings("serial")
public class CursoTableModel extends AbstractTableModel {
	
	private List<Curso> lista;
	
	public CursoTableModel(List<Curso> lista) {

		this.lista = lista;
		
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}
	
	@Override
	public String getColumnName(int coluna) {

		String nome = null;
		
		switch (coluna) {
		case 0:
			nome = "Codigo";
			break;
		case 1:
			nome = "Nome";
			break;
		case 2:
			nome = "Coordenador";
			break;
			
		default:
			break;
		}
		
		return nome;
		
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		Curso curso = lista.get(linha);
		Object obj = null;
		
		switch (coluna) {
		case 0:
			obj = curso.getCodigo();
			break;
		case 1:
			obj = curso.getNome();
			break;
		case 2:
			obj = curso.getCoordenador();
			break;
		default:
			break;
		}
		
		return obj;
	}

}

package br.ufac.si.tesi.academico.visao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Professor;
import br.ufac.si.tesi.academico.modelo.Curso;

@SuppressWarnings("serial")
public class CursoCadastroEditar extends CursoCadastro {
	
	public CursoCadastroEditar(Conexao conexao, CursoConsulta janelaPai) {
		super(conexao, janelaPai);
	}

	@Override
	public void confirmar(int codigo, String nome, int coordenador) {
		
		boolean status = getControle().updateCurso(codigo, nome, coordenador);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(int codigo) throws SQLException {
		
		Professor professor;
		Curso curso;
		curso = getControle().getCurso(codigo);
				
		getCampoCodigo().setText(String.valueOf(curso.getCodigo()));
		getCampoNome().setText(curso.getNome());

		for (int i = 0; i < getCampoCoordenador().getModel().getSize(); i++) {
			professor = getCampoCoordenador().getModel().getElementAt(i);
			if (professor.getNome().equals(curso.getCoordenador())) {
				getCampoCoordenador().setSelectedItem(professor);				
			}
		}
		
		getCampoCodigo().setEnabled(false);
		
	}

}

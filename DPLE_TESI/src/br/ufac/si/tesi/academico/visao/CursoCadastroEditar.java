package br.ufac.si.tesi.academico.visao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Curso;
import br.ufac.si.tesi.academico.modelo.Professor;

@SuppressWarnings("serial")
public class CursoCadastroEditar extends CursoCadastro {

	public CursoCadastroEditar(Conexao conexao, CursoConsulta janelaPai) {
		super(conexao, janelaPai);
		setTitle("Controle Academico - Cadastro Curso / Editar");

	}

	@Override
	public void confirmar(int codigo, String nome, int coordenador) throws SQLException {
		
		boolean status = getControle().updateCurso(codigo, nome, coordenador);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(int codigo) throws SQLException {
		
		Curso curso;
		Professor professor;
		curso = getControle().getCurso(codigo);
		
		getCampoCodigo().setText(String.valueOf(curso.getCodigo()));
		getCampoNome().setText(curso.getNome());
		
		
		for (int i = 0; i < getCampoProfessor().getModel().getSize(); i++) {
			professor = getCampoProfessor().getModel().getElementAt(i);
			if (professor.getNome().equals(curso.getProfessor().getNome())) {
				getCampoProfessor().setSelectedItem(professor);				
			}
		}
		
		getCampoCodigo().setEnabled(false);
		
	}

}
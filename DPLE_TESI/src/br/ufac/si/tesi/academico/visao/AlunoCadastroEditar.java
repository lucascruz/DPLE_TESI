package br.ufac.si.tesi.academico.visao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Curso;
import br.ufac.si.tesi.academico.modelo.Aluno;

@SuppressWarnings("serial")
public class AlunoCadastroEditar extends AlunoCadastro {
	
	public AlunoCadastroEditar(Conexao conexao, AlunoConsulta janelaPai) {
		super(conexao, janelaPai);
		setTitle("Controle Academico - Cadastro Aluno / Editar");

	}

	@Override
	public void confirmar(int matricula, String nome, String telefone, String endereco, String cep, String email, String sexo,  boolean pne, int curso_codigo) {
		
		boolean status = false;
		try {
			status = getControle().updateAluno(matricula, nome, telefone, endereco, cep, email,sexo, pne, curso_codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(int matricula) {
		
		Curso curso;
		Aluno aluno = null;
		try {
			aluno = getControle().getAluno(matricula);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getCampoMatricula().setText(String.valueOf(aluno.getMatricula()));
		getCampoNome().setText(aluno.getNome());
		getCampoSexo();
		getCampoTelefone().setText(aluno.getFone());
		getCampoEndereco().setText(aluno.getEndereco());
		getCampoCep().setText(aluno.getCep());
		getCampoEmail().setText(aluno.getEmail());
		getCampoPne().setSelected(aluno.isPne());

		for (int i = 0; i < getCampoCurso().getModel().getSize(); i++) {
			curso = getCampoCurso().getModel().getElementAt(i);
			if (curso.getNome().equals(aluno.getCurso())) {
				getCampoCurso().setSelectedItem(curso);				
			}
		}
		
		getCampoMatricula().setEnabled(false);
		
	}

}

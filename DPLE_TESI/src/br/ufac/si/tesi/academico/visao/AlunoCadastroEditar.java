package br.ufac.si.tesi.academico.visao;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;
import br.ufac.si.tesi.academico.modelo.Aluno;

public class AlunoCadastroEditar extends AlunoCadastro {
	
	public AlunoCadastroEditar(Conexao conexao, AlunoConsulta janelaPai) {
		super(conexao, janelaPai);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void confirmar(int matricula, String nome, int rg, long cpf, String telefone, String endereco, String cep, String email, boolean substituto, String curso_sigla) {
		
		boolean status = getControle().updateAluno(matricula, nome, rg, cpf, telefone, endereco, cep, email, substituto, curso_sigla);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(int matricula) {
		
		Centro curso;
		Aluno aluno;
		aluno = getControle().getAluno(matricula);
		
		getCampoMatricula().setText(String.valueOf(aluno.getMatricula()));
		getCampoNome().setText(aluno.getNome());
		getCampoRg().setText(String.valueOf(aluno.getRg()));
		getCampoCpf().setText(String.valueOf(aluno.getCpf()));
		getCampoTelefone().setText(aluno.getTelefone());
		getCampoEndereco().setText(aluno.getEndereco());
		getCampoCep().setText(aluno.getCep());
		getCampoEmail().setText(aluno.getEmail());
		getCampoSubstituto().setSelected(aluno.isSubstituto());

		for (int i = 0; i < getCampoCentro().getModel().getSize(); i++) {
			curso = getCampoCentro().getModel().getElementAt(i);
			if (curso.getNome().equals(aluno.getCentro().getNome())) {
				getCampoCentro().setSelectedItem(curso);				
			}
		}
		
		getCampoMatricula().setEnabled(false);
		
	}

}

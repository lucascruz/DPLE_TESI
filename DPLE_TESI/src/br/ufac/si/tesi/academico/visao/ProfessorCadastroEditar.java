package br.ufac.si.tesi.academico.visao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;
import br.ufac.si.tesi.academico.modelo.Professor;

public class ProfessorCadastroEditar extends ProfessorCadastro {
	
	public ProfessorCadastroEditar(Conexao conexao, ProfessorConsulta janelaPai) {
		super(conexao, janelaPai);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void confirmar(int matricula, String nome, int rg, long cpf, String telefone, String endereco, String cep, String email, boolean substituto, String centro_sigla) {
		
		boolean status = false;
		try {
			status = getControle().updateProfessor(matricula, nome, rg, cpf, telefone, endereco, cep, email, substituto, centro_sigla);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(int matricula) {
		
		Centro centro;
		Professor professor = null;
		try {
			professor = getControle().getProfessor(matricula);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		getCampoMatricula().setText(String.valueOf(professor.getMatricula()));
		getCampoNome().setText(professor.getNome());
		getCampoRg().setText(String.valueOf(professor.getRg()));
		getCampoCpf().setText(String.valueOf(professor.getCpf()));
		getCampoTelefone().setText(professor.getTelefone());
		getCampoEndereco().setText(professor.getEndereco());
		getCampoCep().setText(professor.getCep());
		getCampoEmail().setText(professor.getEmail());
		getCampoSubstituto().setSelected(professor.isSubstituto());

		for (int i = 0; i < getCampoCentro().getModel().getSize(); i++) {
			centro = getCampoCentro().getModel().getElementAt(i);
			if (centro.getNome().equals(professor.getCentro().getNome())) {
				getCampoCentro().setSelectedItem(centro);				
			}
		}
		
		getCampoMatricula().setEnabled(false);
		
	}

}

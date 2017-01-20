package br.ufac.si.tesi.academico.visao;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;
import br.ufac.si.tesi.academico.modelo.Disciplina;

public class DisciplinaCadastroEditar extends DisciplinaCadastro {
	
	public DisciplinaCadastroEditar(Conexao conexao, DisciplinaConsulta janelaPai) {
		super(conexao, janelaPai);
	}

	@Override
	public void confirmar(String codigo, String nome, int ch, String  centro_sigla) {
		
		boolean status = getControle().updateDisciplina(codigo, nome, ch, centro_sigla);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(int codigo) {
		
		Centro centro;;
		Disciplina disciplina;
		disciplina = getControle().getDisciplina(codigo);
		
		getCampoCodigo().setText(String.valueOf(disciplina.getCodigo()));
		getCampoNome().setText(disciplina.getNome());
		getCampoCh().setText(String.valueOf(disciplina.getCh()));


		for (int i = 0; i < getCampoCentro().getModel().getSize(); i++) {
			centro = getCampoCentro().getModel().getElementAt(i);
			if (centro.getNome().equals(disciplina.getCentro().getNome())) {
				getCampoCentro().setSelectedItem(centro);				
			}
		}
		
		getCampoCodigo().setEnabled(false);
		
	}

}
package br.ufac.si.tesi.academico.visao;

import javax.swing.JOptionPane;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;

public class CentroCadastroEditar extends CentroCadastro {
	
	public CentroCadastroEditar(Conexao conexao, CentroConsulta janelaPai) {
		super(conexao, janelaPai);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void confirmar(String sigla, String nome) {
		
		boolean status = getControle().updateCentro(sigla, nome);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na atualização do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void carregar(String sigla) {
		
		Centro centro;
		centro = getControle().getCentro(sigla);
		getCampoSigla().setText(centro.getSigla());
		getCampoNome().setText(centro.getNome());
		getCampoSigla().setEnabled(false);
		
	}

}

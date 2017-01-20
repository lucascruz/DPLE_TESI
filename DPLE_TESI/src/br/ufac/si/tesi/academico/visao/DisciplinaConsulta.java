package br.ufac.si.tesi.academico.visao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ufac.si.tesi.academico.controle.DisciplinaControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Disciplina;

@SuppressWarnings("serial")
public class DisciplinaConsulta extends JFrame implements ActionListener {
	
	private JTable tblDisciplina;
	private DisciplinaControle controle;
	private Conexao conexao;
	private List<Disciplina> lista;
	private JComboBox<String> comboCampos;
	private JTextField tfStringBusca;
	private JButton botaoInserir, botaoBuscar, botaoExcluir, botaoEditar;
	private JPanel painelBotoes, painelBusca;
	private DisciplinaCadastro janelaCadastro;
	private DisciplinaCadastroEditar janelaEditar;
	
	public DisciplinaConsulta(Conexao cnx) {

		//Teste
				this.conexao = cnx;
		setSize(400,300);
		setLocationRelativeTo(null);
		
		//conexao = new Conexao();
		//conexao.conecte();
		
		janelaCadastro = new DisciplinaCadastro(conexao, this);
		janelaEditar = new DisciplinaCadastroEditar(conexao, this);
		
		controle = new DisciplinaControle(conexao);
		try {
			lista = controle.getDisciplinas();
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		painelBusca = new JPanel(new BorderLayout());
		comboCampos = new JComboBox<String>(new String[]{"Codigo","Nome"});
		tfStringBusca = new JTextField();
		botaoBuscar = new JButton("Buscar");
		painelBusca.add(comboCampos, BorderLayout.WEST);
		painelBusca.add(tfStringBusca);
		painelBusca.add(botaoBuscar, BorderLayout.EAST);
		botaoBuscar.addActionListener(this);
		
		tblDisciplina = new JTable();
		tblDisciplina.setModel(new DisciplinaTableModel(lista));
		
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		botaoInserir = new JButton("Inserir");
		painelBotoes.add(botaoInserir);
		botaoInserir.addActionListener(this);
		botaoEditar = new JButton("Editar");
		painelBotoes.add(botaoEditar);
		botaoEditar.addActionListener(this);
		botaoExcluir = new JButton("Excluir");
		painelBotoes.add(botaoExcluir);
		botaoExcluir.addActionListener(this);
		
		add(painelBusca, BorderLayout.NORTH);
		add(new JScrollPane(tblDisciplina));
		add(painelBotoes, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botaoInserir) {
			janelaCadastro.setVisible(true);
		} else if (e.getSource() == botaoBuscar) {
			buscar();
		} else if (e.getSource() == botaoExcluir) {
			int resposta = JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Alerta", JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				int linhaSelecionada = tblDisciplina.getSelectedRow();
				int matricula = Integer.parseInt(tblDisciplina.getModel().getValueAt(linhaSelecionada, 0).toString());
				boolean status = controle.deleteDisciplina(matricula);
				if (status) {
					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog(this, "Falha na exclusão do registro!", "Status", JOptionPane.ERROR_MESSAGE);
				}
			}
			buscar();
		} else if (e.getSource() == botaoEditar) {
			int linhaSelecionada = tblDisciplina.getSelectedRow();
			int matricula = Integer.parseInt(tblDisciplina.getModel().getValueAt(linhaSelecionada, 0).toString());
			janelaEditar.carregar(matricula);
			janelaEditar.setVisible(true);
		}
		
	}

	public void buscar() {
		
		lista.clear();
		String stringBusca = tfStringBusca.getText();
		if (stringBusca.isEmpty()) {
			try {
				lista = controle.getDisciplinas();
			} catch (SQLException e) {
				System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
			}
		} else if (comboCampos.getSelectedIndex() == 0) {
			try {
				lista.add(controle.getDisciplina(Integer.parseInt(stringBusca)));
			}catch (SQLException e) {
				System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
			}
		} else {
			try {
				lista = controle.getDisciplinas(stringBusca);
			} catch (SQLException e) {
				System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
			}
		}
		tblDisciplina.setModel(new DisciplinaTableModel(lista));
		
	}

}

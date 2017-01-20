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

import br.ufac.si.tesi.academico.controle.ProfessorControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Professor;

public class ProfessorConsulta extends JFrame implements ActionListener {
	
	private JTable tblProfessor;
	private ProfessorControle controle;
	private Conexao conexao;
	private List<Professor> lista;
	private JComboBox<String> comboCampos;
	private JTextField tfStringBusca;
	private JButton botaoInserir, botaoBuscar, botaoExcluir, botaoEditar;
	private JPanel painelBotoes, painelBusca;
	private ProfessorCadastro janelaCadastro;
	private ProfessorCadastroEditar janelaEditar;
	
	public ProfessorConsulta() {

		setSize(400,300);
		setLocationRelativeTo(null);
		
		conexao = new Conexao();
		conexao.conecte();
		
		janelaCadastro = new ProfessorCadastro(conexao, this);
		janelaEditar = new ProfessorCadastroEditar(conexao, this);
		
		controle = new ProfessorControle(conexao);
		try {
			lista = controle.getProfessores();
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		painelBusca = new JPanel(new BorderLayout());
		comboCampos = new JComboBox<String>(new String[]{"Matrícula","Nome"});
		tfStringBusca = new JTextField();
		botaoBuscar = new JButton("Buscar");
		painelBusca.add(comboCampos, BorderLayout.WEST);
		painelBusca.add(tfStringBusca);
		painelBusca.add(botaoBuscar, BorderLayout.EAST);
		botaoBuscar.addActionListener(this);
		
		tblProfessor = new JTable();
		tblProfessor.setModel(new ProfessorTableModel(lista));
		
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
		add(new JScrollPane(tblProfessor));
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
				int linhaSelecionada = tblProfessor.getSelectedRow();
				int matricula = Integer.parseInt(tblProfessor.getModel().getValueAt(linhaSelecionada, 0).toString());
				boolean status = controle.deleteProfessor(matricula);
				if (status) {
					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog(this, "Falha na exclusão do registro!", "Status", JOptionPane.ERROR_MESSAGE);
				}
			}
			buscar();
		} else if (e.getSource() == botaoEditar) {
			int linhaSelecionada = tblProfessor.getSelectedRow();
			int matricula = Integer.parseInt(tblProfessor.getModel().getValueAt(linhaSelecionada, 0).toString());
			janelaEditar.carregar(matricula);
			janelaEditar.setVisible(true);
		}
		
	}

	public void buscar() {
		
		lista.clear();
		String stringBusca = tfStringBusca.getText();
		if (stringBusca.isEmpty()) {
			try {
				lista = controle.getProfessores();
			} catch (SQLException e) {
				System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
			}
		} else if (comboCampos.getSelectedIndex() == 0) {
			try {
				lista.add(controle.getProfessor(Integer.parseInt(stringBusca)));
			} catch (SQLException e) {
				System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
			}
		} else {
			try {
				lista = controle.getProfessores(stringBusca);
			} catch (SQLException e) {
				System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
			}
		}
		tblProfessor.setModel(new ProfessorTableModel(lista));
		
	}

}

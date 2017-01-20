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

import br.ufac.si.tesi.academico.controle.CursoControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Curso;

public class CursoConsulta extends JFrame implements ActionListener {
	
	private JTable tblCurso;
	private CursoControle controle;
	private Conexao conexao;
	private List<Curso> lista;
	private JComboBox<String> comboCampos;
	private JTextField tfStringBusca;
	private JButton botaoInserir, botaoBuscar, botaoExcluir, botaoEditar;
	private JPanel painelBotoes, painelBusca;
	private CursoCadastro janelaCadastro;
	private CursoCadastroEditar janelaEditar;
	
	public CursoConsulta() {

		setSize(400,300);
		setLocationRelativeTo(null);
		
		conexao = new Conexao();
		conexao.conecte();
		
		janelaCadastro = new CursoCadastro(conexao, this);
		janelaEditar = new CursoCadastroEditar(conexao, this);
		
		controle = new CursoControle(conexao);
		lista = controle.getCursos();
		
		painelBusca = new JPanel(new BorderLayout());
		comboCampos = new JComboBox<String>(new String[]{"Codigo","Nome"});
		tfStringBusca = new JTextField();
		botaoBuscar = new JButton("Buscar");
		painelBusca.add(comboCampos, BorderLayout.WEST);
		painelBusca.add(tfStringBusca);
		painelBusca.add(botaoBuscar, BorderLayout.EAST);
		botaoBuscar.addActionListener(this);
		
		tblCurso = new JTable();
		tblCurso.setModel(new CursoTableModel(lista));
		
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
		add(new JScrollPane(tblCurso));
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
				int linhaSelecionada = tblCurso.getSelectedRow();
				int matricula = Integer.parseInt(tblCurso.getModel().getValueAt(linhaSelecionada, 0).toString());
				boolean status = controle.deleteCurso(matricula);
				if (status) {
					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog(this, "Falha na exclusão do registro!", "Status", JOptionPane.ERROR_MESSAGE);
				}
			}
			buscar();
		} else if (e.getSource() == botaoEditar) {
			int linhaSelecionada = tblCurso.getSelectedRow();
			int matricula = Integer.parseInt(tblCurso.getModel().getValueAt(linhaSelecionada, 0).toString());
			try {
				janelaEditar.carregar(matricula);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			janelaEditar.setVisible(true);
		}
		
	}

	public void buscar() {
		
		lista.clear();
		String stringBusca = tfStringBusca.getText();
		if (stringBusca.isEmpty()) {
			lista = controle.getCursos();
		} else if (comboCampos.getSelectedIndex() == 0) {
			lista.add(controle.getCurso(Integer.parseInt(stringBusca)));
		} else {
			lista = controle.getCursos(stringBusca);
		}
		tblCurso.setModel(new CursoTableModel(lista));
		
	}

}

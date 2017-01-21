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

@SuppressWarnings("serial")
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
	
	public CursoConsulta(Conexao cnx) {

		//Teste
				this.conexao = cnx;
		setSize(400,300);
		setLocationRelativeTo(null);
		setTitle("Controle Academico - Consultar Curso");

		
		//conexao = new Conexao();
		//conexao.conecte();
		
		janelaCadastro = new CursoCadastro(conexao, this);
		janelaEditar = new CursoCadastroEditar(conexao, this);
		
		controle = new CursoControle(conexao);
		try {
			lista = controle.getCursos();
		} catch (SQLException e) {
			e.printStackTrace();		}
		
		painelBusca = new JPanel(new BorderLayout());
		comboCampos = new JComboBox<String>(new String[]{"Sigla","Nome"});
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
			try {
				buscar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == botaoExcluir) {
			if(tblCurso.getSelectedRow() ==-1){
				JOptionPane.showMessageDialog(null,"Selecione um Curso para excluir! ", "Atençao", JOptionPane.INFORMATION_MESSAGE);
			}else{
			int resposta = JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Alerta", JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				int linhaSelecionada = tblCurso.getSelectedRow();
				int codigo = Integer.parseInt(tblCurso.getModel().getValueAt(linhaSelecionada, 0).toString());
				boolean status = false;
				try {
					status = controle.deleteCurso(codigo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (status) {
					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog(this, "Falha na exclusão do registro!", "Status", JOptionPane.ERROR_MESSAGE);
				}
			}
			try {
				buscar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		} else if (e.getSource() == botaoEditar) {
			if(tblCurso.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null,"Selecione um Curso para editar! ", "Atençao", JOptionPane.INFORMATION_MESSAGE);
			}else{
			int linhaSelecionada = tblCurso.getSelectedRow();
			int codigo = Integer.parseInt(tblCurso.getModel().getValueAt(linhaSelecionada, 0).toString());
			try {
				janelaEditar.carregar(codigo);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			janelaEditar.setVisible(true);
		}
		}
		
	}

	public void buscar() throws SQLException {
		
		lista.clear();
		String stringBusca = tfStringBusca.getText();
		if (stringBusca.isEmpty()) {
			lista = controle.getCursos();
		} else if (comboCampos.getSelectedIndex() == 0) {
			try {
				lista.add(controle.getCurso(Integer.parseInt(stringBusca)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			lista = controle.getDisciplinas(stringBusca);
		}
		tblCurso.setModel(new CursoTableModel(lista));
		
	}

}

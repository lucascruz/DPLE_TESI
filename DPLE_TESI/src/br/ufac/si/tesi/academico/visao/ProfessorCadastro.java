package br.ufac.si.tesi.academico.visao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufac.si.tesi.academico.controle.CentroControle;
import br.ufac.si.tesi.academico.controle.ProfessorControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;

public class ProfessorCadastro extends JFrame implements ActionListener {
	
	private JLabel lblMatricula, lblNome, lblRg, lblCpf, lblTelefone, lblEndereco, lblCep, lblEmail, lblSubstituto, lblCentro;
	private JButton botaoConfirmar, botaoCancelar;
	private JTextField campoMatricula, campoNome, campoRg, campoCpf, campoTelefone, campoEndereco, campoCep, campoEmail;
	private JComboBox<Centro> campoCentro;
	private JCheckBox campoSubstituto;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	
	private CentroControle centroControle;
	private ProfessorControle controle;
	private ProfessorConsulta janelaPai;
	
	public ProfessorCadastro(Conexao conexao, ProfessorConsulta janelaPai) {
		
		controle = new ProfessorControle(conexao);
		centroControle = new CentroControle(conexao);
		
		this.janelaPai = janelaPai;

		setSize(300,400);
		setLocationRelativeTo(null);
		
		painelRotulos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		lblMatricula = new JLabel("Matricula:");
		lblNome = new JLabel("Nome:");
		lblRg = new JLabel("RG:");
		lblCpf = new JLabel("CPF:");
		lblTelefone = new JLabel("Telefone:");
		lblEndereco = new JLabel("Endereço:");
		lblCep = new JLabel("CEP:");
		lblEmail = new JLabel("E-mail:");
		lblSubstituto = new JLabel("Substituto:");
		lblCentro = new JLabel("Centro:");
		
		painelRotulos.add(lblMatricula);
		painelRotulos.add(lblNome);
		painelRotulos.add(lblRg);
		painelRotulos.add(lblCpf);
		painelRotulos.add(lblTelefone);
		painelRotulos.add(lblEndereco);
		painelRotulos.add(lblCep);
		painelRotulos.add(lblEmail);
		painelRotulos.add(lblSubstituto);
		painelRotulos.add(lblCentro);
		
		painelCampos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		campoMatricula = new JTextField();
		campoNome = new JTextField();
		campoRg = new JTextField();
		campoCpf = new JTextField();
		campoTelefone = new JTextField();
		campoEndereco = new JTextField();
		campoCep = new JTextField();
		campoEmail = new JTextField();
		campoSubstituto = new JCheckBox();
		campoCentro = new JComboBox(centroControle.getCentros().toArray());
		
		painelCampos.add(campoMatricula);
		painelCampos.add(campoNome);
		painelCampos.add(campoRg);
		painelCampos.add(campoCpf);
		painelCampos.add(campoTelefone);
		painelCampos.add(campoEndereco);
		painelCampos.add(campoCep);
		painelCampos.add(campoEmail);
		painelCampos.add(campoSubstituto);
		painelCampos.add(campoCentro);
		
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		botaoConfirmar = new JButton("Confirmar");
		botaoCancelar = new JButton("Cancelar");
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoCancelar);
		
		botaoConfirmar.addActionListener(this);
		botaoCancelar.addActionListener(this);
		
		add(painelRotulos, BorderLayout.WEST);
		add(painelCampos);
		add(painelBotoes, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botaoConfirmar) {
			
			int matricula = Integer.parseInt(campoMatricula.getText());
			String nome = campoNome.getText();
			int rg = Integer.parseInt(campoRg.getText());
			long cpf = Long.parseLong(campoCpf.getText());
			String telefone = campoTelefone.getText();
			String endereco = campoEndereco.getText();
			String cep = campoCep.getText();
			String email = campoEmail.getText();
			boolean substituto = campoSubstituto.isSelected();
			Centro centro = (Centro) campoCentro.getSelectedItem();
			String centro_sigla = centro.getSigla();
			
//			Professor centro = new Professor();
//			centro.setMatricula(matricula);
//			centro.setNome(nome);
			
			confirmar(matricula, nome, rg, cpf, telefone, endereco, cep, email, substituto, centro_sigla);
			
		} else if (e.getSource() == botaoCancelar) {
			janelaPai.buscar();
			this.dispose();
		}
		
	}
	
	public void confirmar(int matricula, String nome, int rg, long cpf, String telefone, String endereco, String cep, String email, boolean substituto, String centro_sigla) {
		
		boolean status = controle.insertProfessor(matricula, nome, rg, cpf, telefone, endereco, cep, email, substituto, centro_sigla);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public ProfessorControle getControle() {
		
		return controle;
		
	}

	public JTextField getCampoMatricula() {
		return campoMatricula;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

	public JTextField getCampoRg() {
		return campoRg;
	}

	public JTextField getCampoCpf() {
		return campoCpf;
	}

	public JTextField getCampoTelefone() {
		return campoTelefone;
	}

	public JTextField getCampoEndereco() {
		return campoEndereco;
	}

	public JTextField getCampoCep() {
		return campoCep;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JComboBox<Centro> getCampoCentro() {
		return campoCentro;
	}

	public JCheckBox getCampoSubstituto() {
		return campoSubstituto;
	}

}

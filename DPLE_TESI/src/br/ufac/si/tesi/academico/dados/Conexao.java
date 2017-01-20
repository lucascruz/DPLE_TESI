package br.ufac.si.tesi.academico.dados;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private Connection conexao;
	private boolean conectado = false;
	private Statement smt;
	private static final String urlDB = "jdbc:mysql://localhost/academico?useSSL=false";
	
	public void conecte(String u, String s) {
		String usuario = u;
		String senha = s;

		try {
			conexao = DriverManager.getConnection(urlDB, usuario, senha);
			conectado = true;
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
	}
	
	public void desconecte() {

		try {
			conexao.close();
			conectado = false;
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}

	}
	
	public int atualize(String sql) {
		
		int status = 0;
		
		try {
			smt = conexao.createStatement();
			status = smt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return status;
		
	}
	
	public ResultSet consulte(String sql) {
		
		ResultSet rs = null;
		
		try {
			smt = conexao.createStatement();
			rs = smt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return rs;
		
	}
	
	public boolean estaConectado() {
		
		return conectado;
		
	}

}

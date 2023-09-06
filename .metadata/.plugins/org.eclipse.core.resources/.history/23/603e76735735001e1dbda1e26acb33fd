package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
     // Variaveis para configurar o banco de dados
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.44.220:3306/dbsistema";
	private String user = "root";
	private String password = "123@senac";
	// Criação de um objeto para us da classe Connection(JDBC)
	private Connection con;
	/**
	 * Método responsavel por abrir a conexão
	 * @return con
	 */
	
	public Connection conectar() {
		//tratamento de exceções
		try {
			// as linhas abaixo abrem a conexão com o banco
			Class.forName(driver);
			con = DriverManager.getConnection(url , user , password);
			return con;
			
		} catch (Exception e) {
      System.out.println(e);		
     return null;
		
		}
	}
}

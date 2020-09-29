package evelyn.br.com.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usuário
	private static final String USERNAME="root";
	
	//Senha do banco
	private static final String PASSWORD="";
	
	//caminho banco de dados, porta, nome do banco
	private static final String DATABASE_URL="jdbc:mysql://localhost:3307/crud_java";
	
	/*
	 * Conexão com o banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection=DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//recuperar uma conexão com o banco de dados
		Connection conn=createConnectionToMySQL();
		
		//testar se a conexão é nula
		if(conn!=null) {
			System.out.println("Conexão obtida com sucesso!");
			conn.close();
		}
		
		
		
		
	}

}

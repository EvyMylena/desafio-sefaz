package evelyn.br.com.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usu�rio
	private static final String USERNAME="root";
	
	//Senha do banco
	private static final String PASSWORD="";
	
	//caminho banco de dados, porta, nome do banco
	private static final String DATABASE_URL="jdbc:mysql://localhost:3307/crud_java";
	
	/*
	 * Conex�o com o banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection=DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//recuperar uma conex�o com o banco de dados
		Connection conn=createConnectionToMySQL();
		
		//testar se a conex�o � nula
		if(conn!=null) {
			System.out.println("Conex�o obtida com sucesso!");
			conn.close();
		}
		
		
		
		
	}

}

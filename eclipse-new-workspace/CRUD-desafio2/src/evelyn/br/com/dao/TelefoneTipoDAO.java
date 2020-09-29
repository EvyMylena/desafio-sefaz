package evelyn.br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import evelyn.br.com.factory.ConnectionFactory;
import evelyn.br.com.modelo.TelefoneTipo;

public class TelefoneTipoDAO {
	Connection conn=null;
	PreparedStatement statement=null;
	
	public List<TelefoneTipo> listarTelefoneTipo() throws Exception {

		ResultSet rset = null;
		List<TelefoneTipo> listaProdutos = new ArrayList<>();
		
		String sql=null;
		conn=ConnectionFactory.createConnectionToMySQL();
		
		try {
			sql = "SELECT * FROM telefone_tipo";
			statement=(PreparedStatement) conn.prepareStatement(sql);
			
			while(rset.next()) {
				TelefoneTipo teltip = new TelefoneTipo();
				teltip.setId_telefone_tipo(rset.getInt(1));
				teltip.setNome(rset.getString(2));
				
				listaProdutos.add(teltip);	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//fechar conexões
			try {
				if(statement!=null) {
					statement.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaProdutos;
		
	}	
	
	public List<TelefoneTipo> listarTelefoneTipoPorID(int id_telefone_tipo) throws Exception {
		ResultSet rset = null;
		List<TelefoneTipo> arrayTelefoneTipo = new ArrayList<>();
		
		String sql = null;
		conn=ConnectionFactory.createConnectionToMySQL();

		try {
			sql = "SELECT * FROM telefone_tipo WHERE id_telefone_tipo =?";
			statement=(PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, id_telefone_tipo);
			
			rset = statement.executeQuery();
			
			if(rset.next()) {
				TelefoneTipo tt=new TelefoneTipo();
				
				tt.setId_telefone_tipo(rset.getInt(1));
				tt.setNome(rset.getString(2));
				
				arrayTelefoneTipo.add(tt);

			}
			rset.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(statement!=null) {
					statement.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return arrayTelefoneTipo;
	}
	
	private Connection conn() throws Exception{
		return ConnectionFactory.createConnectionToMySQL();
	}
}

package evelyn.br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import evelyn.br.com.factory.ConnectionFactory;
import evelyn.br.com.modelo.UsuarioTelefone;


public class UsuarioTelefoneDAO {
	Connection conn=null;
	PreparedStatement statement=null;
	
	public void inserirUsuarioTelefone(UsuarioTelefone usuarioTelefone) throws Exception {
		String sql = null;
		conn=ConnectionFactory.createConnectionToMySQL();
		
		try {
			conn.setAutoCommit(false);
			sql = "INSERT INTO usuario_telefone(id_usuario, ddd, numero_telefone, id_telefone_tipo) VALUES(?, ?, ?, ?)";
			
			statement = (PreparedStatement) conn.prepareStatement(sql);
			
			statement.setInt(1, usuarioTelefone.getId_usuario());
			statement.setInt(2, usuarioTelefone.getDdd());
			statement.setString(3, usuarioTelefone.getNumero_telefone());
			statement.setInt(4, usuarioTelefone.getId_telefone_tipo());
			
			
			conn.commit();
			statement.close();
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
	}
	
	public List<UsuarioTelefone> listarUsuarioTelefone(int id_usuario) throws Exception {
		
		ResultSet rset = null;
		List<UsuarioTelefone> arrayUsuarioTelefone = new ArrayList<>();
		
		String sql = null;
		conn=ConnectionFactory.createConnectionToMySQL();

		try {
			sql = "SELECT * FROM usuario_telefone WHERE id_usuario=?";
			statement = (PreparedStatement) conn.prepareStatement(sql);
			statement.setInt(1, id_usuario);

			rset = statement.executeQuery();
			

			while(rset.next()) {
				UsuarioTelefone utel = new UsuarioTelefone();
				utel.setId_usuario(rset.getInt(1));
				utel.setDdd(rset.getInt(2));
				utel.setNumero_telefone(rset.getString(3));
				utel.setId_telefone_tipo(rset.getInt(4));
				arrayUsuarioTelefone.add(utel);
				
			}

			rset.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
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

		return arrayUsuarioTelefone;
	}
	public void deletarTodosOsNumerosDoUsuario(int id_usuario) throws Exception {
		String sql = null;
		conn=ConnectionFactory.createConnectionToMySQL();

		try {
			conn.setAutoCommit(false);
			sql = "DELETE FROM usuario_telefone WHERE id_usuario = ?";
			
			statement = (PreparedStatement) conn.prepareStatement(sql);
			
			statement.setInt(1, id_usuario);

			conn.commit();
			
		}
		catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}finally{
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
	}
	
	private Connection conn() throws Exception{
		return ConnectionFactory.createConnectionToMySQL();
	}

}

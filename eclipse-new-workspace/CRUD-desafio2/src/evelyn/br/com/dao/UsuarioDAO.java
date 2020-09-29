package evelyn.br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import evelyn.br.com.factory.ConnectionFactory;
import evelyn.br.com.modelo.Usuario;

public class UsuarioDAO {
	/*
	 * CRUD
	 */
	
	//inserir usuario
	public long inserir(Usuario usuario) {
		
		long id_gerado=0;
		
		String sql="INSERT INTO usuario(id_usuario, nome, email, senha) VALUES (?, ?, ?, ?)";
		
		Connection conn=null;
		PreparedStatement statement=null;
		
		try {
			//criar conexão com banco de dados
			conn=ConnectionFactory.createConnectionToMySQL();
			
			statement=(PreparedStatement) conn.prepareStatement(sql);
			//adicionar os valores esperados pela query
			statement.setString(1, null);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			
			//executar a query
			statement.execute();
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
		return id_gerado;
		
	}

	//alterar usuario
	public void alterar(Usuario usuario) throws SQLException{
		String sql="UPDATE usuario SET nome=?, email=?, senha=? WHERE id_usuario=?";
		
		Connection conn=null;
		PreparedStatement statement=null;
		
		try {
			conn=ConnectionFactory.createConnectionToMySQL();
			statement=(PreparedStatement) conn.prepareStatement(sql);
			
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			statement.setInt(4, usuario.getId_usuario());
			
			statement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	
	//deletar usuário
	public void deletar(int id_usuario) throws SQLException{
		String sql="DELETE FROM usuario WHERE id_usuario=?";
		
		Connection conn=null;
		PreparedStatement statement=null;
		
		try {
			conn=ConnectionFactory.createConnectionToMySQL();
			statement=(PreparedStatement) conn.prepareStatement(sql);
			
			statement.setInt(1, id_usuario);
			
			statement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	
	
	//listar usuários
	public List<Usuario> getUsuarios() throws SQLException{
		
		List<Usuario> usuarios=new ArrayList<Usuario>();
		
		Connection conn=null;
		PreparedStatement statement=null;
		
		//classe que vai recuperar os dados do banco
		ResultSet rset=null;
		
		try {
			
			String sql = "SELECT * FROM usuario ORDER BY nome ASC";
			conn=ConnectionFactory.createConnectionToMySQL();
			
			statement=conn.prepareStatement(sql);
			rset=statement.executeQuery(sql);
			
			while (rset.next()) {
				Usuario usuario=new Usuario();
				
				//recuperar id
				usuario.setId_usuario(rset.getInt(1));
				//recuperar nome
				usuario.setNome(rset.getString(2));
				//recuperar email
				usuario.setEmail(rset.getString(3));
				//recuperar senha
				usuario.setSenha(rset.getString(4));
				
				usuarios.add(usuario);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null) {
					rset.close();
				}
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
		return usuarios;
		
	}
	
	//receber determinado produto
		public Usuario listarUsuario(int id_usuario) throws SQLException, Exception {
			ResultSet resultSet = null;
			Connection conn=null;
			PreparedStatement statement=null;
			Usuario usuario=new Usuario();

			String sql = null;

			try {
				conn=ConnectionFactory.createConnectionToMySQL();
				
				sql = "SELECT * FROM usuario WHERE id_usuario =?";
				statement=conn.prepareStatement(sql);
				statement.setInt(1, id_usuario);
				
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {				
					usuario.setId_usuario(resultSet.getInt(1));
					usuario.setNome(resultSet.getString(2));
					usuario.setEmail(resultSet.getString(3));
					usuario.setSenha(resultSet.getString(4));
				}
				statement.close();
				resultSet.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn.close();
			}
			return usuario;
		}

	//login
	public Usuario login(Usuario u) throws SQLException{
		ResultSet rset=null;
		Connection conn=null;
		PreparedStatement statement=null;
		
		String sql="SELECT * FROM usuario WHERE email=? AND senha=?";
		
		Usuario usuario=new Usuario();
		
		try {
			conn=ConnectionFactory.createConnectionToMySQL();
			statement=(PreparedStatement) conn.prepareStatement(sql);
			
			statement.setString(1, u.getEmail());
			statement.setString(2, u.getSenha());
			rset=statement.executeQuery();
			
			if(rset.next()) {
				usuario.setId_usuario(rset.getInt(1));
				usuario.setNome(rset.getString(2));
				usuario.setEmail(rset.getString(3));
				usuario.setSenha(rset.getString(3));
			}
			
			statement.execute();
			
		} catch (Exception e) {
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
				if (rset!=null) {
					rset.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return usuario;
	}
	
	private Connection conn() throws Exception{
		return ConnectionFactory.createConnectionToMySQL();
	}
	
}

package exemplo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import exemplo.dto.UsuarioDTO;
import exemplo.exception.NegocioException;
import exemplo.util.ConexaoUtil;

public class UsuarioDAO {


	public static Integer cadastrarUsuario(UsuarioDTO usuarioDTO) throws NegocioException, ClassNotFoundException,ParseException {

		Integer codUsuario = null;
		ConexaoUtil c = null;
		try {
			c = new ConexaoUtil();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USUARIO(NM_USUARIO, USUARIO, SENHA, EMAIL, COD_USUARIO_CADASTRO)");
			sql.append(" VALUES(?, ?, ?, ?, ?)");

			PreparedStatement statement = c.getConexao().prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(usuarioDTO.getNmUsuario()!=null){statement.setString(1, usuarioDTO.getNmUsuario());}else{statement.setNull(1,java.sql.Types.NULL);}
			if(usuarioDTO.getUsuario()!=null){statement.setString(2, usuarioDTO.getUsuario());}else{statement.setNull(2,java.sql.Types.NULL);}
			if(usuarioDTO.getSenha()!=null){statement.setString(3, usuarioDTO.getSenha());}else{statement.setNull(3,java.sql.Types.NULL);}
			if(usuarioDTO.getEmail()!=null){statement.setString(4, usuarioDTO.getEmail());}else{statement.setNull(4,java.sql.Types.NULL);}
			if(usuarioDTO.getCodUsuarioCadastro()!=null){statement.setInt(5, usuarioDTO.getCodUsuarioCadastro());}else{statement.setNull(5,java.sql.Types.NULL);}

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.first()) {
				codUsuario = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new NegocioException(e);
		} finally {
			try {
				c.fechaConexao();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return codUsuario;
	}
	
	
	

	public static boolean atualizarUsuario(UsuarioDTO usuarioDTO) throws NegocioException {
		Boolean execute = false;
		ConexaoUtil c = null;
		try {
			c = new ConexaoUtil();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE USUARIO ");
			sql.append(" SET NM_USUARIO = ?, USUARIO = ?, EMAIL = ?, ATIVO = ?, COD_USUARIO_CADASTRO = ?");
			sql.append(" WHERE COD_USUARIO = ?");

			PreparedStatement statement = c.getConexao().prepareStatement(sql.toString());
			if(usuarioDTO.getNmUsuario()!=null){statement.setString(1, usuarioDTO.getNmUsuario());}else{statement.setNull(1,java.sql.Types.NULL);}
			if(usuarioDTO.getUsuario()!=null){statement.setString(2, usuarioDTO.getUsuario());}else{statement.setNull(2,java.sql.Types.NULL);}
			if(usuarioDTO.getEmail()!=null){statement.setString(3, usuarioDTO.getEmail());}else{statement.setNull(3,java.sql.Types.NULL);}
			if(usuarioDTO.getAtivo()!=null){statement.setInt(4, usuarioDTO.getAtivo());}else{statement.setNull(4,java.sql.Types.NULL);}
			if(usuarioDTO.getCodUsuarioCadastro()!=null){statement.setInt(5, usuarioDTO.getCodUsuarioCadastro());}else{statement.setNull(5,java.sql.Types.NULL);}
			if(usuarioDTO.getCodUsuario()!=null){statement.setInt(6, usuarioDTO.getCodUsuario());}else{statement.setNull(6,java.sql.Types.NULL);}

			statement.executeUpdate();
			
			execute = true;

		} catch (SQLException e) {
			execute = false;
			throw new NegocioException(e);
		} finally {
			try {
				c.fechaConexao();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return execute;
	}
	
		
	
	public UsuarioDTO listarUsuarioPorId(Integer codUsuario) throws NegocioException {
		UsuarioDTO usuarioDTO = null;
		ConexaoUtil c = null;
		try {
			c = new ConexaoUtil();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT U.COD_USUARIO,  U.NM_USUARIO, U.USUARIO, U.SENHA, U.EMAIL, U.ATIVO");
			sql.append(" FROM USUARIO AS U ");
			sql.append(" WHERE U.COD_USUARIO = ? ORDER BY U.NM_USUARIO ASC");

			PreparedStatement statement = c.getConexao().prepareStatement(sql.toString());
			statement.setInt(1, codUsuario);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCodUsuario(resultSet.getInt("cod_usuario"));
				usuarioDTO.setNmUsuario(resultSet.getString("nm_usuario"));
				usuarioDTO.setUsuario(resultSet.getString("usuario"));
				usuarioDTO.setSenha(resultSet.getString("senha"));
				usuarioDTO.setEmail(resultSet.getString("email"));
				usuarioDTO.setAtivo(resultSet.getInt("ativo"));
				
			}
		} catch (SQLException e) {
			throw new NegocioException(e);
		} finally {
			try {
				c.fechaConexao();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return usuarioDTO;
	}
	

	public boolean removerUsuario(Integer codUsuario) throws NegocioException {
		Boolean execute = false;
		ConexaoUtil c = null;
		try {
			c = new ConexaoUtil();

			StringBuilder sql = new StringBuilder();
			//sql.append("DELETE FROM USUARIO WHERE COD_USUARIO = ?");
			sql.append("UPDATE USUARIO ");
			sql.append(" SET ATIVO = ?");
			sql.append(" WHERE COD_USUARIO = ?");

			PreparedStatement statement = c.getConexao().prepareStatement(sql.toString());
			statement.setInt(1, 0);
			if(codUsuario!=null){statement.setInt(2, codUsuario);}else{statement.setNull(2,java.sql.Types.NULL);}

			statement.execute();
			
			execute = true;
		
		} catch (SQLException e) {
			throw new NegocioException(e);
		} finally {
			try {
				c.fechaConexao();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return execute;
	}
	

	public List<UsuarioDTO> filtrar(String condicao)
			throws NegocioException {

		String where = "";

		if (condicao != null && !condicao.isEmpty()) {

			where = "WHERE " + condicao;

		} else {

			where = "";
		}

		List<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();

		ConexaoUtil c = null;
		try {
			c = new ConexaoUtil();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT U.COD_USUARIO,  U.NM_USUARIO, U.USUARIO, U.SENHA, U.EMAIL, U.ATIVO");
			sql.append(" FROM USUARIO AS U ");
			sql.append("" + where + " ORDER BY U.NM_USUARIO ASC");

			PreparedStatement statement = c.getConexao().prepareStatement(sql.toString());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				UsuarioDTO usuarioAux = new UsuarioDTO();
				usuarioAux.setCodUsuario(resultSet.getInt("cod_usuario"));
				usuarioAux.setNmUsuario(resultSet.getString("nm_usuario"));
				usuarioAux.setUsuario(resultSet.getString("usuario"));
				usuarioAux.setSenha(resultSet.getString("senha"));
				usuarioAux.setEmail(resultSet.getString("email"));
				usuarioAux.setAtivo(resultSet.getInt("ativo"));
								
				listaUsuarios.add(usuarioAux);
			}
			
			
		} catch (SQLException e) {
			throw new NegocioException(e);
		} finally {
			try {
				c.fechaConexao();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		return listaUsuarios;
	}
}

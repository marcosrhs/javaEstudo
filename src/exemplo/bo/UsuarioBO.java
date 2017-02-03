package exemplo.bo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import exemplo.dao.UsuarioDAO;
import exemplo.dto.UsuarioDTO;
import exemplo.exception.NegocioException;


public class UsuarioBO { 
	
private UsuarioDAO usuarioDAO;


	
	public UsuarioBO() {
		usuarioDAO = new UsuarioDAO();
	}


	public void cadastrarUsuario(UsuarioDTO usuarioDTO) throws NegocioException {
		try {
			UsuarioDAO.cadastrarUsuario(usuarioDTO);
		} catch (ClassNotFoundException e) {
			e.getMessage();
			throw new NegocioException(e);
		} catch (ParseException e) {
			e.getMessage();
			throw new NegocioException(e);
		}
	}
	

	public void removerUsuario(Integer codUsuario) throws NegocioException {
		try {
			usuarioDAO.removerUsuario(codUsuario);
		} catch (NegocioException e) {
			e.getMessage();
			throw new NegocioException(e);
		}
	}
	
	
	public void atualizarUsuario(UsuarioDTO usuarioDTO) throws NegocioException {
		try {
			UsuarioDAO.atualizarUsuario(usuarioDTO);
		} catch (NegocioException e) {
			e.getMessage();
			throw new NegocioException(e);
		}
	}
	
	
	public UsuarioDTO listarUsuarioPorId(Integer codUsuario) throws NegocioException {
		try {
			return usuarioDAO.listarUsuarioPorId(codUsuario);
		} catch (NegocioException e) {
			e.getMessage();
			throw new NegocioException(e);
		}
	}
	

	public List<UsuarioDTO> filtrar(String condicao) throws NegocioException {
		try {
			return usuarioDAO.filtrar(condicao);
		} catch (NegocioException e) {
			e.getMessage();
			throw new NegocioException(e);
		}
	}

	/**
	 * Método de geração de MD5.
	 * 
	 * @param senha
	 * @return
	 * @throws NegocioException
	 */
	public static String geraMD5 (String senha) throws NegocioException{
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
			throw new NegocioException(e);
		}  
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
		String s2 = hash.toString(16);
		
		return s2;

	}
	
	

}

package exemplo.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codUsuario;
	private String nmUsuario;
	private String usuario;
	private String senha;
	private String email;
	private Integer ativo;
	private Integer codUsuarioCadastro;
	
	public Integer getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNmUsuario() {
		return nmUsuario;
	}
	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAtivo() {
		return ativo;
	}
	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}
	public Integer getCodUsuarioCadastro() {
		return codUsuarioCadastro;
	}
	public void setCodUsuarioCadastro(Integer codUsuarioCadastro) {
		this.codUsuarioCadastro = codUsuarioCadastro;
	}
	
}

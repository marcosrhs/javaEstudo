package exemplo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.bo.UsuarioBO;
import exemplo.dto.UsuarioDTO;
import exemplo.exception.NegocioException;

public class AtualizarUsuarioCommand {

	//private static String proximo;

	private static UsuarioBO usuarioBO;

	public static void execute(HttpServletRequest request,HttpServletResponse response) throws NegocioException, ServletException, IOException {
		UsuarioDTO usr = (UsuarioDTO) request.getSession().getAttribute("usuarioAutenticado"); 
		String codUsuario = request.getParameter("cod_usuario");
		usuarioBO = new UsuarioBO();


		String nmUsuario = request.getParameter("nm_usuario");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String ativo = request.getParameter("ativo");

		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setNmUsuario(nmUsuario!=null?nmUsuario:null);
			usuarioDTO.setUsuario(usuario!=null?usuario:null);
			usuarioDTO.setSenha(senha!=null?UsuarioBO.geraMD5(senha):null);
			usuarioDTO.setEmail(email!=null?email:null);
			usuarioDTO.setAtivo(ativo!=null?Integer.parseInt(ativo):null);
			usuarioDTO.setCodUsuario(codUsuario!=null?Integer.parseInt(codUsuario):null);

			usuarioDTO.setCodUsuarioCadastro(usr.getCodUsuario());

			
				usuarioBO.atualizarUsuario(usuarioDTO);
				//proximo = "MainServlet?acao=listaUsuario";

			} catch (Exception e) {
				e.printStackTrace();
			request.setAttribute("msgErro", e.toString());
		}

		//RequestDispatcher rd = request.getRequestDispatcher(proximo);
        //rd.forward(request, response);
	}

}

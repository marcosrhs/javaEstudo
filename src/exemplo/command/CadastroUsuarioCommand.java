package exemplo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.bo.UsuarioBO;
import exemplo.dto.UsuarioDTO;
import exemplo.exception.NegocioException;

public class CadastroUsuarioCommand {
	
	//private static String proximo;
	
	private static UsuarioBO usuarioBO;
	
	public static void execute(HttpServletRequest request,HttpServletResponse response) throws NegocioException, ServletException, IOException {
		UsuarioDTO usr = (UsuarioDTO) request.getSession().getAttribute("usuarioAutenticado"); 
		usuarioBO = new UsuarioBO();
		//proximo = "admin/cadastroUsuario.jsp";
		
		String nmUsuario = request.getParameter("nm_usuario");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");

		
		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setNmUsuario(nmUsuario!=null?nmUsuario:null);
			usuarioDTO.setUsuario(usuario!=null?usuario:null);
			usuarioDTO.setSenha(senha!=null?UsuarioBO.geraMD5(senha):null);
			usuarioDTO.setEmail(email!=null?email:null);
			usuarioDTO.setCodUsuarioCadastro(usr.getCodUsuario());
			
			usuarioBO.cadastrarUsuario(usuarioDTO);
			//proximo = "MainServlet?acao=listaUsuario";
				
			
		} catch (Exception e) {
			//proximo = "MainServlet?acao=montagemCadastro&tipo=usuario";
			request.setAttribute("msgErro", e.getMessage());
		}
		
		//RequestDispatcher rd = request.getRequestDispatcher(proximo);
        //rd.forward(request, response);
	}
	
}

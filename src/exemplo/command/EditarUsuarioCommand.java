package exemplo.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.bo.UsuarioBO;
import exemplo.dto.UsuarioDTO;
import exemplo.exception.NegocioException;

public class EditarUsuarioCommand{

	private static String proximo;
	
	private static UsuarioBO usuarioBO;

	
	public static void execute(HttpServletRequest request,HttpServletResponse response) throws NegocioException, ServletException, IOException {
		proximo = "admin/editaUsuario.jsp";
		usuarioBO = new UsuarioBO();
		
		try {
			Integer codUsuario = Integer.parseInt(request.getParameter("cod_usuario"));
			UsuarioDTO usuario = usuarioBO.listarUsuarioPorId(codUsuario);
			request.setAttribute("usuario", usuario);
			
		} catch (NegocioException e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		if (!response.isCommitted()){  
			   RequestDispatcher dispatcher = request.getRequestDispatcher(proximo);   
			   dispatcher.forward(request, response);   
			}  
			return; 
	}

}

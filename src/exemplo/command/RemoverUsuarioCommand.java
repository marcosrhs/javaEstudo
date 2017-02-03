package exemplo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.bo.UsuarioBO;
import exemplo.exception.NegocioException;

public class RemoverUsuarioCommand {

	private static UsuarioBO usuarioBO;

	public static void execute(HttpServletRequest request,HttpServletResponse response) throws NegocioException, ServletException, IOException {

		usuarioBO = new UsuarioBO();
		
		try {
			Integer codUsuario = Integer.parseInt(request.getParameter("cod_usuario"));

			usuarioBO.removerUsuario(codUsuario);
		} catch (NegocioException e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
	}

}

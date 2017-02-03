package exemplo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.command.AtualizarUsuarioCommand;
import exemplo.command.CadastroUsuarioCommand;
import exemplo.command.EditarUsuarioCommand;
import exemplo.command.IndexCommand;
import exemplo.command.ListaUsuarioCommand;
import exemplo.command.LogoutCommand;
import exemplo.command.MontagemCadastroCommand;
import exemplo.command.RemoverUsuarioCommand;
import exemplo.exception.NegocioException;

public class MainServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;


		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String acao = request.getParameter("acao");
					
			if("montagemCadastro".equals(acao)){
				try {
					MontagemCadastroCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			
			if("index".equals(acao)){
				try {
					IndexCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			
			if("logout".equals(acao)){
				try {
					LogoutCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			
			if("atualizaUsuario".equals(acao)){
				try {
					AtualizarUsuarioCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			if("cadastroUsuario".equals(acao)){
				try {
					CadastroUsuarioCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			if("editaUsuario".equals(acao)){
				try {
					EditarUsuarioCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			if("listaUsuario".equals(acao)){
				try {
					ListaUsuarioCommand.execute(request,response,acao);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
			
			if("removerUsuario".equals(acao)){
				try {
					RemoverUsuarioCommand.execute(request,response);
				} catch (NegocioException e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
	}
}
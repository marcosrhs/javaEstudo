package exemplo.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.exception.NegocioException;

public class MontagemCadastroCommand {
	
	private static String proximo;
	
	public static void execute(HttpServletRequest request, HttpServletResponse response) throws NegocioException, ServletException, IOException {

		String tipo = request.getParameter("tipo");
		String isAdd = request.getParameter("isAdd");
		String isEdit = request.getParameter("isEdit");
		String isSearch = request.getParameter("isSearch");
		
		/////////////////////////////////////////////////////////////////
		
		if (isEdit != null && !"".equals(isEdit) && "usuario".equals(tipo)) {
			
			proximo = "admin/editaUsuario.jsp";
		}
		if (isSearch != null && !"".equals(isSearch) && "usuario".equals(tipo)) {
			proximo = "admin/pesquisaUsuario.jsp";
		} 
		if (isAdd != null && "y".equals(isAdd) && "usuario".equals(tipo)) {
			
			proximo = "admin/cadastroUsuario.jsp";
		}
		
		if (!response.isCommitted()){  
			   RequestDispatcher dispatcher = request.getRequestDispatcher(proximo);   
			   dispatcher.forward(request, response);   
			}  
			return; 
	}
}

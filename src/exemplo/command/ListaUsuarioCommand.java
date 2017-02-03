package exemplo.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.bo.UsuarioBO;
import exemplo.dto.UsuarioDTO;
import exemplo.exception.NegocioException;

public class ListaUsuarioCommand{

	private static String proximo;
	
	private static UsuarioBO usuarioBO;


	public static void execute(HttpServletRequest request,HttpServletResponse response,String acao) throws NegocioException, ServletException, IOException {
		usuarioBO = new UsuarioBO();
		proximo = "admin/listaUsuario.jsp";
		
		try {
			String nmUsuario = request.getParameter("nm_usuario");
			String usuario = request.getParameter("usuario");

			String ativo = null;
			
			if(request.getParameter("ativo") == null){
				
				ativo = "1";
			}else{
				
				ativo = request.getParameter("ativo");
			}
			
			String queryAux = " ";
			
			StringBuffer query = new StringBuffer();
			
			if (nmUsuario != null && !nmUsuario.isEmpty() && !nmUsuario.isEmpty()) {
				query.append(queryAux);
				query.append("U.NM_USUARIO LIKE '%"+nmUsuario+"%'");
				queryAux = " AND "; 
			}
			
			if (usuario != null && !usuario.isEmpty() && !usuario.isEmpty()) {
				query.append(queryAux);
				query.append("U.USUARIO == '%"+usuario+"%'");
				queryAux = " AND "; 
			}

			if (ativo != null && !ativo.isEmpty() && !ativo.isEmpty()) {
				query.append(queryAux);
				query.append("U.ATIVO = '"+ativo+"'");
				queryAux = " AND "; 
			}

			List<UsuarioDTO> listaUsuario = usuarioBO.filtrar(query.toString());
			request.setAttribute("listaUsuario", listaUsuario);
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

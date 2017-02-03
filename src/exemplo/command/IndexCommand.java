package exemplo.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exemplo.exception.NegocioException;

public class IndexCommand {
	
	public static void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NegocioException {
        
		String proximo = "index.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(proximo);
        rd.forward(request, response);
	}
	
}

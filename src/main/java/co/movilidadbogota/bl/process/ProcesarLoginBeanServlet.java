package co.movilidadbogota.bl.process;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.movilidadbogota.bl.LoginBean;
import co.movilidadbogota.bl.UsuarioBean;


@WebServlet ("/processLogin")
public class ProcesarLoginBeanServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProcesarLoginBeanServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
 
			LoginBean loginBean = new LoginBean();

			System.out.println(request.getParameter("usuario"));
			String respuesta = loginBean.usuarioSesion(request);

			if (loginBean.getUsername().isEmpty() || loginBean.getPassword().isEmpty()) {
				


				RequestDispatcher req = request.getRequestDispatcher("login.jsp");
				req.include(request, response);

			} else {
				
				if (respuesta == "main") {
					UsuarioBean user = new UsuarioBean();
					user.setLogin(request.getParameter("usuario"));
					user.setPassword(request.getParameter("password"));
					user.setFechaIngreso(new Date());
					System.out.println("\nCrea sesion");
					HttpSession session = request.getSession(true);
					
					session.setAttribute("usuario", user);

					RequestDispatcher req = request.getRequestDispatcher("/pages/main.jsp");
					req.forward(request, response);
				}else {
					RequestDispatcher req = request.getRequestDispatcher("login.jsp");
					req.forward(request, response);
				}
			}

		} catch (ServletException | IOException e) {

			e.printStackTrace();
		}catch (Exception e) {

			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	
}

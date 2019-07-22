package co.movilidadbogota.bl.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import co.movilidadbogota.bl.BLEstudiosPrevios;
import co.movilidadbogota.model.EstudiosPrevios;

/**
 * Servlet implementation class LlenarCamposServlet
 */

@WebServlet(name = "/LlenarCamposServlet",
urlPatterns = {
    "/LlenarCampos/estudiosPrevios",
    "/LlenarCampos/buscarestudiosPrevios"
}
)
public class LlenarCamposServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LlenarCamposServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet(request, response);
		processRequest(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {
			String accion = this.getUrlAccion(request.getServletPath());
			boolean resultadoMod;
			ArrayList<EstudiosPrevios> estudiosPrevios = new ArrayList<EstudiosPrevios>();
			try {
				switch (accion) {
				case "estudiosPrevios":
					this.estudiosPrevios(request, response);
					break;
			
				case "buscarestudiosPrevios":
					this.buscarestudiosPrevios(request, response);
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void estudiosPrevios(HttpServletRequest request, HttpServletResponse response) {

		Gson gson = new Gson();

		BLEstudiosPrevios blestudiosPrevios;
		boolean rs = false;
		try {
			blestudiosPrevios = new BLEstudiosPrevios();

			EstudiosPrevios estudiosPrevios = (EstudiosPrevios) blestudiosPrevios.mostrarCampos(request);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("respuesta", estudiosPrevios);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	private void buscarestudiosPrevios(HttpServletRequest request, HttpServletResponse response) {

		Gson gson = new Gson();

		BLEstudiosPrevios blestudiosPrevios;
		boolean rs = false;
		try {
			blestudiosPrevios = new BLEstudiosPrevios();
			
			List<EstudiosPrevios> listaEstudiosPrevios = new ArrayList<EstudiosPrevios>();
			listaEstudiosPrevios =	(List<EstudiosPrevios>) blestudiosPrevios.mostrarBusquedaCampos(request);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("respuesta", listaEstudiosPrevios);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private String getUrlAccion(String url) {
        String[] partes = url.split("/");
        String accion = partes[partes.length - 1];
        return accion;
    }
	
	
}

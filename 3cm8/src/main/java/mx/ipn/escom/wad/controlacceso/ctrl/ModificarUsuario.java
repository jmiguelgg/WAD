package mx.ipn.escom.wad.controlacceso.ctrl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import mx.ipn.escom.wad.controlacceso.bs.UsuarioBs;
import mx.ipn.escom.wad.controlacceso.mapeo.Usuario;

/**
 * Servlet implementation class ModificarUsuario
 */
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioBs usuarioBs;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		Usuario usuario = usuarioBs.findById(idUsuario);
		RequestDispatcher rd = request.getRequestDispatcher("usuarios/modificar.jsp");
		request.setAttribute("usuario", usuario);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = obtenerUsuario(request);
		usuario = usuarioBs.update(usuario);
		response.sendRedirect("GestionarUsuarioCtrl");
	}

	private Usuario obtenerUsuario(HttpServletRequest request) {
		Usuario u = new Usuario();
		u.setId(Integer.parseInt(request.getParameter("idUsuario")));
		u.setNombre(request.getParameter("nombre"));
		u.setPrimerApellido(request.getParameter("primerApellido"));
		u.setSegundoApellido(request.getParameter("segundoApellido"));
		String fechaString=request.getParameter("nacimiento");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date nacimiento = null;
		try{
			nacimiento = format.parse(fechaString);
		} catch (ParseException pe) {
			// agregar el error de conversion
		}
		u.setNacimiento(nacimiento);
		return u;
	}
	
	public UsuarioBs getUsuarioBs() {
		return usuarioBs;
	}

	public void setUsuarioBs(UsuarioBs usuarioBs) {
		this.usuarioBs = usuarioBs;
	}

}

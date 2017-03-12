package mx.ipn.escom.wad.servlets.filters;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class Practica2
 */
public class Practica2 implements Filter {

    /**
     * Default constructor. 
     */
    public Practica2() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Cabeceras de la peticion: ");
		HttpServletRequest rq = (HttpServletRequest)request;
		SimpleDateFormat fm = new SimpleDateFormat("dd MMM HH:mm");
		Date date = new Date(rq.getDateHeader("If-Modified-Since"));
		String path = rq.getContextPath();
		String method = rq.getMethod();
		String []userAgent = rq.getHeader("User-Agent").split(" ");
		String so = userAgent[1] + userAgent[2] + " " + userAgent[3] + " " + userAgent[4] + " " + userAgent[5] + " " + userAgent[6];
		String nav = userAgent[12];
		System.out.println(fm.format(date) + " | " + path + " | " + method + " | " + nav + " " + so);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

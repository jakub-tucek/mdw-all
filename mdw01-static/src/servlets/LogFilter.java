package servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weblogic.servlet.annotation.WLFilter;

/**
 * Servlet implementation class LogFilter
 */
@WLFilter(mapping = "/*")
public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
	    // pass the request along the filter chain
	    chain.doFilter(request, response);
	    System.out.println("ExampleLogFilter: "+((HttpServletRequest)request).getRequestURI()+" - "+String.valueOf(System.currentTimeMillis()-start)+" ms");
	   
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}

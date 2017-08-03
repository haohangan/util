package test.demo;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean{

	public JwtFilter(){
		Logger.getGlobal().info("JwtFilter:"+JwtFilter.class);
	}
	
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) arg0;

//        final String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new ServletException("Missing or invalid Authorization header.");
//        }

//        final String token = authHeader.substring(7); // The part after "Bearer "

//        try {
//            final Claims claims = Jwts.parser().setSigningKey("secretkey")
//                .parseClaimsJws(token).getBody();
//            request.setAttribute("claims", claims);
//        }
//        catch (final SignatureException e) {
//            throw new ServletException("Invalid token.");
//        }
		Logger.getGlobal().info("request:"+request.getRequestURI());
        arg2.doFilter(arg0, arg1);
	}

}

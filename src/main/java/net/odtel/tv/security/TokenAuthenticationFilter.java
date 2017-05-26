package net.odtel.tv.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public TokenAuthenticationFilter() {
        super("/all");
        log.info("++++++");
        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            response.getOutputStream().print(authenticationException.getMessage());
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String token = request.getHeader("token");

        if (token == null) {
            token = request.getParameter("token");
        }

        if (token == null) {
            TokenAuthentication authentication = new TokenAuthentication(null, null);
            authentication.setAuthenticated(false);
            return authentication;
        }
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token, null);
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
        return authentication;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}

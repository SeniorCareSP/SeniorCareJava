    package seniorcare.crudseniorcare.configuration.security;

    import org.springframework.security.core.AuthenticationException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.security.authentication.BadCredentialsException;
    import org.springframework.security.web.AuthenticationEntryPoint;
    import org.springframework.stereotype.Component;
    import javax.naming.InsufficientResourcesException;
    import java.io.IOException;
    import java.rmi.ServerException;

    @Component
    public class AutenticacaoEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException, ServerException {
            if (authException.getClass().equals(BadCredentialsException.class)
                    || authException.getClass().equals(InsufficientResourcesException.class)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }







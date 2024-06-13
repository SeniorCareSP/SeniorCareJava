package seniorcare.crudseniorcare.configuration.security;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import seniorcare.crudseniorcare.configuration.security.jwt.GerenciadorTokenJwt;
import seniorcare.crudseniorcare.service.usuario.autenticacao.AutenticacaoService;
import java.io.IOException;
import java.util.Objects;
@RequiredArgsConstructor
public class AutenticacaoFilter extends OncePerRequestFilter {
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private UserDetailsService userDetailsService;

    public AutenticacaoFilter(AutenticacaoService autenticacaoService, GerenciadorTokenJwt gerenciadorTokenJwt) {
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        final String requestTokenHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwtToken = null;
//
//        // JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//            try {
//                username = gerenciadorTokenJwt.getUsernameFromToken(jwtToken);
//            } catch (IllegalArgumentException e) {
//                logger.error("Unable to get JWT Token");
//            } catch (MalformedJwtException e) {
//                logger.error("JWT Token has invalid format");
//            }
//        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
//        }
//
//        // Valide o token
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            // se o token é válido configure a autenticação manualmente
//            if (gerenciadorTokenJwt.validateToken(jwtToken, userDetails)) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                // Depois configure a autenticação no contexto do Spring
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = gerenciadorTokenJwt.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (MalformedJwtException e) {
                logger.error("JWT Token has invalid format");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");

            // Se a solicitação for para o endpoint de login, permita o acesso sem autenticação JWT
            if (request.getRequestURI().equals("/usuarios/login") && request.getMethod().equals("POST")) {
                // Aqui você pode adicionar lógica para validar as credenciais do usuário
                // e gerar um token JWT manualmente, se necessário
                chain.doFilter(request, response);
                return;
            }
        }

        // Valide o token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // se o token é válido configure a autenticação manualmente
            if (gerenciadorTokenJwt.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Depois configure a autenticação no contexto do Spring
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
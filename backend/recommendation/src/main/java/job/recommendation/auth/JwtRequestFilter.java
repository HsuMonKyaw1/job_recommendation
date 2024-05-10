// package job.recommendation.auth;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;

// public class JwtRequestFilter extends OncePerRequestFilter {

//     private JwtTokenUtil jwtTokenUtil;

//     public JwtRequestFilter(JwtTokenUtil jwtTokenUtil) {
//         this.jwtTokenUtil = jwtTokenUtil;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//             throws ServletException, IOException {
//         final String requestTokenHeader = request.getHeader("Authorization");

//         String username = null;
//         String jwtToken = null;
//         if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//             jwtToken = requestTokenHeader.substring(7);
//             username = jwtTokenUtil.extractUsername(jwtToken);
//         }

//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             if (jwtTokenUtil.validateToken(jwtToken, username)) {
//                 Authentication authentication = jwtTokenUtil.getAuthentication(jwtToken);
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }
//         chain.doFilter(request, response);
//     }
// }

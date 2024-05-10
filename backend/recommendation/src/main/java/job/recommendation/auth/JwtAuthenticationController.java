// package job.recommendation.auth;

// // JwtAuthenticationController.java
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.DisabledException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
// import job.recommendation.auth.JwtTokenUtil;
// import job.recommendation.auth.JwtUserDetailsService;
// import job.recommendation.services.UserService;
// import job.recommendation.auth.JwtRequest;
// import job.recommendation.models.User;

// @RestController
// @RequestMapping("/api/auth")
// public class JwtAuthenticationController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/authenticate")
//     public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        
//         User user = userService.findByUsername(authenticationRequest.getUsername());

//         if (user != null && user.getPassword().equals(authenticationRequest.getPassword())) {
           
//             String jwtToken = generateJwtToken(user);
//             return ResponseEntity.ok(new JwtResponse(jwtToken));
//         } else {
            
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//         }
//     }


//     private String generateJwtToken(User user) {
//         return jwtTokenUtil.generateToken(user);
//     }
// }

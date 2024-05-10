// package job.recommendation.auth;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import javax.sql.DataSource;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.JdbcUserDetailsManager;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private DataSource dataSource;

//     @Bean
//     public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
//         UserDetails user = User.builder()
//             .username("user")
//             .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//             .roles("USER")
//             .build();
//         UserDetails admin = User.builder()
//             .username("admin")
//             .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//             .roles("USER", "ADMIN")
//             .build();
//         JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//         userDetailsManager.createUser(user);
//         userDetailsManager.createUser(admin);
//         return userDetailsManager;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     http
//         .authorizeHttpRequests(requests -> requests
//             .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//             .anyRequest().authenticated()
//         )
//         .formLogin(form -> form
//             .loginPage("/login")
//             .permitAll()
//         )
//         .logout(logout -> logout
//             .permitAll());
    
//      return http.build();
//     }
// }



    


// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.core.userdetails.UserDetailsService;

// // SecurityConfig.java
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//     @Autowired
//     private UserDetailsService jwtUserDetailsService;

//     @Autowired
//     private JwtRequestFilter jwtRequestFilter;

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity.csrf().disable()
//                 .authorizeRequests().antMatchers("/authenticate").permitAll()
//                 .anyRequest().authenticated().and()
//                 .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//         httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//     }

//     @Override
//     @Bean
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }
// }

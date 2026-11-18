//package co.istad.lyhout.ecommerce.user.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain appFilterChain(HttpSecurity http) {
//        // 1. Endpoints: registration and API docs are public, everything else needs a JWT
//        http.authorizeHttpRequests(endpoint -> endpoint
//                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
//                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                // let error responses (409, 502, ...) reach anonymous callers
//                .requestMatchers("/error").permitAll()
//                .anyRequest().authenticated()
//        );
//
//        // 2. Stateless
//        http.sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        // 3. Disable CSRF and form login
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.formLogin(AbstractHttpConfigurer::disable);
//
//        // 4. Validate JWT from Keycloak
//        http.oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(Customizer.withDefaults())
//        );
//
//        return http.build();
//    }
//}

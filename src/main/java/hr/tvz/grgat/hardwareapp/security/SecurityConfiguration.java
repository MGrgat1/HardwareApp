package hr.tvz.grgat.hardwareapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Enables logging, defines unauthenticated endpoints, configures http security.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    static final List<String> UNAUTHENTICATED_ENDPOINTS = List.of(
            // TODO - popisati putanje koje ne trebaju biti prolaziti autentifikaciju
            "/authentication/login",
            "/h2-console/**"
    );

    private final JwtFilter jwtFilter;

    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
        // inherit security context in async function calls
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO - pobrinuti se da radi pristup h2 konzoli, slobodno ugasiti CORS, CSRF i slične zaštite
        http = http.cors().and().csrf().disable();
        http = http.headers().frameOptions().disable().and();

        // set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, e) -> {
                            log.error("Unauthorized request - {}", e.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                        }
                )
                .and();

        // set permissions on endpoints
        http.authorizeRequests()
                // public endpoints
                .antMatchers(UNAUTHENTICATED_ENDPOINTS.toArray(new String[0])).permitAll()
                // private endpoints
                .antMatchers(HttpMethod.DELETE, "/hardware/**").hasAnyRole("ROLE_ADMIN", "ROLE_DELETER")
                .anyRequest().authenticated();

        // add token filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

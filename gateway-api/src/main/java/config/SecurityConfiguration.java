package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
public class SecurityConfiguration {

    private final ReactiveClientRegistrationRepository repository;

    @Autowired
    public SecurityConfiguration(ReactiveClientRegistrationRepository repository) {
        this.repository = repository;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain (ServerHttpSecurity http) {

        http
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessHandler(oidServerLogoutSuccessHandler()); // to redirect to oauth2 login page.

        return http.build();
    }

    private ServerLogoutSuccessHandler oidServerLogoutSuccessHandler(){
        OidcClientInitiatedServerLogoutSuccessHandler oidcClientInitiatedServerLogoutSuccessHandler
                = new OidcClientInitiatedServerLogoutSuccessHandler(repository);
        oidcClientInitiatedServerLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/login");
        return oidcClientInitiatedServerLogoutSuccessHandler;
    }
}

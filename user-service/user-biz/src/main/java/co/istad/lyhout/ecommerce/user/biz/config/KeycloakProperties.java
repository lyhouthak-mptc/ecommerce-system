package co.istad.lyhout.ecommerce.user.biz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak.admin")
public record KeycloakProperties(
        String realm
) {
}

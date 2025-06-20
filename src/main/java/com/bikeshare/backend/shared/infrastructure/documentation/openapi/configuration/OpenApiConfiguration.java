package com.bikeshare.backend.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    //Properties

    @Value("Bike Share Platform")
    String applicationName;

    @Value("Bike Renting Platform")
    String applicationDescription;

    @Value("v2.0")
    String applicationVersion;

    //Methods

    @Bean
    public OpenAPI bikeShareOpenAPI() {

        // General configuration
        var openApi = new OpenAPI();
        openApi.info(new Info()
                        .title(this.applicationName)
                        .description(this.applicationDescription)
                        .version(this.applicationVersion)
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(
                        new ExternalDocumentation()
                                .description("ACME Learning Platform wiki Documentation")
                                .url("https://github.com/acme/learn-platform/wiki"));

        // Add a Security scheme
        final String securitySchemeName = "bearerAuth";
        /*
        openApi.addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                ).addSecurityItem(new SecurityRequirement().addList(securitySchemeName));*/

        // Return the OpenAPI configuration object with all the settings
        return openApi;

    }
}

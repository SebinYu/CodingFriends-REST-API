package net.skhu.codingFriends.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("v2.7.8") String appVersion) {

        Info info = new Info().title("REST API Docs - 코딩프렌즈").version(appVersion)
                .description("Spring Boot를 이용한 REST API Docs 입니다.")
                .contact(new Contact().name("유세빈").url("https://github.com/SebinYu").email("sebinyu72@gmail.com"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

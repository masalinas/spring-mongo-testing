package io.oferto.testing;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.oferto.testing.property.OpenApiProperties;
import io.oferto.testing.util.Generated;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableConfigurationProperties(OpenApiProperties.class)
public class TestingApplication {

	/*@Value("${api.common.title}") String apiTitle;
	@Value("${api.common.version}") String apiVersion;
	@Value("${api.common.description}") String apiDescription;
	@Value("${api.common.contact.name}") String apiContactName;
	@Value("${api.common.contact.url}")  String apiContactUrl;
	@Value("${api.common.contact.email}") String apiContactEmail;*/
	
	@Autowired
    private OpenApiProperties openApiProperties;
	
	@Bean
	public OpenAPI getOpenApiDocumentation() {		
		return new OpenAPI()
					.info(new Info().title(openApiProperties.getCommon().getTitle()).
						description(openApiProperties.getCommon().getDescription())
				        .version(openApiProperties.getCommon().getVersion())
				        .contact(new Contact()
				          .name(openApiProperties.getCommon().getContact().getName())
				          .url(openApiProperties.getCommon().getContact().getUrl())
				          .email(openApiProperties.getCommon().getContact().getEmail())));		
		}
	
	@Generated
	public static void main(String[] args) {		
		SpringApplication.run(TestingApplication.class, args);
	}	
}

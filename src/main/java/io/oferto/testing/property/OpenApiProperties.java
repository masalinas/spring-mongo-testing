package io.oferto.testing.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "api")
@Data
public class OpenApiProperties {	
	private Common common = new Common();
	
	@Data
	public static class Common {        
		private String version;
        private String title;
        private String description;
        private Contact contact = new Contact();
            
        @Data
        public static class Contact {            
			private String name;
            private String url;
            private String email;
        }
    }
}

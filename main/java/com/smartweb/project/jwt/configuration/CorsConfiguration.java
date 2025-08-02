
package com.smartweb.project.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";

	@Bean
	public WebMvcConfigurer corsConfigurer() {// تسجيل كائن  في السياق الكونتكست وتهدف الى تعديل الاعدادات الافتراضية في سبرنج بوت
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods(GET, POST, PUT, DELETE)
						.allowedHeaders("*")
						.allowedOrigins("http://localhost:8080") //Don't use in production.
											 //Don't use both .allowedOrigins("*") AND .allowCredentials(true);
											 //Instead use .allowedOriginPatterns("*")
											 // or .allowedOrigins("http://localhost:3000", "https://yourfrontend.com").
						.allowCredentials(true);	// cookies and tokens
			}
		};
	}
}

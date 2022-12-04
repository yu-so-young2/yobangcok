package com.ssafy.yobangcok.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 스프링 실행시 설정파일
@EnableSwagger2 // Swagger2를 사용
@SuppressWarnings("unchecked") // warning 제거
public class SwaggerConfiguration {

	
//  http://localhost:8080/swagger-ui.html#/

	private String version = "V1";
	private String title = "YoBangCok API " + version;

	private ApiInfo apiInfo() {
		String descript = "SSAFY Final Project<br>YoBangCok API Reference for Developers<br>";
		return new ApiInfoBuilder()
				.title(title)
				.description(descript)
//				.termsOfServiceUrl("https://edu.ssafy.com")
//				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
//				.license("SSAFY License")
//				.licenseUrl("ssafy@ssafy.com")
				.version("1.0")
				.build();
	}

	// API마다 구분짓기 위한 설정.
	@Bean
	public Docket allApi() {
		return getDocket("1. 전체", Predicates.or(PathSelectors.regex("/*.*")));
	}
	
	@Bean
	public Docket userApi() {
		return getDocket("2. 회원", Predicates.or(PathSelectors.regex("/users.*")));
	}

	@Bean
	public Docket boardApi() {
		return getDocket("3. 게시판", Predicates.or(PathSelectors.regex("/board.*")));
	}
	
	@Bean
	public Docket housesearchApi() {
		return getDocket("4. 아파트 검색", Predicates.or(PathSelectors.regex("/housesearch.*")));
		
	}

	@Bean
	public Docket housedealApi() {
		return getDocket("5. 실거래내역", Predicates.or(PathSelectors.regex("/housedeal.*")));

	}
	
	@Bean
	public Docket housesaleApi() {
		return getDocket("6. 실시간매물", Predicates.or(PathSelectors.regex("/housesale.*")));

	}
	


	public Docket getDocket(String groupName, Predicate<String> predicate) {
//		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.yobangcok.controller")).paths(predicate)
				.apis(RequestHandlerSelectors.any()).build();
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,responseMessages);
	}

	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}

}

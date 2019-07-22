package com.example.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
@Slf4j
@Configuration
@EnableConfigurationProperties(ApiSettings.class)
public class AppConfig {

	@Value("${security.auth.enable:false}")
	private boolean isSecurityEnable;

	@Value("${security.auth.keyId:defaultId}")
	private String keyId;

	@Value("#{'${byPassLtpaStr}'.split(',')}")
	private List<String> byPassAuthenticationUrls;

	@Autowired
	private ApiSettings apiSettings;

	@Bean(name = "contextLoggingFilter")
	public FilterRegistrationBean<ContextLoggingFilter> contextLoggingFilter() {
		final FilterRegistrationBean<ContextLoggingFilter> registrationBean = new FilterRegistrationBean<ContextLoggingFilter>();
		registrationBean.setFilter(new ContextLoggingFilter("baggage-wrapper"));
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean(name = "securityFilter")
	public FilterRegistrationBean<SecurityFilter> securityFilter() {
		final FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<SecurityFilter>();
		registrationBean.setFilter(new SecurityFilter(isSecurityEnable,keyId, byPassAuthenticationUrls));
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(2);
		return registrationBean;
	}

	@Bean(name = "restTemplate")
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		restTemplate.setMessageConverters(Arrays.asList(jacksonMessageConverter(), new StringHttpMessageConverter()));
		return restTemplate;
	}

	@Bean
	@Primary
	public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		final MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();
		return jacksonMessageConverter;
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(apiSettings.getReadTimeOut());
		factory.setConnectTimeout(apiSettings.getConnectionTimeOut());
		return factory;
	}
}

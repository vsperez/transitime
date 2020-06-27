package org.transitclock.spring;

import java.util.List;

import javax.xml.transform.Source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//  StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		// stringConverter.setWriteAcceptCharset(false);
		converters.add(new ByteArrayHttpMessageConverter());
		//converters.add(stringConverter);// SE ELIMINA PORQUE PROCUDE ERRORES
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter<Source>());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		converters.add(jackson2Converter());
		//converters.add(jacksonKml2Converter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter jackson2Converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(jacksonObjectMaper());

		return converter;
	}

	@Bean
	public ObjectMapper jacksonObjectMaper() {

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JtsModule());
		SimpleModule module = new SimpleModule();
		module.addSerializer(String.class, new StringSerializer());
		om.registerModule(module );

		return om;
	}

}

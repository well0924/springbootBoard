package web.com.spring.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("/istatic/file/")
	private String istaticpath;
	//업로드 경로
	@Value("${external.files.path}")
	private String filepath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler(istaticpath + "**").addResourceLocations("file:///" + filepath)
		.setCachePeriod(0)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true); // 강제 변경 
		
		return encodingFilter;	
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(1000));
		factory.setMaxRequestSize(DataSize.ofMegabytes(1000));
		 
	    return factory.createMultipartConfig();
	}
	 
	@Bean
	public MultipartResolver multipartResolver() {
	  return new  StandardServletMultipartResolver();
	}
}

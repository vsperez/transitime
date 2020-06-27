package org.transitclock.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({"org.transitclock"})
//@PropertySources(value= {@PropertySource(factory=cl.cm.zookeeper.config.ZookeeperPropertySourceFactory.class,value="cm_SAM?zoneEnabled=false&createKey=true"),@PropertySource("classpath:app.properties")})
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
            return new PropertySourcesPlaceholderConfigurer();
    }

}
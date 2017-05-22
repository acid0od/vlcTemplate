package net.odtel.tv.configuration;

import lombok.extern.slf4j.Slf4j;
import net.odtel.tv.repository.ChannelListRepository;
import net.odtel.tv.repository.TVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.odtel.tv")
@PropertySource(value = {"classpath:service.properties"}, ignoreResourceNotFound = true)
@Slf4j
public class VlcTemplateSpringConfiguration  {
    
    @Autowired
    private Config config;
    
    @Bean
    public DataSource getDataSource() {
        
        String url = config.getDbPrefix() + config.getHost() + ":" + config.getPort() + "/" + config.getDbName();
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
    
        dataSource.setDriverClassName(config.getDriverName());
        dataSource.setUrl(url);
        dataSource.setUsername(config.getUsername());
        dataSource.setUsername(config.getPassword());
        return dataSource;
    }

    @Bean
    public TVRepository tvRepository() {
        return new ChannelListRepository(getDataSource());
    }
}

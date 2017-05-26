package net.odtel.tv.configuration;

import net.odtel.tv.repository.ChannelListRepository;
import net.odtel.tv.repository.TVRepository;
import net.odtel.tv.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "net.odtel.tv")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class VlcTemplateSecurityConfig extends WebSecurityConfigurerAdapter {

 /*   @Autowired
    VlcTemplateUserDetailsService userDetailsService;

    @Autowired
    TokenAuthenticationManager tokenAuthenticationManager;
 
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
 
*//*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated();
                //.antMatchers("*//*
*//**").authenticated();
    }
*/
    
/*    @Autowired
    private Config config;*/
    
   /* @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrettyPrint(true);
        registry.enableContentNegotiation(jsonView);
        super.configureViewResolvers(registry);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static*//**").addResourceLocations("/static/");
    }*/
    
/*    @Bean
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
    }*/
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
                // allow anonymous resource requests
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();
        
        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        
        // disable page caching
        httpSecurity.headers().cacheControl();
    }
    
    
  /*  @Bean(name = "restTokenAuthenticationFilter")
    public TokenAuthenticationFilter restTokenAuthenticationFilter() {
        TokenAuthenticationFilter restTokenAuthenticationFilter = new TokenAuthenticationFilter();
        tokenAuthenticationManager.setUserDetailsService(userDetailsService);
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }*/
}

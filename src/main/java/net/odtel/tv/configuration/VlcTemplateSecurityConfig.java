package net.odtel.tv.configuration;

import net.odtel.tv.security.TokenAuthenticationFilter;
import net.odtel.tv.security.TokenAuthenticationManager;
import net.odtel.tv.security.VlcTemplateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "net.odtel.tv")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class VlcTemplateSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    VlcTemplateUserDetailsService userDetailsService;

    @Autowired
    TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Bean(name = "restTokenAuthenticationFilter")
    public TokenAuthenticationFilter restTokenAuthenticationFilter() {
        TokenAuthenticationFilter restTokenAuthenticationFilter = new TokenAuthenticationFilter();
        tokenAuthenticationManager.setUserDetailsService(userDetailsService);
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }
}

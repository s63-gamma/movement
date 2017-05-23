package com.gamma.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Created by guushamm on 3-4-17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${ldap.url}")
	private String ldapurl;
	@Value("${ldap.basedn}")
	private String ldapbasedn;
	@Value("${ldap.userdn}")
	private String ldapuserdn;
	@Value("${ldap.password}")
	private String ldappassword;
	@Value("${ldap.searchbase}")
	private String ldapsearchBase;
	@Value("${ldap.search}")
	private String ldapsearch;
	@Value("${ldap.passwordAttribute}")
	private String ldappasswordAttibute;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilterBefore(new CorsFilter(corsConfigurationSource()), ChannelProcessingFilter.class)
				.csrf().disable()
				.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and().httpBasic();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.ldapAuthentication()
				.userSearchBase(ldapsearchBase)
				.userSearchFilter(ldapsearch)
				//.groupSearchBase("ou=groups")
				.contextSource(contextSource())
				.passwordCompare()
				.passwordEncoder(new LdapShaPasswordEncoder())
				.passwordAttribute(ldappasswordAttibute)
				.and().userDetailsContextMapper(new CredentialsRoleMapper());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource source = new LdapContextSource();
		source.setUrl(ldapurl);
		source.setBase(ldapbasedn);
		source.setUserDn(ldapuserdn);
		source.setPassword(ldappassword);
		return source;
	}

	@Bean
	public LdapTemplate ldapTemplate(LdapContextSource source){
		return new LdapTemplate(source);
	}
}

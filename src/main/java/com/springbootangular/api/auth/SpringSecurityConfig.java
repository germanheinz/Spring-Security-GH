package com.springbootangular.api.auth;

import com.springbootangular.api.services.UsuarioService;
import com.springbootangular.api.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*
* Creando una clase de configuracion
* Extendemos la clase a WebSecurityConfigurerAdapter
* Inyectamos la variable UserDetailService ya que nuestra clase la implementa
* Sobreescribimos un metodo para la configuracion del login configure(AuthenticationManagerBuilder auth)
* Usamos una instancia de encriptar un password para mas seguridad
* Ya que luego vamos a utilizar este metodo passwordEncoder en otras las clases, lo marcamos como Bean
* parecido a la anotacion component, pero component es para una clase que creemos nosotros, en este caso
* es un metodo y devuelve un objeto, entonces debe ir como @Bean
* Anotandolo como @Bean nos dejara inyectarlo en otra clase
*
* */

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailsService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

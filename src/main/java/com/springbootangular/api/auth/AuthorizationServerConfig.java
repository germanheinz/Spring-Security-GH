package com.springbootangular.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;


/*
* Crea una clase de authorization y extiendo AuthorizationServerConfigurerAdapter
* Implemento 3 metodos de configuracion
*
* */

//Configuracion para OATH
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    /* Metodo que nos permitira definir los permisos de nuestro endpoint, de spring security OAUTH 2, quien puede acceder a la autenticacion
    * 1 - Damos el permiso a todos por permitAll
    * 2 - Asignamos el permiso al endpoint para validar el token. Cada vez que queramos acceder a una pagina protegida tenemos que enviar nuestro token como authorization
    *     Recap - Primero genera el token cuando se autentica. Segundo valida/chequea el token
    * */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permiteAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /* Metodo que se encarga de los clientes que accederan a nuestra api, en este caso solo uno, la api de Angular
    * 1 - Seleccionar el modo de almacenamiento, en este caso inMemory
    * 2 - Seleccionar el secret del pass
    * 3 - El scope, que va a poder hacer con nuestra app
    * 4 - Asignamos como va a ser nuestro acceso al token, va a ser por autenticacion username + password
    * 5 - Asignamos la duracion del token
    * 6 - Asignamos la duracion del refresh token
    * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("angularapp")
                .secret(passwordEncoder.encode("12345"))
                .scopes("read","write")
                .authorizedGrantTypes("password","refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);
    }

    /* Meotodo que Se encarga del proceso de autenticacion y validacion del token
    * 1 - Registrar el authenticationManager en el endpoint
    * 2 - Registrar el accessTokenConverter - Encargado de token. Almacena los datos de autenticacion, username, roles, info extra (claims), codifica y decodifica el token
    * 3 - Creo un metodo accessTokenConverter
    * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }
    @Bean
    public AccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        return jwtAccessTokenConverter;
    }
}

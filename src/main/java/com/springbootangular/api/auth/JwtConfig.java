package com.springbootangular.api.auth;



/*
* Llave secreta personalizada
* Con la llave secreta se crea nuestro token en el servidor de autorizacion, y luego ya autenticado, el cliente. lo envia al servidor de recursos
* El servidor de recurso verifica los permisos y va a validar el token que sea autentico
* a travez del servidor de autorizacion y va a utilizar esta misma llave secreta
*
*
* */
public class JwtConfig {
    public static final String SECRET_KEY = "GER";
}

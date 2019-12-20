package com.springbootangular.api.services;

import api.v1.model.UsuarioDTO;

public interface UsuarioService {

    public UsuarioDTO findByUsername(String username);

}

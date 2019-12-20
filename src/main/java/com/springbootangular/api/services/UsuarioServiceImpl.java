package com.springbootangular.api.services;

import api.v1.mapper.UsuarioMapper;
import api.v1.model.UsuarioDTO;
import com.springbootangular.api.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO findByUsername(String username) {
        return usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.findByUsername(username));
    }
}

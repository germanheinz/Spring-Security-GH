package com.springbootangular.api.services;

import api.v1.mapper.UsuarioMapper;
import api.v1.model.UsuarioDTO;
import com.springbootangular.api.domain.Usuario;
import com.springbootangular.api.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "German";
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(UsuarioMapper.INSTANCE, usuarioRepository);
    }

    @Test
    void findByUsername() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("German");
        usuario.setNombre("German");

        when(usuarioRepository.findByUsername(anyString())).thenReturn(usuario);

        UsuarioDTO usuarioDTO = usuarioService.findByUsername(usuario.getUsername());

        assertEquals(ID, usuario.getId());
        assertEquals(NAME, usuario.getUsername());
    }
}
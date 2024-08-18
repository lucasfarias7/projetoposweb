package br.edu.unipe.api.controller;

import br.edu.unipe.api.model.Usuario;
import br.edu.unipe.api.model.dto.UsuarioDTO;
import br.edu.unipe.api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioService.listarUsuarios()).thenReturn(usuarios);

        List<Usuario> result = usuarioController.listar();

        assertEquals(2, result.size());
        verify(usuarioService, times(1)).listarUsuarios();
    }

    @Test
    void testConsultarPorId() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.consultar(eq(1))).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioController.consultarPorId(1);

        assertEquals(usuarioDTO, result);
        verify(usuarioService, times(1)).consultar(1);
    }

    @Test
    void testGetUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        when(usuarioService.buscarPorEmail(eq("email@example.com"))).thenReturn(usuario);

        Usuario result = usuarioController.getUsuarioPorEmail("email@example.com");

        assertEquals(usuario, result);
        verify(usuarioService, times(1)).buscarPorEmail("email@example.com");
    }

    @Test
    void testSalvar() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.salvar(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.salvar(usuarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
        verify(usuarioService, times(1)).salvar(usuarioDTO);
    }

    @Test
    void testAtualizar() {
        Usuario usuario = new Usuario();
        when(usuarioService.alterar(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioController.atualizar(usuario);

        assertEquals(usuario, result);
        verify(usuarioService, times(1)).alterar(usuario);
    }

    @Test
    void testDeleteUsuario() {
        doNothing().when(usuarioService).deletar(eq(1));

        ResponseEntity<Void> response = usuarioController.deleteUsuario(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).deletar(1);
    }
}

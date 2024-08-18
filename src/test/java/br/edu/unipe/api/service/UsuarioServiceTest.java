package br.edu.unipe.api.service;

import br.edu.unipe.api.model.Usuario;
import br.edu.unipe.api.model.dto.UsuarioDTO;
import br.edu.unipe.api.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("test@example.com");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        usuarioDTO.setEmail("test@example.com");
    }

    @Test
    void listarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals(usuario, usuarios.get(0));
    }

    @Test
    void salvar() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(modelMapper.map(any(UsuarioDTO.class), any())).thenReturn(usuario);
        when(modelMapper.map(any(Usuario.class), any())).thenReturn(usuarioDTO);

        UsuarioDTO savedUsuarioDTO = usuarioService.salvar(usuarioDTO);

        assertNotNull(savedUsuarioDTO);
        assertEquals(usuarioDTO.getId(), savedUsuarioDTO.getId());
        assertEquals(usuarioDTO.getEmail(), savedUsuarioDTO.getEmail());
    }

    @Test
    void consultar() {
        when(usuarioRepository.existsById(anyInt())).thenReturn(true);
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(modelMapper.map(any(Usuario.class), any())).thenReturn(usuarioDTO);

        UsuarioDTO foundUsuarioDTO = usuarioService.consultar(1);

        assertNotNull(foundUsuarioDTO);
        assertEquals(usuarioDTO.getId(), foundUsuarioDTO.getId());
        assertEquals(usuarioDTO.getEmail(), foundUsuarioDTO.getEmail());
    }

    @Test
    void alterar() {
        when(usuarioRepository.existsById(anyInt())).thenReturn(true);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario updatedUsuario = usuarioService.alterar(usuario);

        assertNotNull(updatedUsuario);
        assertEquals(usuario.getId(), updatedUsuario.getId());
        assertEquals(usuario.getEmail(), updatedUsuario.getEmail());
    }

    @Test
    void deletar() {
        doNothing().when(usuarioRepository).deleteById(anyInt());
        when(usuarioRepository.existsById(anyInt())).thenReturn(true);

        usuarioService.deletar(1);

        verify(usuarioRepository, times(1)).deleteById(1);
    }

    @Test
    void buscarPorEmail() {
        when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(usuario);

        Usuario foundUsuario = usuarioService.buscarPorEmail("test@example.com");

        assertNotNull(foundUsuario);
        assertEquals(usuario.getEmail(), foundUsuario.getEmail());
    }

    @Test
    void validarExistenciaId_throwsException_whenIdNotExists() {
        when(usuarioRepository.existsById(anyInt())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.consultar(2);
        });

        assertEquals("Usuário não existe para o id 2", exception.getMessage());
    }

    @Test
    void validarExistenciaId_throwsException_whenIdIsNull() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.consultar(null);
        });

        assertEquals("Usuário não existe para o id null", exception.getMessage());
    }
}

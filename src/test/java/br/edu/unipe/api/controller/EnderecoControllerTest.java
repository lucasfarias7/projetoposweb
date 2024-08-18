package br.edu.unipe.api.controller;

import br.edu.unipe.api.client.ViaCepClient;
import br.edu.unipe.api.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private ViaCepClient viaCepClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarCep() {
        // Mocking the response from ViaCepClient
        Endereco mockEndereco = new Endereco();
        mockEndereco.setCep("01001-000");
        mockEndereco.setLogradouro("Praça da Sé");
        mockEndereco.setBairro("Sé");
        mockEndereco.setLocalidade("São Paulo");
        mockEndereco.setUf("SP");

        when(viaCepClient.consultaEnderecoPorCep(eq("01001000"))).thenReturn(mockEndereco);

        // Calling the controller method
        Endereco endereco = enderecoController.consultarCep("01001000");

        // Verifying the response
        assertNotNull(endereco);
        assertEquals("01001-000", endereco.getCep());
        assertEquals("Praça da Sé", endereco.getLogradouro());
        assertEquals("Sé", endereco.getBairro());
        assertEquals("São Paulo", endereco.getLocalidade());
        assertEquals("SP", endereco.getUf());
    }
}

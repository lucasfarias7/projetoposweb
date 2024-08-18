package br.edu.unipe.api.controller;

import br.edu.unipe.api.client.ViaCepClient;
import br.edu.unipe.api.model.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final ViaCepClient viaCepClient;

    @GetMapping("/cep/{cep}")
    public Endereco consultarCep(@PathVariable String cep){
        return viaCepClient.consultaEnderecoPorCep(cep);
    }
}

package br.edu.unipe.api.controller;

import br.edu.unipe.api.model.Aluno;
import br.edu.unipe.api.model.dto.AlunoDTO;
import br.edu.unipe.api.repository.AlunoRepository;
import br.edu.unipe.api.service.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService service;

    @GetMapping
    public List<Aluno> listarAlunos(){
        log.info("Listando alunos");
        return service.listarAlunos();
    }

    @GetMapping("/{id}")
    public AlunoDTO  consultarPorId(@PathVariable int id){
        log.info("Inicio - Consulta aluno id {} " , id);
        var AlunoDTO = service.consultar(id);
        log.info("Fim  - Consulta aluno id {} " , id);
        return AlunoDTO;
    }

    @GetMapping("/email/{email}")
    public Aluno getAlunoPorEmail(@PathVariable String  email){
        var aluno = service.buscarPorEmail(email);
        return aluno;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> salvar(@RequestBody AlunoDTO alunoDTO){
        alunoDTO = service.salvar(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTO);
    }

    @PutMapping
    public Aluno atualizar(@RequestBody Aluno aluno){
        return service.alterar(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

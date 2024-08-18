package br.edu.unipe.api.service;

import br.edu.unipe.api.exceptions.ResourceNotFoundException;
import br.edu.unipe.api.model.Aluno;
import br.edu.unipe.api.model.dto.AlunoDTO;
import br.edu.unipe.api.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final ModelMapper modelMapper;

    public List<Aluno> listarAlunos(){
        return repository.findAll();
    }

    public AlunoDTO salvar(AlunoDTO AlunoDto){
        var Aluno = repository.save(convertToEntity(AlunoDto));
        return convertToDto(Aluno);
    }

    public AlunoDTO consultar(Integer id){
        validarExistenciaId(id);
        return convertToDto(repository.findById(id).get());
    }

    public Aluno alterar(Aluno Aluno){
        validarExistenciaId(Aluno.getId());
        return repository.save(Aluno);
    }


    public void deletar(Integer id){
        log.info("Start - Excluindo user ID {} ", id);
        repository.deleteById(id);
        log.info("End - Excluindo user ID {} ", id);
    }

    public Aluno buscarPorEmail(String email){
        var Aluno = repository.buscarPorEmail(email);
        if(Objects.isNull(Aluno))
            throw new ResourceNotFoundException("Email não localizado " +email);

        return Aluno;
    }

    private void validarExistenciaId(Integer id){
        if(Objects.isNull(id) || !repository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não existe para o id "+id);
        }
    }

    public AlunoDTO convertToDto(Aluno user) {
        return modelMapper.map(user, AlunoDTO.class);
    }

    public Aluno convertToEntity(AlunoDTO userDTO) {
        return modelMapper.map(userDTO, Aluno.class);
    }

}

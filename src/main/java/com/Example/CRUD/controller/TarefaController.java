package com.Example.CRUD.controller;

import com.Example.CRUD.model.Tarefa;
import com.Example.CRUD.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository repository;

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return repository.save(tarefa);

    }
    @GetMapping
    public List<Tarefa> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Long id){
        return repository.findById(id).orElse(null);

    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada){
        return repository.findById(id).map(tarefa -> {
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluido(tarefaAtualizada.isConcluido());
            return repository.save(tarefa);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }
}

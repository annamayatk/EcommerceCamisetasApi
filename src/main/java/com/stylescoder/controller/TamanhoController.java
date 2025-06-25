package com.stylescoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stylescoder.entity.Tamanho;
import com.stylescoder.repository.TamanhoRepository;

@RestController
@RequestMapping("/tamanhos")
public class TamanhoController {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @PostMapping
    public ResponseEntity<Tamanho> criar(@RequestBody Tamanho tamanho) {
        Tamanho salvo = tamanhoRepository.save(tamanho);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public List<Tamanho> listar() {
        return tamanhoRepository.findAll();
    }
}

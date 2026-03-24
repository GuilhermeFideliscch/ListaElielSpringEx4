package com.example.Autor.controllers;

import com.example.Autor.models.AutorModel;
import com.example.Autor.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(name = "/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorModel>> findAll(){
        List<AutorModel> autores = autorService.findAll();

        return ResponseEntity.ok().body(autores);
    }

    @PostMapping
    public ResponseEntity<AutorModel> criarAutor(AutorModel autorModel){
        AutorModel novoAutor = autorService.criarAutor(autorModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoAutor.getId()).toUri();

        return ResponseEntity.created(uri).body(novoAutor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AutorModel>> findById(@PathVariable Long id){
        Optional<AutorModel> optionalAutorModel = autorService.findById(id);

        return ResponseEntity.ok().body(optionalAutorModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id){
        autorService.deletarAutor(id);
        return ResponseEntity.noContent().build();
    }
}

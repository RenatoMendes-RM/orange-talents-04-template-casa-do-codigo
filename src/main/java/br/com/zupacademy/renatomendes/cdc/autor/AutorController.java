package br.com.zupacademy.renatomendes.cdc.autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class AutorController {
    private AutorRepository autorRepository;
    private AutorRequest autorRequest;

    @Autowired
    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping("/autores")
    public ResponseEntity<?> cadastraAutor(@RequestBody @Validated AutorRequest autorRequest) {
        this.autorRequest = autorRequest;
        Autor autor = autorRequest.toModel();

// Primeira alternativa usando Entity manager:
        /*
        Optional<Autor> possivelEmail = autorRepository.findByEmail(autorRequest.getEmail());
        if (possivelEmail.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        */

// Segunda alternativa usando Repository:
        if (autorRepository.existsByEmail(autorRequest.getEmail())) {
            return ResponseEntity.badRequest().build();
        }


        autorRepository.save(autor);
        System.out.println("Autor Request: " + autorRequest);
        System.out.println("tudo funcionando belezinha " + LocalDateTime.now());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/autores/{id}/email")
    public ResponseEntity<?> buscaEmailAutor(@PathVariable Long id) {
        Optional<Autor> byId = autorRepository.findById(id);
        return ResponseEntity.ok(byId.get().getEmail());
    }
}

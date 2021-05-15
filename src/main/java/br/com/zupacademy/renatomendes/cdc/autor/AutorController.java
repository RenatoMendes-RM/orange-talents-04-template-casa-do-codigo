package br.com.zupacademy.renatomendes.cdc.autor;
import compartilhado.CampoUnicoComSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> cadastraAutor(@RequestBody @Valid AutorRequest autorRequest) {
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

    @InitBinder
    public void validacao(WebDataBinder binder) {
        CampoUnicoComSpring<AutorRequest, String> validadorEmailUnico
                = new CampoUnicoComSpring<>( "email"
                , AutorRequest.class
                , autorRepository::existsByEmail);
        binder.addValidators(validadorEmailUnico);
    }


    /*
    // Alefh  fez somente para demonstrar como funciona validador do Spring

    public void validacao(WebDataBinder binder, BindingResult result) {
        AutorRequest autorRequest;

        binder.getValidators().forEach(validator -> {
            if(validator.supports(AutorRequest.class)) {
                validator.validate(autorRequest, result.getAllErrors());

            }
        });

        binder.addValidators();

    }
*/

    @GetMapping("/autores/{id}/email")
    public ResponseEntity<?> buscaEmailAutor(@PathVariable Long id) {
        Optional<Autor> byId = autorRepository.findById(id);
        return ResponseEntity.ok(byId.get().getEmail());
    }
}

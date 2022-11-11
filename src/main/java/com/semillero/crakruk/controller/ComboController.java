package com.semillero.crakruk.controller;
import com.semillero.crakruk.dto.ComboDto;
import com.semillero.crakruk.exeption.EntityNotFoundException;
import com.semillero.crakruk.service.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/combos")
@CrossOrigin(origins = "*")
public class ComboController {

    @Autowired
    IComboService service;

    @PostMapping
    public ResponseEntity<ComboDto> createCombo(@Valid @RequestBody(required = true)ComboDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCombo(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComboDto> getComboById(@PathVariable("id") Long id){
       return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCombo(@PathVariable("id") Long id){
        service.deleteCombo(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateCombo(@PathVariable("id") long id,
                                        @Valid @RequestBody(required = true) ComboDto dto) throws EntityNotFoundException {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.updateCombo(id, dto));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data.not.found");
        }

    }

    @GetMapping
    public ResponseEntity<List<ComboDto>> getAllCombos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}

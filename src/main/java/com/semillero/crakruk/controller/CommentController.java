package com.semillero.crakruk.controller;

import com.semillero.crakruk.dto.ComboDto;
import com.semillero.crakruk.dto.CommentDto;
import com.semillero.crakruk.exeption.EntityNotFoundException;
import com.semillero.crakruk.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    ICommentService service;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody(required = true)CommentDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id){
        service.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping
    public ResponseEntity<?> updateComment(@PathVariable("id") long id,
                                         @Valid @RequestBody(required = true) CommentDto dto) throws EntityNotFoundException {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.updateComment(id, dto));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data.not.found");
        }

    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllComments());
    }
}

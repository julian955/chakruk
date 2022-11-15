package com.semillero.crakruk.controller;

import com.semillero.crakruk.auth.repository.UserRepository;
import com.semillero.crakruk.auth.service.IUserService;
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
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    ICommentService service;
    @Autowired
    IUserService userService;
    @Autowired
    UserRepository userRepository;



    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestHeader(value = "token") String token, @Valid @RequestBody(required = true)CommentDto dto){
        String userName = userService.getUserEmailFromToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(dto,userName));
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

    @PutMapping("/{id}")
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

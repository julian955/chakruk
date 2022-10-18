package com.semillero.crakruk.repository;

import com.semillero.crakruk.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

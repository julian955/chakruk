package com.semillero.crakruk.auth.model;


import com.semillero.crakruk.model.Comment;
import com.semillero.crakruk.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE combos SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick")
    private String user;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    @ManyToOne
    @JoinColumn(name="roles")
    private Role role;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;



}

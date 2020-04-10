package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    @ManyToMany
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    @Column
    @NotNull
    @Builder.Default
    private boolean accountExpired = false;

    @Column
    @NotNull
    @Builder.Default
    private boolean accountLocked = false;

    @Column
    @NotNull
    @Builder.Default
    private boolean credentialsExpired = false;

    @Column
    @NotNull
    @Builder.Default
    private boolean disabled = false;
}

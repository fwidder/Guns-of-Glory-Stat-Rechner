package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;
}

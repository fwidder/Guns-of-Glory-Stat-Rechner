package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Authority {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @NonNull
    private String name;
}

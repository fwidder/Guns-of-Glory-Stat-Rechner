package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class PlayerResources {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    private Resource food = Resource.builder().build();

    @NotNull
    @OneToOne
    private Resource wood = Resource.builder().build();

    @NotNull
    @OneToOne
    private Resource iron = Resource.builder().build();

    @NotNull
    @OneToOne
    private Resource silver = Resource.builder().build();

    @NotNull
    @ManyToOne(optional = false)
    private Player player;

    @Column(updatable = false)
    @NotNull
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();


}

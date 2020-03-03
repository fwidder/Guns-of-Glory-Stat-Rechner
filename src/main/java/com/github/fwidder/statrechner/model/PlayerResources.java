package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Column
    @NotNull
    @Min(value = 0, message = "Your amount of Gold can't be lower then zero!")
    private Long gold;

    @Column
    @NotNull
    @Min(value = 0, message = "Your amount of Wheat can't be lower then zero!")
    private Long wheat;

    @Column
    @NotNull
    @Min(value = 0, message = "Your amount of Wood can't be lower then zero!")
    private Long wood;

    @NotNull
    @ManyToOne(optional = false)
    private Player player;

    @Column(updatable = false)
    @NotNull
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();


}

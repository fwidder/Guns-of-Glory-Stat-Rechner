package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

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
    @Min(value = 0, message = "Your amount of Gold can't be lower then zero!")
    private Long gold;

    @Column
    @Min(value = 0, message = "Your amount of Wheat can't be lower then zero!")
    private Long wheat;

    @Column
    @Min(value = 0, message = "Your amount of Wood can't be lower then zero!")
    private Long wood;
}

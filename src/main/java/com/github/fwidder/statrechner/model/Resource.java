package com.github.fwidder.statrechner.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    @Builder.Default
    private Long quantity = 0L;

    @OneToMany
    @Builder.Default
    private List<ResourcePackage> resourcePackages = new ArrayList<>();
}

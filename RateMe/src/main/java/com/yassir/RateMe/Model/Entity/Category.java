package com.yassir.RateMe.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @NotBlank(message = "category name required")
    private String name ;

    @OneToMany (mappedBy = "category", cascade = CascadeType.ALL)
    private  List<Place> places = new ArrayList<>();


}







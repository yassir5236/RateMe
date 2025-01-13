package com.yassir.RateMe.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="Places")
public class Place {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

    private String name;
    private String description;
    private String category;
    private String photo;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double averageRating;

}


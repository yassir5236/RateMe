package com.yassir.RateMe.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Places")
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

//    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();


//    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
//    private List<Recommendation> recommendations = new ArrayList<>();

}


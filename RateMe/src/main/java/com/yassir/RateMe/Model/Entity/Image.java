package com.yassir.RateMe.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.engine.validationcontext.ReturnValueExecutableValidationContext;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;

    public Image(String path) {
        this.path = path;
    }

    public static Image of(String path) {
        return new Image(path);
    }
}

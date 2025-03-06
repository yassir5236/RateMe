package com.yassir.RateMe.Dto.Like;

import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record EmbeddedLikeDTO(
        Long id
) {
}

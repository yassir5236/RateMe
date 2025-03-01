package com.yassir.RateMe.Repository;

import com.yassir.RateMe.Model.Entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findByUserId(Long userId); // Fetch shares by user ID

}

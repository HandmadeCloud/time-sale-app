package com.example.timesaleapp.repository;

import com.example.timesaleapp.domain.timesale.TimeSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSaleRepository extends JpaRepository<TimeSale,Long> {
}

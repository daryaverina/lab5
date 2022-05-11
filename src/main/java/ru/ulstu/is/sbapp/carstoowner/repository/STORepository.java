package ru.ulstu.is.sbapp.carstoowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.carstoowner.model.STO;

public interface STORepository extends JpaRepository<STO, Long> {
}

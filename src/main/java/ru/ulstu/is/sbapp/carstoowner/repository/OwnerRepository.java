package ru.ulstu.is.sbapp.carstoowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.carstoowner.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

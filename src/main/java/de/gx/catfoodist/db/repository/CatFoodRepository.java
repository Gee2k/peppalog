package de.gx.catfoodist.db.repository;

import de.gx.catfoodist.db.entity.CatFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CatFoodRepository extends JpaRepository<CatFood, UUID> {
}

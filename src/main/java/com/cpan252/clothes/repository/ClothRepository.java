package com.cpan252.clothes.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.cpan252.clothes.model.Cloth;
import com.cpan252.clothes.model.Cloth.Brand;

//It will be an interface that defines operations with the fighter
//table in the database
public interface ClothRepository extends CrudRepository<Cloth, Long>{
    List<Cloth> findTitleByBrandAndYearcreated(String brand, Integer yearcreated);
}



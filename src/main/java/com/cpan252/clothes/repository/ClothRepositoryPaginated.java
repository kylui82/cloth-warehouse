package com.cpan252.clothes.repository;

import com.cpan252.clothes.model.Cloth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//It will be an interface that defines operations with the fighter
//table in the database
public interface ClothRepositoryPaginated extends PagingAndSortingRepository<Cloth, Long>{

}






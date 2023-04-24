package edu.iu.c322.finalProject.returnservice.repository;

import edu.iu.c322.finalProject.returnservice.model.entity.ItemReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemReturnRepository extends JpaRepository<ItemReturn, Integer> {
}

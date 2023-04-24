package edu.iu.c322.finalProject.returnservice.repository;

import edu.iu.c322.finalProject.returnservice.model.entity.RentedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentedItemRepository extends JpaRepository<RentedItems, Integer> {

    List<RentedItems> findByCustomerId(Integer customerId);

    RentedItems findByCustomerIdAndItemId(Integer customerId, Integer itemId);

}
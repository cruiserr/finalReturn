package edu.iu.c322.finalProject.returnservice.controller;

import edu.iu.c322.finalProject.returnservice.model.dto.ItemReturnDto;
import edu.iu.c322.finalProject.returnservice.model.dto.RentedItemDto;
import edu.iu.c322.finalProject.returnservice.model.dto.RentedItemsDto;
import edu.iu.c322.finalProject.returnservice.model.entity.ItemReturn;
import edu.iu.c322.finalProject.returnservice.model.entity.RentedItems;
import edu.iu.c322.finalProject.returnservice.repository.ItemReturnRepository;
import edu.iu.c322.finalProject.returnservice.repository.RentedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;




@RestController
@RequestMapping("/return")
public class returnController {

    @Autowired
    RentedItemRepository rentedItemRepository;

    @Autowired
    ItemReturnRepository itemReturnRepository;


    //showing items rented by customerId
    //
    @GetMapping("/return/{id}")
    public List<RentedItemsDto> findAllByCustomerId(@PathVariable int id){

        List<RentedItems> rentedItems = rentedItemRepository.findByCustomerId(id);
        List<RentedItemsDto> rentedItemsDtos = new ArrayList<>();

        for(RentedItems x: rentedItems){
            RentedItemsDto rentedItemsDto = new RentedItemsDto();
            rentedItemsDto.setName(x.getName());
            rentedItemsDtos.add(rentedItemsDto);
        }

        return rentedItemsDtos;

    }


    //clicking on the item
    //showing return page for specific customer item

    @GetMapping("/return/{id}/{iid}")
    public RentedItemDto findByCustomerAndItemId(@PathVariable int id, @PathVariable int iid){

        RentedItems rentedItem = rentedItemRepository.findByCustomerIdAndItemId(id, iid);
        RentedItemDto rentedItemDto = new RentedItemDto();

        rentedItemDto.setDateRented(rentedItem.getDateRented());
        rentedItemDto.setName(rentedItem.getName());
        rentedItemDto.setPrice(rentedItem.getPrice());
        rentedItemDto.setDateRented(rentedItem.getDateRented());
        rentedItemDto.setQuantity(rentedItem.getQuantity());

        return rentedItemDto;


    }

    // once customer hits final return button, they are moved to another page with reciept of return
    //saves return data
    @GetMapping("/return/{id}/{iid}/receipt")
    public ItemReturnDto giveReceipt(@PathVariable int id, @PathVariable int iid) {

        RentedItems rentedItem = rentedItemRepository.findByCustomerIdAndItemId(id, iid);

        ItemReturn itemReturn = new ItemReturn();

        itemReturn.setOrderId(rentedItem.getOrder_Id());
        //itemReturn.setCustomerTotal(rentedItem.getOrder_Id());




        LocalDate todayF = LocalDate.now();
        DateTimeFormatter formatterF = DateTimeFormatter.ofPattern("M/d/yyyy");
        String formattedToday = todayF.format(formatterF);
        itemReturn.setDateReceived(formattedToday);

        // date rented is formatted like this: 4/23/2023
        String dateRented = rentedItem.getDateRented();
        int lateFeePerDay = 5;

        // Convert dateRented and returnByDate strings to LocalDate objects
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate rentedDate = LocalDate.parse(dateRented, formatter);
        LocalDate returnByDate = LocalDate.parse(rentedItem.getReturnByDate(), formatter);

        // Get the current date
        LocalDate today = LocalDate.now();

        // Check if today is after the returnByDate
        if (today.isAfter(returnByDate)) {
            // Calculate the number of days past the return by date
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(returnByDate, today);

            // Calculate the late fee
            int lateFee = (int) (lateFeePerDay * daysLate);
            itemReturn.setLatePenalty(lateFee);
            System.out.println("Late fee: $" + lateFee);


            itemReturn.setCustomerTotal(rentedItem.getQuantity() * rentedItem.getPrice() + lateFee);
        } else {
            System.out.println("No late fee.");
            itemReturn.setLatePenalty(0);
            itemReturn.setCustomerTotal(rentedItem.getQuantity() * rentedItem.getPrice());
        }

        itemReturn.setSiteFee(2);

        itemReturnRepository.save(itemReturn);

        ItemReturnDto itemReturnDto = new ItemReturnDto();

        itemReturnDto.setCustomerTotal(itemReturn.getCustomerTotal());
        itemReturnDto.setLatePenalty(itemReturn.getLatePenalty());
        itemReturnDto.setOrderId(itemReturn.getOrderId());
        itemReturnDto.setDateReceived(itemReturn.getDateReceived());
        itemReturnDto.setSiteFee(itemReturn.getSiteFee());

        return itemReturnDto;




    }



}

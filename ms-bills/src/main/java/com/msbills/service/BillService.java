package com.msbills.service;

import com.msbills.feign.modelDTO.UserDTO;
import com.msbills.feign.service.SubscripcionService;
import com.msbills.models.Bill;
import com.msbills.repositories.IBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BillService{

  private final IBillRepository repository;
  private final SubscripcionService service;

  public List<Bill> getAllBill() {
    return repository.findAll();
  }

  public Bill saveBill(Bill bill) {
    return repository.save(bill);
  }

  public Bill findByCustomer(String customer) {
    UserDTO user = service.findById(customer);
    Bill bill  = repository.findByCustomerBill(customer).orElse(null);

   if(!Objects.isNull(bill))
          bill.setCustomer(user);

    return bill;
  }

}

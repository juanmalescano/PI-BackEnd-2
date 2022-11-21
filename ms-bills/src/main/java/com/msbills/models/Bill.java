package com.msbills.models;

import com.msbills.feign.modelDTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Bill {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String idBill;

  private Date billingDate;

  private String customerBill;

  private String productBill;

  private Double totalPrice;

  private UserDTO customer;
}

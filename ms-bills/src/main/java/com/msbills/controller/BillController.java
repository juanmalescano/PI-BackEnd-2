package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

  private final BillService service;

  @GetMapping("/all")
  @PreAuthorize("hasAuthority('GROUP_admin')")
  public ResponseEntity<List<Bill>> getAll() {
    return ResponseEntity.ok().body(service.getAllBill());
  }

  @GetMapping(path ="/detail/{id}")
  public Map<String, Object> detailSecure(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient
          , Authentication auth
          , @PathVariable("id") Long idAccount) {
    Map<String, Object> response = new HashMap<>();
    response.put("clientName", authorizedClient.getClientRegistration().getClientName());
    response.put("id_account", idAccount);
    response.put("token", authorizedClient.getAccessToken().getTokenValue());

    return response;
  }

  @GetMapping("/me")
  public Bill getBill(Principal principal){
    return null;
  }

  @PostMapping()
  @PreAuthorize("hasAuthority('GROUP_provider') AND hasAuthority('SCOPE_facturacion:gestion')")
  public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
    return ResponseEntity.ok().body(service.saveBill(bill));
  }

  @GetMapping("/findBy")
  public ResponseEntity<Bill> findByCustomer(@RequestParam String customer) {
    Bill bill = service.findByCustomer(customer);
    if (bill != null) {
      return ResponseEntity.ok().body(bill);
    }
    return ResponseEntity.notFound().build();
  }

}

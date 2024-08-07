package com.aarmas.tiendaNube.controllers;

import com.aarmas.tiendaNube.DTOs.ContactsPricingDTO;
import com.aarmas.tiendaNube.exceptions.CustomException;
import com.aarmas.tiendaNube.models.ContactsPricing;
import com.aarmas.tiendaNube.models.EmailPricing;
import com.aarmas.tiendaNube.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @GetMapping("/contacts/list")
    public ResponseEntity<Object> listContacts() {
        try {
            List<ContactsPricing> pricingList = pricingService.getAllContacts();
            if (pricingList.isEmpty()) {
                return new ResponseEntity<>("No pricing found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(pricingList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/contacts/create")
    public ResponseEntity<Object> createContact(@RequestBody ContactsPricingDTO pricing) {
        try {
            ContactsPricing createdPricing = pricingService.createPricing(pricing);
            return new ResponseEntity<>(createdPricing, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emails/list")
    public ResponseEntity<Object> listEmail() {
        try {
            List<EmailPricing> pricingList = pricingService.getAllEmail();
            if (pricingList.isEmpty()) {
                return new ResponseEntity<>("No pricing found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(pricingList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/emails/create")
    public ResponseEntity<Object> createEmail(@RequestBody EmailPricing pricing) {
        try {
            EmailPricing createdPricing = pricingService.createEmailPricing(pricing);
            return new ResponseEntity<>(createdPricing, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
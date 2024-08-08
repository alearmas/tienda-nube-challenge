package com.aarmas.tiendaNube.services;

import com.aarmas.tiendaNube.DTOs.ContactsPricingDTO;
import com.aarmas.tiendaNube.exceptions.CustomException;
import com.aarmas.tiendaNube.models.ContactsPricing;
import com.aarmas.tiendaNube.models.EmailPricing;
import com.aarmas.tiendaNube.repositories.ContactsRepository;
import com.aarmas.tiendaNube.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class PricingService {

    @Autowired
    private ContactsRepository contactsRepository;
    @Autowired
    private EmailRepository emailRepository;

    public List<ContactsPricing> getAllContacts() {
        return contactsRepository.findAll();
    }

    public List<EmailPricing> getAllEmail() {
        return emailRepository.findAll();
    }

    public ContactsPricing createPricing(ContactsPricingDTO dto) throws CustomException {
        if (dto.getContactsAvailable() % 10 != 0) {
            throw new CustomException("The value of contactsAvailable must be a multiple of 10.");
        }

        int emailsAvailable = dto.getContactsAvailable() < 100000 ? 0 : dto.getContactsAvailable() * 12;

        Optional<ContactsPricing> existingContactsPricing = contactsRepository.findByContactsAvailable(dto.getContactsAvailable());
        Optional<ContactsPricing> existingEmailsPricing = contactsRepository.findByEmailsAvailable(emailsAvailable);

        if (existingContactsPricing.isPresent() || existingEmailsPricing.isPresent()) {
            throw new CustomException("A pricing with the same contactsAvailable or emailsAvailable already exists.");
        }

        ContactsPricing pricing = new ContactsPricing(
                dto.getContactsAvailable(),
                emailsAvailable,
                dto.getPrice().setScale(2, RoundingMode.HALF_UP)
        );

        return contactsRepository.save(pricing);
    }

    public EmailPricing createEmailPricing(EmailPricing pricing) throws CustomException {
        if (pricing.getEmailsAvailable() % 10 != 0) {
            throw new CustomException("The value of emailsAvailable must be a multiple of 10.");
        }

        Optional<EmailPricing> existingEmailsPricing = emailRepository.findByEmailsAvailable(pricing.getEmailsAvailable());

        if (existingEmailsPricing.isPresent()) {
            throw new CustomException("A pricing with the same emailsAvailable already exists.");
        }

        pricing.setPrice(pricing.getPrice().setScale(2, RoundingMode.HALF_UP));
        return emailRepository.save(pricing);
    }

}

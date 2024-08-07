package com.aarmas.tiendaNube;

import com.aarmas.tiendaNube.DTOs.ContactsPricingDTO;
import com.aarmas.tiendaNube.exceptions.CustomException;
import com.aarmas.tiendaNube.models.ContactsPricing;
import com.aarmas.tiendaNube.models.EmailPricing;
import com.aarmas.tiendaNube.repositories.ContactsRepository;
import com.aarmas.tiendaNube.repositories.EmailRepository;
import com.aarmas.tiendaNube.services.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PricingServiceTest {

    @Mock
    private ContactsRepository contactsRepository;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private PricingService pricingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateContactsPricing_Success() {
        ContactsPricingDTO pricing = addContactDTO(100000, 1, BigDecimal.valueOf(100));

        when(contactsRepository.findByContactsAvailable(pricing.getContactsAvailable())).thenReturn(Optional.empty());
        when(contactsRepository.findByEmailsAvailable(pricing.getEmailsAvailable())).thenReturn(Optional.empty());
        when(contactsRepository.save(any(ContactsPricing.class))).thenAnswer(i -> i.getArguments()[0]);

        ContactsPricing createdPricing = pricingService.createPricing(pricing);

        assertNotNull(createdPricing);
        assertEquals(1200000, createdPricing.getEmailsAvailable());
        verify(contactsRepository, times(1)).save(createdPricing);
    }

    @Test
    public void testCreateContactsPricing_Failure_NotMultipleOfTen() {
        ContactsPricingDTO pricing = addContactDTO(12345, 100, BigDecimal.valueOf(100));

        CustomException exception = assertThrows(CustomException.class, () -> {
            pricingService.createPricing(pricing);
        });

        assertEquals("The value of contactsAvailable must be a multiple of 10.", exception.getMessage());
    }

    @Test
    public void testGetAllContactsPricings() {
        when(contactsRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContactsPricing> pricingList = pricingService.getAllContacts();

        assertTrue(pricingList.isEmpty());
        verify(contactsRepository, times(1)).findAll();
    }

    @Test
    public void testCreateEmailPricing_Success() {
        EmailPricing emailPricing = addEmailPricing(1000, BigDecimal.valueOf(100));
        when(emailRepository.findByEmailsAvailable(emailPricing.getEmailsAvailable())).thenReturn(Optional.empty());
        when(emailRepository.save(any(EmailPricing.class))).thenReturn(emailPricing);

        EmailPricing result = pricingService.createEmailPricing(emailPricing);

        assertNotNull(result);
        assertEquals(emailPricing.getEmailsAvailable(), result.getEmailsAvailable());
        assertEquals(emailPricing.getPrice().setScale(2, RoundingMode.HALF_UP), result.getPrice());
        verify(emailRepository, times(1)).findByEmailsAvailable(emailPricing.getEmailsAvailable());
        verify(emailRepository, times(1)).save(emailPricing);
    }

    @Test
    public void testCreateEmailPricing_Fails_InvalidEmailsAvailable() {
        EmailPricing emailPricing = addEmailPricing(123, BigDecimal.valueOf(100));

        CustomException exception = assertThrows(CustomException.class, () ->
                pricingService.createEmailPricing(emailPricing));

        assertEquals("The value of emailsAvailable must be a multiple of 10.", exception.getMessage());
        verify(emailRepository, never()).findByEmailsAvailable(emailPricing.getEmailsAvailable());
        verify(emailRepository, never()).save(emailPricing);
    }

    @Test
    public void testCreateEmailPricing_Fails_EmailsAvailableExists() {
        EmailPricing emailPricing = addEmailPricing(1000, BigDecimal.valueOf(100));
        when(emailRepository.findByEmailsAvailable(emailPricing.getEmailsAvailable())).thenReturn(Optional.of(emailPricing));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                pricingService.createEmailPricing(emailPricing));

        assertEquals("A pricing with the same emailsAvailable already exists.", exception.getMessage());
        verify(emailRepository, times(1)).findByEmailsAvailable(emailPricing.getEmailsAvailable());
        verify(emailRepository, never()).save(emailPricing);
    }

    private static ContactsPricingDTO addContactDTO(int contactsAvailable, int emailsAvailable, BigDecimal price) {
        ContactsPricingDTO pricing = new ContactsPricingDTO();
        pricing.setContactsAvailable(contactsAvailable);
        pricing.setEmailsAvailable(emailsAvailable);
        pricing.setPrice(price);
        return pricing;
    }

    private static EmailPricing addEmailPricing(int emailAvailable, BigDecimal price) {
        EmailPricing emailPricing = new EmailPricing();
        emailPricing.setEmailsAvailable(emailAvailable);
        emailPricing.setPrice(price);
        return emailPricing;
    }
}

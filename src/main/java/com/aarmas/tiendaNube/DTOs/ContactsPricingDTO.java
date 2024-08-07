package com.aarmas.tiendaNube.DTOs;

import java.math.BigDecimal;

public class ContactsPricingDTO {
    private int contactsAvailable;
    private int emailsAvailable;
    private BigDecimal price;

    public int getContactsAvailable() {
        return contactsAvailable;
    }

    public void setContactsAvailable(int contactsAvailable) {
        this.contactsAvailable = contactsAvailable;
    }

    public int getEmailsAvailable() {
        return emailsAvailable;
    }

    public void setEmailsAvailable(int emailsAvailable) {
        this.emailsAvailable = emailsAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
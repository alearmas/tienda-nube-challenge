package com.aarmas.tiendaNube.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "contacts_pricing")
public class ContactsPricing {

    @Id
    @Indexed(unique=true)
    private String id;
    private Integer contactsAvailable;
    private Integer emailsAvailable;
    private BigDecimal price;
    private String currencyBase = "USD";

    public ContactsPricing() {
    }

    public ContactsPricing(int contactsAvailable, int emailsAvailable, BigDecimal price) {
        this.contactsAvailable = contactsAvailable;
        this.emailsAvailable = emailsAvailable;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Integer getContactsAvailable() {
        return contactsAvailable;
    }

    public void setContactsAvailable(Integer contactsAvailable) {
        this.contactsAvailable = contactsAvailable;
    }

    public Integer getEmailsAvailable() {
        return emailsAvailable;
    }

    public void setEmailsAvailable(Integer emailsAvailable) {
        this.emailsAvailable = emailsAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ContactsPricing{" +
                "id = " + id +
                ", contactsAvailable = " + contactsAvailable +
                ", emailsAvailable = " + emailsAvailable +
                ", price = '" + currencyBase + price + '\'' +
                '}';
    }
}
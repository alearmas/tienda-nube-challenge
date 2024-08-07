package com.aarmas.tiendaNube.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "emailPricing")
public class EmailPricing {

    @Id
    @Indexed(unique=true)
    private String id;
    private Integer emailsAvailable;
    private BigDecimal price;
    private String currencyBase = "USD";

    public EmailPricing() {
    }

    public EmailPricing(int emailsAvailable, BigDecimal price) {
        this.emailsAvailable = emailsAvailable;
        this.price = price;
    }

    public String getId() {
        return id;
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
}
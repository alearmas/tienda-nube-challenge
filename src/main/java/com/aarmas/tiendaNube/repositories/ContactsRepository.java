package com.aarmas.tiendaNube.repositories;

import com.aarmas.tiendaNube.models.ContactsPricing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactsRepository extends MongoRepository<ContactsPricing, UUID> {
    Optional<ContactsPricing> findByContactsAvailable(int contactsAvailable);
    Optional<ContactsPricing> findByEmailsAvailable(int emailsAvailable);
}

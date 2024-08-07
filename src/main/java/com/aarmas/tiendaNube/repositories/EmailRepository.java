package com.aarmas.tiendaNube.repositories;

import com.aarmas.tiendaNube.models.EmailPricing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends MongoRepository<EmailPricing, UUID> {
    Optional<EmailPricing> findByEmailsAvailable(int emailsAvailable);
}

package com.example.notification_service.repository;

import com.example.notification_service.model.MailConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MailConfigRepository extends MongoRepository<MailConfig, String> {
    Optional<MailConfig> findFirstBy(); 
}
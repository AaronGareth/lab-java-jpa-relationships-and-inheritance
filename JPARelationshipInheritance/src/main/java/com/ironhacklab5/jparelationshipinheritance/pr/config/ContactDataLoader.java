package com.lab.pr.config;

import com.ironhacklab5.jparelationshipinheritance.pr.entity.Contact;
import com.ironhacklab5.jparelationshipinheritance.pr.entity.Name;
import com.ironhacklab5.jparelationshipinheritance.pr.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds the database with sample PR Contact data on application startup.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ContactDataLoader implements CommandLineRunner {

    private final ContactRepository contactRepository;

    @Override
    public void run(String... args) {
        log.info("--- Seeding PR Contact System ---");

        Contact lawyer = Contact.builder()
                .company("Harmon & Associates")
                .title("Senior Partner")
                .name(Name.builder()
                        .salutation("Dr.")
                        .firstName("Eleanor")
                        .middleName("Grace")
                        .lastName("Harmon")
                        .build())
                .build();

        Contact prDirector = Contact.builder()
                .company("Blue Sky Media")
                .title("Director of Public Relations")
                .name(Name.builder()
                        .salutation("Ms.")
                        .firstName("Priya")
                        .middleName("Ann")
                        .lastName("Kapoor")
                        .build())
                .build();

        Contact pressOfficer = Contact.builder()
                .company("Ministry of Communications")
                .title("Press Officer")
                .name(Name.builder()
                        .salutation("Mr.")
                        .firstName("James")
                        .middleName("Edward")
                        .lastName("Fletcher")
                        .build())
                .build();

        contactRepository.save(lawyer);
        contactRepository.save(prDirector);
        contactRepository.save(pressOfficer);

        log.info("Saved {} contacts.", contactRepository.count());
    }
}
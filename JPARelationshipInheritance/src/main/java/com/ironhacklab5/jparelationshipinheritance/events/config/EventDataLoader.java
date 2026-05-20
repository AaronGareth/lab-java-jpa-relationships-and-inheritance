package com.ironhacklab5.jparelationshipinheritance.events.config;


import com.ironhacklab5.jparelationshipinheritance.events.entity.*;
import com.ironhacklab5.jparelationshipinheritance.events.repository.ConferenceRepository;
import com.ironhacklab5.jparelationshipinheritance.events.repository.ExhibitionRepository;
import com.ironhacklab5.jparelationshipinheritance.events.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Seeds the Event Management module with sample conferences and exhibitions.
 *
 * Important: Speakers are saved FIRST via speakerRepository.save() so that
 * JPA has managed instances before we attach them to conferences.
 * Reusing a detached entity (one saved in a previous transaction) in a
 * @ManyToMany with CascadeType.PERSIST causes a "detached entity" error.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class EventDataLoader implements CommandLineRunner {

    private final ConferenceRepository conferenceRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final SpeakerRepository speakerRepository;

    @Override
    public void run(String... args) {
        log.info("--- Seeding Event Management System ---");

        // Save speakers FIRST and use the returned managed instances.
        // This avoids the "detached entity passed to persist" error when
        // the same speaker (e.g. speakerBob) is reused across conferences.
        Speaker speakerAlice = speakerRepository.save(
                Speaker.builder()
                        .name("Dr. Alice Monroe")
                        .presentationDuration(45)
                        .build());

        Speaker speakerBob = speakerRepository.save(
                Speaker.builder()
                        .name("Prof. Bob Nakamura")
                        .presentationDuration(60)
                        .build());

        Speaker speakerCarla = speakerRepository.save(
                Speaker.builder()
                        .name("Carla Esposito")
                        .presentationDuration(30)
                        .build());

        // Conference 1 — speakerAlice and speakerBob presenting
        Conference techConference = Conference.builder()
                .title("Global Tech Summit 2025")
                .date(LocalDate.of(2025, 9, 15))
                .duration(480)
                .location("Convention Center, Madrid")
                .guests(List.of(
                        Guest.builder().name("James Walton").status(GuestStatus.ATTENDING).build(),
                        Guest.builder().name("Yuki Tanaka").status(GuestStatus.NO_RESPONSE).build(),
                        Guest.builder().name("Fatima Al-Hassan").status(GuestStatus.ATTENDING).build()
                ))
                .speakers(List.of(speakerAlice, speakerBob))
                .build();

        // Conference 2 — speakerBob reused here (Many-to-Many in action)
        Conference healthConference = Conference.builder()
                .title("International Healthcare Innovation Forum")
                .date(LocalDate.of(2025, 11, 3))
                .duration(360)
                .location("Grand Hotel, Barcelona")
                .guests(List.of(
                        Guest.builder().name("Dr. Lena Becker").status(GuestStatus.ATTENDING).build(),
                        Guest.builder().name("Omar Khalid").status(GuestStatus.NOT_ATTENDING).build()
                ))
                .speakers(List.of(speakerBob, speakerCarla))
                .build();

        // Exhibition — no speakers, just guests
        Exhibition artExhibition = Exhibition.builder()
                .title("Modern Art Showcase 2025")
                .date(LocalDate.of(2025, 8, 20))
                .duration(720)
                .location("National Gallery, Bilbao")
                .guests(List.of(
                        Guest.builder().name("Sophie Martin").status(GuestStatus.ATTENDING).build(),
                        Guest.builder().name("Ravi Sharma").status(GuestStatus.NO_RESPONSE).build(),
                        Guest.builder().name("Emma Clarke").status(GuestStatus.NOT_ATTENDING).build()
                ))
                .build();

        conferenceRepository.save(techConference);
        conferenceRepository.save(healthConference);
        exhibitionRepository.save(artExhibition);

        log.info("Saved {} conferences, {} exhibitions.",
                conferenceRepository.count(), exhibitionRepository.count());
    }
}
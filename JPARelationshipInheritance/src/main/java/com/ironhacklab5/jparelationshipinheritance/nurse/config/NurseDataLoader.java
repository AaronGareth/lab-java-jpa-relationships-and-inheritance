package com.ironhacklab5.jparelationshipinheritance.nurse.config;

import com.ironhacklab5.jparelationshipinheritance.nurse.entity.Association;
import com.ironhacklab5.jparelationshipinheritance.nurse.entity.Division;
import com.ironhacklab5.jparelationshipinheritance.nurse.entity.Member;
import com.ironhacklab5.jparelationshipinheritance.nurse.entity.MemberStatus;
import com.ironhacklab5.jparelationshipinheritance.nurse.repository.AssociationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

/**
 * Seeds the Nurse Association module with one full Association,
 * 7 Divisions, each having a president and at least one member.
 *
 * Because cascade = ALL is configured on Association -> Division -> Member,
 * a single associationRepository.save() persists the entire object graph.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class NurseDataLoader implements CommandLineRunner {

    private final AssociationRepository associationRepository;

    @Override
    public void run(String... args) {
        log.info("--- Seeding Nurse Association System ---");

        Association association = Association.builder()
                .name("Nurse Association of Spain")
                .divisions(List.of(
                        buildDivision("Northern Division", "Galicia",
                                buildMember("Rosa López",   MemberStatus.ACTIVE, 2025, 6, 1),
                                buildMember("Carlos Vega",  MemberStatus.ACTIVE, 2025, 8, 15),
                                buildMember("Inés Romero",  MemberStatus.LAPSED, 2024, 12, 1)),
                        buildDivision("Southern Division", "Andalusia",
                                buildMember("Miguel Ruiz",  MemberStatus.ACTIVE, 2025, 9, 1),
                                buildMember("Lucia Blanco", MemberStatus.ACTIVE, 2026, 1, 1)),
                        buildDivision("Eastern Division", "Catalonia",
                                buildMember("Ana Puig",     MemberStatus.ACTIVE, 2025, 7, 1),
                                buildMember("David Font",   MemberStatus.LAPSED, 2024, 11, 1)),
                        buildDivision("Western Division", "Extremadura",
                                buildMember("Sara Mora",    MemberStatus.ACTIVE, 2025, 10, 1),
                                buildMember("Pedro Gil",    MemberStatus.ACTIVE, 2026, 3, 1)),
                        buildDivision("Central Division", "Castilla",
                                buildMember("Elena Sanz",   MemberStatus.ACTIVE, 2025, 5, 1),
                                buildMember("Tomás Reyes",  MemberStatus.LAPSED, 2024, 10, 1)),
                        buildDivision("Island Division", "Canary Islands",
                                buildMember("Marta León",   MemberStatus.ACTIVE, 2025, 11, 1),
                                buildMember("Juan Díaz",    MemberStatus.ACTIVE, 2026, 2, 1)),
                        buildDivision("Capital Division", "Madrid",
                                buildMember("Nuria Torres", MemberStatus.ACTIVE, 2025, 4, 1),
                                buildMember("Álvaro Cruz",  MemberStatus.ACTIVE, 2025, 12, 1),
                                buildMember("Claudia Méndez", MemberStatus.LAPSED, 2024, 9, 1))
                ))
                .build();

        associationRepository.save(association);
        log.info("Saved association '{}' with {} divisions.",
                association.getName(), association.getDivisions().size());
    }

    private Division buildDivision(String name, String district, Member... members) {
        Member president = members[0];
        List<Member> memberList = new java.util.ArrayList<>();
        for (int i = 1; i < members.length; i++) memberList.add(members[i]);
        return Division.builder()
                .name(name).district(district)
                .president(president).members(memberList).build();
    }

    private Member buildMember(String name, MemberStatus status, int y, int m, int d) {
        return Member.builder()
                .name(name).status(status)
                .renewalDate(LocalDate.of(y, m, d)).build();
    }
}

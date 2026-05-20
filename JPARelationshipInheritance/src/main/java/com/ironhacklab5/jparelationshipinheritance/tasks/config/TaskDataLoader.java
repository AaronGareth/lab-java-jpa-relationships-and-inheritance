package com.ironhacklab5.jparelationshipinheritance.tasks.config;


import com.ironhacklab5.jparelationshipinheritance.tasks.entity.BillableTask;
import com.ironhacklab5.jparelationshipinheritance.tasks.entity.InternalTask;
import com.ironhacklab5.jparelationshipinheritance.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Seeds the Task Management module with sample billable and internal tasks.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TaskDataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        log.info("--- Seeding Task Management System ---");

        taskRepository.save(BillableTask.builder()
                .title("Q3 Client Financial Report")
                .dueDate(LocalDate.of(2025, 9, 30))
                .completed(false)
                .hourlyRate(new BigDecimal("120.00")).build());

        taskRepository.save(BillableTask.builder()
                .title("Client Website Redesign")
                .dueDate(LocalDate.of(2025, 10, 15))
                .completed(true)
                .hourlyRate(new BigDecimal("95.50")).build());

        taskRepository.save(BillableTask.builder()
                .title("Contract Legal Review")
                .dueDate(LocalDate.of(2025, 8, 1))
                .completed(true)
                .hourlyRate(new BigDecimal("200.00")).build());

        taskRepository.save(InternalTask.builder()
                .title("Weekly Team Standup Preparation")
                .dueDate(LocalDate.of(2025, 9, 1))
                .completed(false).build());

        taskRepository.save(InternalTask.builder()
                .title("Monthly Server Maintenance")
                .dueDate(LocalDate.of(2025, 9, 20))
                .completed(false).build());

        taskRepository.save(InternalTask.builder()
                .title("New Developer Onboarding")
                .dueDate(LocalDate.of(2025, 9, 5))
                .completed(true).build());

        log.info("Saved {} tasks total.", taskRepository.count());
    }
}
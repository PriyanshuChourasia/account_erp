package com.codymitra.shared_service.seeders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Runs registered {@link Seeder}s.
 *
 * Triggers:
 *   --seed                          run every seeder
 *   --seed=account_nature,bank_type run only the named seeders
 *   app.seed.enabled=true           run every seeder on each startup
 *   (no flag)                       run only seeders with runOnStartup() = true
 *
 * Or call {@link #seedAll()} / {@link #seed(Collection)} from code (tests, admin endpoint).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements ApplicationRunner {

    private final List<Seeder> seeders;
    private final TransactionTemplate transactionTemplate;

    @Value("${app.seed.enabled:false}")
    private boolean seedOnStartup;

    @Override
    public void run(ApplicationArguments args) {
        if (args.containsOption("seed")) {
            Set<String> names = args.getOptionValues("seed").stream()
                    .flatMap(value -> Arrays.stream(value.split(",")))
                    .map(String::trim)
                    .filter(name -> !name.isEmpty())
                    .collect(Collectors.toSet());

            if (names.isEmpty()) {
                seedAll();
            } else {
                seed(names);
            }
        } else if (seedOnStartup) {
            seedAll();
        } else {
            runSeeders(seeders.stream().filter(Seeder::runOnStartup).toList());
        }
    }

    public void seedAll() {
        runSeeders(seeders);
    }

    public void seed(Collection<String> names) {
        Set<String> available = seeders.stream().map(Seeder::name).collect(Collectors.toSet());
        names.stream()
                .filter(name -> !available.contains(name))
                .forEach(name -> log.warn("No seeder named '{}'. Available: {}", name, available));

        runSeeders(seeders.stream().filter(seeder -> names.contains(seeder.name())).toList());
    }

    private void runSeeders(List<Seeder> toRun) {
        toRun.stream()
                .sorted(Comparator.comparingInt(Seeder::order))
                .forEach(seeder -> {
                    log.info("Seeding: {}", seeder.name());
                    transactionTemplate.executeWithoutResult(status -> seeder.seed());
                });
        log.info("Seeding finished ({} seeder(s))", toRun.size());
    }
}

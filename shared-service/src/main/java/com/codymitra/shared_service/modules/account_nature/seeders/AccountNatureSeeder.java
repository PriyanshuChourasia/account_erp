package com.codymitra.shared_service.modules.account_nature.seeders;

import com.codymitra.shared_service.modules.account_nature.entities.AccountNatureEntity;
import com.codymitra.shared_service.modules.account_nature.repositories.AccountNatureRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountNatureSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/account_nature/data/account_nature.json";

    private final AccountNatureRepository accountNatureRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "account_nature";
    }

    @Override
    public int order() {
        return 10;
    }

    @Override
    public boolean runOnStartup() {
        return false;
    }

    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, AccountNatureEntity.class).stream()
                .filter(accountNature -> !accountNatureRepository.existsByName(accountNature.getName()))
                .forEach(accountNatureRepository::save);
    }
}

package com.codymitra.shared_service.modules.gender.seeder;

import com.codymitra.shared_service.modules.gender.entities.GenderEntity;
import com.codymitra.shared_service.modules.gender.repositories.GenderRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenderSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/gender/data/gender.json";

    private final GenderRepository genderRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "gender";
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
        seedDataReader.readList(DATA_FILE, GenderEntity.class).stream()
                .filter(gender -> !genderRepository.existsByName(gender.getName()))
                .forEach(genderRepository::save);
    }
}

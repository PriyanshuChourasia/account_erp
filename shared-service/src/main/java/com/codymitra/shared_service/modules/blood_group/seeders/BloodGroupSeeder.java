package com.codymitra.shared_service.modules.blood_group.seeders;

import com.codymitra.shared_service.modules.blood_group.entities.BloodGroupEntity;
import com.codymitra.shared_service.modules.blood_group.repositories.BloodGroupRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BloodGroupSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/blood_group/data/blood_group.json";

    private final BloodGroupRepository bloodGroupRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "blood_group";
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
        seedDataReader.readList(DATA_FILE, BloodGroupEntity.class).stream()
                .filter(bloodGroup -> !bloodGroupRepository.existsByName(bloodGroup.getName()))
                .forEach(bloodGroupRepository::save);
    }
}

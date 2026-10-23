package com.codymitra.shared_service.modules.user_type.seeders;

import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import com.codymitra.shared_service.modules.user_type.repositories.UserTypeRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTypeSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/user_type/data/user_type.json";

    private final UserTypeRepository userTypeRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "user_type";
    }

    @Override
    public int order() {
        return 5;
    }

    @Override
    public boolean runOnStartup() {
        // registration needs the ADMIN row, so always make sure these exist
        return false;
    }

    @Override
    public void seed() {
        // name and code are both unique, so skip a row if either already exists
        seedDataReader.readList(DATA_FILE, UserTypeEntity.class).stream()
                .filter(userType -> !userTypeRepository.existsByCode(userType.getCode()))
                .filter(userType -> !userTypeRepository.existsByName(userType.getName()))
                .forEach(userTypeRepository::save);
    }
}

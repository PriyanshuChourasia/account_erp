package com.codymitra.shared_service.modules.designation.seeders;


import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.designation.repositories.DesignationRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DesignationSeeder implements Seeder {
    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/designation/data/designation.json";

    private final DesignationRepository designationRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name(){
        return "designation";
    }

    @Override
    public int order(){
        return 21;
    }

    @Override
    public boolean runOnStartup(){
        return false;
    }

    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, DesignationSeed.class)
                .forEach(seed -> createDesignation(seed, null));
    }

    private void createDesignation(DesignationSeed seed, DesignationEntity parent) {
        // an existing row is reused as the parent, so missing children further down still get created
        DesignationEntity designationEntity = designationRepository.findByCode(seed.code()).orElse(null);

        if (designationEntity == null) {
            if (designationRepository.existsByName(seed.name())) {
                log.warn("Designation '{}' already exists with a different code, skipping it and its children", seed.name());
                return;
            }
            designationEntity = new DesignationEntity();
            designationEntity.setName(seed.name());
            designationEntity.setCode(seed.code());
            designationEntity.setDescription(seed.description());
            designationEntity.setActive(true);
            designationEntity.setParentId(parent != null ? parent.getId() : null);
            designationEntity = designationRepository.save(designationEntity);
        }

        if(seed.children != null){
            for(DesignationSeed child: seed.children){
                createDesignation(child,designationEntity);
            }
        }

    }

    private record DesignationSeed(
            String name,
            String code,
            List<DesignationSeed> children,
            String description
    ) {
    }
}

package com.codymitra.shared_service.modules.unique_quantity_code.seeders;

import com.codymitra.shared_service.modules.unique_quantity_code.entities.UniqueQuantityCodeEntity;
import com.codymitra.shared_service.modules.unique_quantity_code.repositories.UniqueQuantityCodeRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UQCSeeder implements Seeder {

    private final static String DATA_FILE = "com/codymitra/shared_service/modules/unique_quantity_code/data/uqc.json";

    private final UniqueQuantityCodeRepository uniqueQuantityCodeRepository;
    private final SeedDataReader seedDataReader;


    @Override
    public String name(){
        return "UQC Seeder";
    }

    @Override
    public int order(){
        return 38;
    }

    @Override
    public boolean runOnStartup(){
        return true;
    }


    @Override
    public void seed(){
        seedDataReader.readList(DATA_FILE, UQCode.class).stream()
                .filter(uqcSeeder -> !uniqueQuantityCodeRepository.existsByName(uqcSeeder.name()))
                .filter(uqcSeeder -> !uniqueQuantityCodeRepository.existsByCode(uqcSeeder.code()))
                .forEach(this::createUQC);
    }


    private void createUQC(UQCode uqCode){
        UniqueQuantityCodeEntity uniqueQuantityCode = new UniqueQuantityCodeEntity();
        uniqueQuantityCode.setName(uqCode.name());
        uniqueQuantityCode.setCode(uqCode.code());
        uniqueQuantityCode.setAlias(uqCode.alias());
        uniqueQuantityCode.setDescription(uqCode.description());
        uniqueQuantityCodeRepository.save(uniqueQuantityCode);
    }


    private record UQCode(
            String name,
            String code,
            String alias,
            String description
    ){}
}

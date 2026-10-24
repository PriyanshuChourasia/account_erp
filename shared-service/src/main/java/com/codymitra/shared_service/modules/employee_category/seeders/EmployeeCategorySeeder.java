package com.codymitra.shared_service.modules.employee_category.seeders;

import com.codymitra.shared_service.modules.employee_category.entities.EmployeeCategoryEntity;
import com.codymitra.shared_service.modules.employee_category.repositories.EmployeeCategoryRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeCategorySeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/employee_category/data/employee_category.json";

    private final EmployeeCategoryRepository employeeCategoryRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name(){
        return "employee_category";
    }

    @Override
    public int order(){
        return 22;
    }

    @Override
    public boolean runOnStartup(){
        return false;
    }

    @Override
    public void seed(){
        seedDataReader.readList(DATA_FILE,EmployeeCategorySeed.class).stream()
                .filter(seed -> !employeeCategoryRepository.existsByName(seed.name()))
                .filter(seed -> !employeeCategoryRepository.existsByAlias(seed.alias()))
                .forEach(this::createEmployeeCategory);
    }


    private void createEmployeeCategory(EmployeeCategorySeed seed){
        EmployeeCategoryEntity categoryEntity = new EmployeeCategoryEntity();
        categoryEntity.setName(seed.name());
        categoryEntity.setAlias(seed.alias());
        categoryEntity.setDescription(seed.description());
        categoryEntity.setActive(true);
        categoryEntity.setAllocateRevenueItem(seed.allocatedRevenue());
        categoryEntity.setAllocateNonRevenueItem(seed.allocatedNonRevenue());
        employeeCategoryRepository.save(categoryEntity);
    }

    private record EmployeeCategorySeed(
            String name,
            String alias,
            String description,
            Boolean allocatedRevenue,
            Boolean allocatedNonRevenue
    ){

    }

}

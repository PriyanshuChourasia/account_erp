package com.codymitra.shared_service.modules.department.seeders;

import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.department.repositories.DepartmentRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Demo departments. Children point to their parent by parentCode, so parents must come first in the JSON.
 * Not run on startup: use --seed=department.
 */
@Component
@RequiredArgsConstructor
public class DepartmentSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/department/data/department.json";

    private final DepartmentRepository departmentRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "department";
    }

    @Override
    public int order() {
        return 20;
    }

    @Override
    public boolean runOnStartup(){
        return false;
    }

    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, DepartmentSeed.class).stream()
                .filter(seed -> !departmentRepository.existsByCode(seed.code()))
                .filter(seed -> !departmentRepository.existsByName(seed.name()))
                .forEach(this::createDepartment);
    }

    private void createDepartment(DepartmentSeed seed) {
        DepartmentEntity department = new DepartmentEntity();
        department.setName(seed.name());
        department.setCode(seed.code());
        department.setDescription(seed.description());
        department.setActive(true);

        if (seed.parentCode() != null) {
            DepartmentEntity parent = departmentRepository.findByCode(seed.parentCode()).orElseThrow(
                    () -> new IllegalStateException(
                            "Parent department " + seed.parentCode() + " not found for " + seed.code()
                                    + ". Put parents before children in department.json")
            );
            department.setParentId(parent.getId());
        }

        departmentRepository.save(department);
    }

    private record DepartmentSeed(
            String name,
            String code,
            String parentCode,
            String description
    ) {
    }
}

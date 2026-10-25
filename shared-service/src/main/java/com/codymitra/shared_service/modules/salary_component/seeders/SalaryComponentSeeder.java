package com.codymitra.shared_service.modules.salary_component.seeders;

import com.codymitra.shared_service.modules.salary_component.entities.SalaryComponentEntity;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryCalculationTypeEnum;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryComponentTypeEnum;
import com.codymitra.shared_service.modules.salary_component.repositories.SalaryComponentRepository;
import com.codymitra.shared_service.seeders.SeedDataReader;
import com.codymitra.shared_service.seeders.Seeder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalaryComponentSeeder implements Seeder {

    private static final String DATA_FILE =
            "com/codymitra/shared_service/modules/salary_component/data/salary_component.json";

    private final SalaryComponentRepository salaryComponentRepository;
    private final SeedDataReader seedDataReader;

    @Override
    public String name() {
        return "salary_component";
    }

    @Override
    public int order() {
        return 23;
    }

    @Override
    public boolean runOnStartup() {
        return false;
    }

    // base components must appear in the JSON before the components calculated on them
    @Override
    public void seed() {
        seedDataReader.readList(DATA_FILE, SalaryComponentSeed.class).stream()
                .filter(seed -> !salaryComponentRepository.existsByName(seed.name()))
                .filter(seed -> !salaryComponentRepository.existsByCode(seed.code()))
                .forEach(this::createSalaryComponent);
    }

    private void createSalaryComponent(SalaryComponentSeed seed) {
        UUID baseComponentId = null;
        if (seed.baseComponentCode() != null) {
            SalaryComponentEntity base = salaryComponentRepository.findByCode(seed.baseComponentCode()).orElse(null);
            if (base == null) {
                log.warn("Skipping salary component {}: base component {} not found", seed.code(), seed.baseComponentCode());
                return;
            }
            baseComponentId = base.getId();
        }

        SalaryComponentEntity entity = new SalaryComponentEntity();
        entity.setName(seed.name());
        entity.setCode(seed.code());
        entity.setDescription(seed.description());
        entity.setComponentType(seed.componentType());
        entity.setCalculationType(seed.calculationType());
        entity.setAmount(seed.amount());
        entity.setPercentage(seed.percentage());
        entity.setBaseComponentId(baseComponentId);
        entity.setTaxable(Boolean.TRUE.equals(seed.taxable()));
        entity.setAffectsNetSalary(seed.affectsNetSalary() == null || seed.affectsNetSalary());
        entity.setDisplayOrder(seed.displayOrder());
        entity.setActive(true);
        salaryComponentRepository.save(entity);
    }

    private record SalaryComponentSeed(
            String name,
            String code,
            String description,
            SalaryComponentTypeEnum componentType,
            SalaryCalculationTypeEnum calculationType,
            BigDecimal amount,
            BigDecimal percentage,
            String baseComponentCode,
            Boolean taxable,
            Boolean affectsNetSalary,
            Integer displayOrder
    ) {
    }
}

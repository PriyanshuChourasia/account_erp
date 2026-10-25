package com.codymitra.shared_service.modules.gender.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.gender.dtos.CreateGenderRequest;
import com.codymitra.shared_service.modules.gender.dtos.GenderDTO;
import com.codymitra.shared_service.modules.gender.entities.GenderEntity;
import com.codymitra.shared_service.modules.gender.mappers.GenderMapper;
import com.codymitra.shared_service.modules.gender.repositories.GenderRepository;
import com.codymitra.shared_service.modules.gender.services.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GenderDTO> getAll() {
        return genderRepository.findAll().stream()
                .map(GenderMapper::genderDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GenderDTO getById(UUID id) {
        return GenderMapper.genderDTO(findById(id));
    }

    @Override
    @Transactional
    public GenderDTO create(CreateGenderRequest request) {
        validateUnique(request.name().trim(), GenderMapper.resolveCode(request.code()), null);
        return GenderMapper.genderDTO(genderRepository.save(GenderMapper.genderEntity(request)));
    }

    @Override
    @Transactional
    public GenderDTO update(UUID id, CreateGenderRequest request) {
        GenderEntity entity = findById(id);
        validateUnique(request.name().trim(), GenderMapper.resolveCode(request.code()), id);
        return GenderMapper.genderDTO(genderRepository.save(GenderMapper.genderEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        genderRepository.delete(findById(id));
        return "Gender deleted successfully";
    }

    private GenderEntity findById(UUID id) {
        return genderRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such gender found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? genderRepository.existsByName(name)
                : genderRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Gender already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? genderRepository.existsByCode(code)
                : genderRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Gender already exists with this code");
        }
    }
}

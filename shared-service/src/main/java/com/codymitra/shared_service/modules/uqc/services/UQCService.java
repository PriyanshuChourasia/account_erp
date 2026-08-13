package com.codymitra.shared_service.modules.uqc.services;

import com.codymitra.shared_service.modules.uqc.dtos.CreateUQCDTO;
import com.codymitra.shared_service.modules.uqc.dtos.UQCDTO;

import java.util.List;

public interface UQCService {

    List<UQCDTO> getAll();

    UQCDTO getById(Long id);

    UQCDTO create(CreateUQCDTO request);

    UQCDTO update(Long id, CreateUQCDTO request);

    String delete(Long id);
}

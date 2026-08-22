package com.codymitra.shared_service.modules.address.services;

import com.codymitra.shared_service.modules.address.dtos.AddressDTO;
import com.codymitra.shared_service.modules.address.dtos.CreateAddressDTO;

public interface AddressService {

    AddressDTO create(CreateAddressDTO createAddressDTO, String addressableId, String addressableType);
}

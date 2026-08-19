package com.codymitra.shared_service.modules.account_group.dtos;

import java.util.UUID;
import com.codymitra.shared_service.modules.account_nature.dtos.AccountNatureDTO;

public record AccountGroupHierarchyDTO(
    UUID id,
    String name,
    Long code,
    String alias,
    String description,
    AccountNatureDTO accountNature,
    AccountGroupDTO children
) {
}

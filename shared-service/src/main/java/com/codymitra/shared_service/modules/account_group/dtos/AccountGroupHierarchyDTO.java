package com.codymitra.shared_service.modules.account_group.dtos;

import com.codymitra.shared_service.modules.accounting_nature.dtos.AccountNatureDTO;

public record AccountGroupHierarchyDTO(
    Long id,
    String name,
    Long code,
    String alias,
    String description,
    AccountNatureDTO accountNature,
    AccountGroupDTO children
) {
}

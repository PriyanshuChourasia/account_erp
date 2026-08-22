package com.codymitra.shared_service.modules.currency.mappers;

import com.codymitra.shared_service.modules.currency.dtos.CreateCurrencyDTO;
import com.codymitra.shared_service.modules.currency.dtos.CurrencyDTO;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;

public final class CurrencyMapper {

    public static CurrencyDTO currencyDTO(CurrencyEntity currency) {
        return new CurrencyDTO(
                currency.getId(),
                currency.getName(),
                currency.getCurrencySymbol(),
                currency.getDecimalPlace(),
                currency.getIsSymbolSuffix(),
                currency.getSpaceBetweenAmountAndSymbol()
        );
    }

    public static CurrencyEntity currencyEntity(CreateCurrencyDTO request) {
        CurrencyEntity currency = new CurrencyEntity();
        currency.setName(request.name());
        currency.setCurrencySymbol(request.currencySymbol());
        currency.setDecimalPlace(request.decimalPlace() != null ? request.decimalPlace() : 2);
        currency.setIsSymbolSuffix(request.isSymbolSuffix() != null ? request.isSymbolSuffix() : false);
        currency.setSpaceBetweenAmountAndSymbol(request.spaceBetweenAmountAndSymbol() != null ? request.spaceBetweenAmountAndSymbol() : true);
        return currency;
    }
}

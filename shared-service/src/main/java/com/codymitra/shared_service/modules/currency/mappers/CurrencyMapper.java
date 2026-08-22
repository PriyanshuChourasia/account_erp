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
        CurrencyEntity currency = applyRequest(new CurrencyEntity(), request);
        if (currency.getDecimalPlace() == null) {
            currency.setDecimalPlace(2);
        }
        if (currency.getIsSymbolSuffix() == null) {
            currency.setIsSymbolSuffix(false);
        }
        if (currency.getSpaceBetweenAmountAndSymbol() == null) {
            currency.setSpaceBetweenAmountAndSymbol(true);
        }
        return currency;
    }

    public static CurrencyEntity currencyEntity(CurrencyEntity currency, CreateCurrencyDTO request) {
        return applyRequest(currency, request);
    }

    private static CurrencyEntity applyRequest(CurrencyEntity currency, CreateCurrencyDTO request) {
        currency.setName(request.name());
        currency.setCurrencySymbol(request.currencySymbol());
        if (request.decimalPlace() != null) {
            currency.setDecimalPlace(request.decimalPlace());
        }
        if (request.isSymbolSuffix() != null) {
            currency.setIsSymbolSuffix(request.isSymbolSuffix());
        }
        if (request.spaceBetweenAmountAndSymbol() != null) {
            currency.setSpaceBetweenAmountAndSymbol(request.spaceBetweenAmountAndSymbol());
        }
        return currency;
    }
}

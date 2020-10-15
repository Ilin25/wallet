package ru.ilin.wallet.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TypeOperation {
    REPLENISHMENT("Пополнение"), PURCHASE("Покупка");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }


    TypeOperation(String displayValue) {
        this.displayValue = displayValue;
    }

    public static TypeOperation[] returnTypeOperations() {
        return TypeOperation.values();
    }

}


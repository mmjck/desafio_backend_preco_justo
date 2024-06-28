package com.fair_price.shop.domain.useCases.utils;

import com.fair_price.shop.adapters.gateway.database.entities.StatusDuck;

public class FormatterData {
    public static String formatterHasDiscount(boolean hasDiscount) {
        return hasDiscount ? "Com desconto": "---";
    }

    public static String formatterStatus(StatusDuck status){
        return status == StatusDuck.AVAILABLE ? "Disponível" : "Vendido";
    }


}

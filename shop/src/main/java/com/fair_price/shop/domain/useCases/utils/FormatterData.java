package com.fair_price.shop.domain.useCases.utils;

import java.text.NumberFormat;
import java.util.Locale;

import com.fair_price.shop.adapters.gateway.database.entities.StatusDuck;

public class FormatterData {
    public static String formatHasDiscount(boolean hasDiscount) {
        return hasDiscount ? "Com desconto" : "---";
    }

    public static String formatStatus(StatusDuck status) {
        return status == StatusDuck.AVAILABLE ? "Disponível" : "Vendido";
    }

    public static String formatPrice(Double value) {

        if(value == null){
            return "-";
        }
        Locale brazilLocale = new Locale("pt", "BR");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(brazilLocale);
        return currencyFormatter.format(value);

    }

}

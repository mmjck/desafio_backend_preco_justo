package com.fair_price.shop.domain.useCases.report;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.reports.impl.GenerateSheetReportGatewayImpl;

@Service
public class GenerateSheetReportUseCase {

    private final GenerateSheetReportGatewayImpl service;

    public GenerateSheetReportUseCase(GenerateSheetReportGatewayImpl service) {
        this.service = service;
    }

    public byte[] call() throws Exception {
        var response = this.service.call();
        return response;
    }
}

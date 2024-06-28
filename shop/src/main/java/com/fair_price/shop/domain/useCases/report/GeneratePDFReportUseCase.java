package com.fair_price.shop.domain.useCases.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.OrdersGateway;
import com.fair_price.shop.adapters.gateway.reports.impl.GeneratePDFReportGatewayImpl;


@Service
public class GeneratePDFReportUseCase {
    @Autowired
    GeneratePDFReportGatewayImpl reportService;

    @Autowired
    OrdersGateway gateway;


    public byte[] call() throws Exception{
        return reportService.call();
    }
}

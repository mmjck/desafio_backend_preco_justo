package com.fair_price.shop.adapters.gateway.reports.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.reports.GeneratePDFReportGateway;
import com.fair_price.shop.adapters.gateway.structures.impl.graph.GraphNode;
import com.fair_price.shop.adapters.gateway.structures.impl.graph.GraphService;
import com.fair_price.shop.domain.useCases.report.ReportModel;
import com.fair_price.shop.domain.useCases.utils.FormatterData;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class GeneratePDFReportGatewayImpl implements GeneratePDFReportGateway{
    
    private final GraphService graphService;

    GeneratePDFReportGatewayImpl(GraphService graphService) {
        this.graphService = graphService;
    }

    public byte[] call() throws Exception {
        try {
            graphService.call();
        } catch (Exception e) {
            throw e;
        }
        InputStream reportStream = getClass().getResourceAsStream("/reports/orders_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<Integer, GraphNode> graph = graphService.getStruct();
        
        
        
        List<GraphNode> roots = new ArrayList<>();
        for (GraphNode node : graph.values()) {
            if (node.getData().getParentId() == null) {
                roots.add(node);
            }
        }

        List<ReportModel> excelData = new ArrayList<>();

        for (int i = 0; i < roots.size(); i++) {
            GraphNode root = roots.get(i);
            root.collectData(i + 1, excelData, 0, "", 1);
        }

        for (ReportModel d : excelData) {
            var order = graphService.getOrderHashMap().get(d.getDuckId());
            if (order != null) {
                d.setPrice(order.getPrice());
                d.setCustomerName(order.getCustomerName() != null ? order.getCustomerName() : "--");
                d.setHasDiscount(FormatterData.formatterHasDiscount(order.getHasDiscount()));

            }
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(excelData);

        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

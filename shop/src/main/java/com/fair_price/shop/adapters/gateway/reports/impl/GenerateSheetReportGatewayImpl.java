package com.fair_price.shop.adapters.gateway.reports.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.reports.GenerateSheetReportGateway;
import com.fair_price.shop.adapters.gateway.structures.impl.graph.GraphNode;
import com.fair_price.shop.adapters.gateway.structures.impl.graph.GraphService;
import com.fair_price.shop.domain.useCases.report.ReportModel;
import com.fair_price.shop.domain.useCases.utils.FormatterData;

@Service
public class GenerateSheetReportGatewayImpl implements GenerateSheetReportGateway {


    private final GraphService graphService;


    protected Workbook workbook;
    protected Sheet sheet;

    GenerateSheetReportGatewayImpl(GraphService graphService ) {
        this.graphService = graphService;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Gerenciamento de Patos");
        
    }
   
    private void createTitle(){
        Row title = sheet.createRow(0);
        CellStyle titleStyle = createTitleCellStyle(workbook);
        title.setHeight((short) (title.getHeight() * 1.5));

        Cell titleHeader = title.createCell(1);
        titleHeader.setCellStyle(titleStyle);
        titleHeader.setCellValue("Gerenciamento de Patos".toUpperCase());


    }

    public byte[] call() throws Exception {

        createTitle();
        
        try {
            graphService.call();
        } catch (Exception e) {
            throw e;
        }

        Map<Integer, GraphNode> graph = graphService.getStruct();
        List<GraphNode> roots = new ArrayList<>();
        
        
        for (GraphNode node : graph.values()) {
            if (node.getData().getParentId() == null) {
                roots.add(node);
            }
        }

        List<ReportModel> excelData = new ArrayList<>();
        int paddingMax = 1;

        for (int i = 0; i < roots.size(); i++) {
            GraphNode root = roots.get(i);
            root.collectData(i + 1, excelData, 0, "", 1);
        }

        for (ReportModel d : excelData) {
            var order = graphService.getOrderHashMap().get(d.getDuckId());
            if (order != null) {
                d.setPrice(order.getPrice());
                d.setCustomerName(order.getCustomerName());
                d.setHasDiscount(FormatterData.formatterHasDiscount(order.getHasDiscount()));
            }

        }

        for (ReportModel d : excelData) {
            if (d.getPadding() > paddingMax) {
                paddingMax = d.getPadding();
            }
        }

        int rowNum = 2;

        for (ReportModel rowData : excelData) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            List<Cell> cells = new ArrayList<>();
            for (int i = 0; i < 5 + paddingMax; i++) {
                Cell cell = row.createCell(colNum);
                cells.add(cell);
                cell.setBlank();
                colNum++;
            }

            cells.get(rowData.getPadding()).setCellValue(rowData.getName());
            cells.get(paddingMax + 1).setCellValue(rowData.getStatus());
            cells.get(paddingMax + 2).setCellValue(rowData.getCustomerName());
            cells.get(paddingMax + 2).setCellValue(rowData.getHasDiscount());
        }



        lastConfigs(paddingMax);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();

    }

    private void lastConfigs(int paddingMax){
        Row header = sheet.createRow(1);
        CellStyle headerStyle = createHeaderCellStyle(workbook);
        String[] headers = { "Nome", "Status", "Cliente", "Tipo do cliente", "Valor"
        };
        Cell c = header.createCell(0);
        c.setCellValue(headers[0]);

        for (int i = 1; i < headers.length; i++) {
            Cell cell = header.createCell(i + paddingMax);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        for (int i = 0; i < paddingMax + headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, paddingMax));
        c.setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, paddingMax + headers.length - 1));
        sheet.autoSizeColumn(paddingMax + 3);

    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private CellStyle createTitleCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

}

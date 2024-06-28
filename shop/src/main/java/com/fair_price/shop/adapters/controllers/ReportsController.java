package com.fair_price.shop.adapters.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fair_price.shop.domain.useCases.report.GeneratePDFReportUseCase;
import com.fair_price.shop.domain.useCases.report.GenerateSheetReportUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ReportsController {
    @Autowired
    GenerateSheetReportUseCase useCase;

    @Autowired
    GeneratePDFReportUseCase generatePDFReportUseCase;

    @GetMapping("/reports")
    public ResponseEntity<Object> create(
            @RequestParam(name = "type", defaultValue = "sheet") String type) {
                
        try {
            if (type.equals("pdf")) {
                return executePDFMode();
            }
            return executeSheetMode();
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    private HttpHeaders createHeadersToSheetFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "example.xlsx");
        return headers;
    }

    private HttpHeaders createHeadersToPDFFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"orders_report.pdf\"");
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentType(MediaType.APPLICATION_PDF);
        return headers;
    }

    private ResponseEntity<Object> executePDFMode() throws Exception {
        try {
            var response = this.generatePDFReportUseCase.call();

            HttpHeaders headers = createHeadersToPDFFile();
            headers.setContentLength(response.length);
            return ResponseEntity.ok().headers(headers).body(response);
        } catch (Exception e) {
            throw e;

        }
    }

    private ResponseEntity<Object> executeSheetMode() throws Exception {
        try {
            HttpHeaders headers = createHeadersToSheetFile();
            var response = this.useCase.call();

            return ResponseEntity.ok().headers(headers).body(response);
        } catch (Exception e) {
            throw e;

        }
    }
}
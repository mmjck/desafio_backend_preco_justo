package com.fair_price.shop.adapters.gateway.structures.impl.graph;

import java.util.ArrayList;
import java.util.List;

import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;
import com.fair_price.shop.domain.useCases.report.ReportModel;
import com.fair_price.shop.domain.useCases.utils.FormatterData;

public class GraphNode {
    private DuckEntity data;
    private List<GraphNode> children;

    public GraphNode(DuckEntity data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public DuckEntity getData() {
        return data;
    }

    public List<GraphNode> getChildren() {
        return children;
    }

    public void addChild(GraphNode node) {
        this.children.add(node);
    }

    public void collectData(Integer index, List<ReportModel> excelData, int depth, String prefix, int level) {

        ReportModel model = new ReportModel();
        model.setDuckId(data.getId());
        model.setPadding(depth);

        String currentPrefix = prefix.isEmpty() ? "" : prefix + ".";
        int childLevel = 1;
        if (data.getParentId() == null) {
            model.setName("Pato" + " " + "Mãe" + " " + index);
        } else {
            model.setName("Pato" + " " + "Filho" + " " + currentPrefix);
        }

        model.setStatus(FormatterData.formatStatus(data.getStatus()));
        excelData.add(model);

        if (children.isEmpty()) {
            return;
        } else {
            for (GraphNode child : children) {
                child.collectData(index, excelData, depth + 1, currentPrefix + level, childLevel);
            }

        }

    }

}
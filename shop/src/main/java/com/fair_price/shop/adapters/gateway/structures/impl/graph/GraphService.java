package com.fair_price.shop.adapters.gateway.structures.impl.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.DuckGateway;
import com.fair_price.shop.adapters.gateway.database.OrdersGateway;
import com.fair_price.shop.adapters.gateway.database.dtos.ListOrderDTO;
import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;
import com.fair_price.shop.adapters.gateway.structures.StructureGateway;

@Service
public class GraphService implements StructureGateway<Map<Integer, GraphNode> >{

    private final OrdersGateway ordersGateway;
    private final DuckGateway duckGateway;
    
    
    private Map<Integer, GraphNode> graph;
    private HashMap<Integer, ListOrderDTO> orderHashMap;


    public GraphService(OrdersGateway ordersGateway, DuckGateway duckGateway) {
        this.ordersGateway = ordersGateway;
        this.duckGateway = duckGateway;
        graph = new HashMap<>() ;
        orderHashMap = new HashMap<>();
    }

    
    public Map<Integer, GraphNode> getStruct() {
        return this.graph;
    }


    public HashMap<Integer, ListOrderDTO> getOrderHashMap() {
        return this.orderHashMap;
    }



    public void call() throws Exception {
        List<DuckEntity> duckEntities = this.duckGateway.findAll();
        List<ListOrderDTO> orders = this.ordersGateway.findAll();

        graph.clear();
        orderHashMap.clear();

        
        orders.forEach(e -> {
            orderHashMap.put(e.getDuckId(), e);
        });

        for (DuckEntity pair : duckEntities) {
            int id = pair.getId();
            Integer parentId = pair.getParentId();

            GraphNode node = graph.getOrDefault(id, new GraphNode(pair));
            graph.put(id, node);
            if (parentId != null) {
                GraphNode parentNode = graph.get(parentId);

                if (parentNode == null) {
                    for (DuckEntity element : duckEntities) {
                        if (element.getId() == parentId) {
                            parentNode = new GraphNode(element);
                            break;
                        }
                    }
                }
                parentNode.addChild(node);
                graph.put(parentId, parentNode);        
            }
            
        }
    }

   
}

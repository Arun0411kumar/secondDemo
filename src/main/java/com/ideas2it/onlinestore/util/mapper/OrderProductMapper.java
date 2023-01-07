package com.ideas2it.onlinestore.util.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlinestore.dto.OrderProductDTO;
import com.ideas2it.onlinestore.model.OrderProduct;

import lombok.Builder;

public class OrderProductMapper {

    @Builder(builderMethodName = "DTOBuilder")
    public static OrderProductDTO convertOrderProductToOrderProductDTO(OrderProduct orderProduct) {
        OrderProductDTO orderProductDTO = null;

        if (null != orderProduct) {
            orderProductDTO = OrderProductDTO.builder()
                    .id(orderProduct.getId())
                    .quantity(orderProduct.getQuantity())
                    .product(ProductMapper.convertProductToProductDTO(orderProduct.getProduct()))
                    .build();
        }
        return orderProductDTO;
    }

    @Builder(builderMethodName = "EntityBuilder")
    public static OrderProduct convertOrderProductDTOToOrderProduct(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = null;

        if (null != orderProductDTO) {
            orderProduct = OrderProduct.builder()
                    .id(orderProductDTO.getId())
                    .quantity(orderProductDTO.getQuantity())
                    .product(ProductMapper.convertProductDTOToProduct(orderProductDTO.getProduct()))
                    .build();
        }
        return orderProduct;
    }

    public static List<OrderProduct> convertOrderProductDTOsToOrderProducts(List<OrderProductDTO> orderProductDTOs) {
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (OrderProductDTO orderProductDTO : orderProductDTOs) {
            OrderProduct orderProduct = convertOrderProductDTOToOrderProduct(orderProductDTO);
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }

    public static List<OrderProductDTO> convertOrderProductsToOrderProductDTOs(List<OrderProduct> orderProducts) {
        List<OrderProductDTO> orderProductDTOs = new ArrayList<>();

        for (OrderProduct orderProduct : orderProducts) {
            OrderProductDTO orderProductDTO = convertOrderProductToOrderProductDTO(orderProduct);
            orderProductDTOs.add(orderProductDTO);
        }
        return orderProductDTOs;
    }
}
package com.ideas2it.onlinestore.util.mapper;

import com.ideas2it.onlinestore.dto.OrderDTO;
import com.ideas2it.onlinestore.model.Order;

import lombok.Builder;
/**
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public class OrderMapper {

    /**
     *
     * @param order
     * @return
     */
    @Builder(builderMethodName = "OrderDTOBuilder")
    public static OrderDTO convertOrderEntityToOrderDTO(Order order) {
        OrderDTO orderDTO = null;

        if (null != order) {
            orderDTO = OrderDTO.builder()
                    .id(order.getId())
                    .date(order.getDate())
                    .status(order.getStatus())
                    .amount(order.getAmount())
                    .address(UserMapper.convertAddressDAOToDTO(order.getAddress()))
                    .user(UserMapper.convertUserDAOToDTO(order.getUser()))
                    .orderProducts(OrderProductMapper.convertOrderProductsToOrderProductDTOs(order.getOrderProducts()))
                    .build();
        }
        return orderDTO;
    }

    @Builder(builderMethodName = "OrderEntityBuilder")
    public Order convertOrderDTOToOrder(OrderDTO orderDTO) {
        Order order = null;

        if (null != orderDTO) {
            order = Order.builder()
                    .id(orderDTO.getId())
                    .amount(orderDTO.getAmount())
                    .address(UserMapper.convertAddressDTOToDAO(orderDTO.getAddress()))
                    .date(orderDTO.getDate())
                    .status(orderDTO.getStatus()).build();
        }
        return order;
    }
}
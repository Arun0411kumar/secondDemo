package com.ideas2it.onlinestore.util.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.onlinestore.dto.CartProductDTO;
import com.ideas2it.onlinestore.model.CartProduct;

import lombok.Builder;
/**
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public class CartProductMapper {

	/**
	 *
	 * @param cartProduct
	 * @return
	 */
	@Builder(builderMethodName = "DTOBuilder")
	public static CartProductDTO convertCartProductEntityToDTO(CartProduct cartProduct) {
		CartProductDTO cartProductDTO = null;

		if (null != cartProduct) {
			cartProductDTO = CartProductDTO.builder()
					.id(cartProduct.getId())
					.quantity(cartProduct.getQuantity())
					.product(ProductMapper.convertProductToProductDTO(cartProduct.getProduct()))
					.build();
		}
		return cartProductDTO;
	}

	/**
	 *
	 * @param cartProductDTO
	 * @return
	 */
	@Builder(builderMethodName = "EntityBuilder")
	public static CartProduct convertCartProductDTOToEntity(CartProductDTO cartProductDTO) {
		CartProduct cartProduct = null;

		if (null != cartProductDTO) {
			cartProduct = CartProduct.builder()
					.id(cartProductDTO.getId())
					.quantity(cartProductDTO.getQuantity())
					.product(ProductMapper.convertProductDTOToProduct(cartProductDTO.getProduct()))
					.build();
		}
		return cartProduct;
	}

	/**
	 *
	 * @param cartProducts
	 * @return
	 */
	public static List<CartProductDTO> convertCartProductsToDTOs(List<CartProduct> cartProducts) {
		List<CartProductDTO> cartProductDTOs = new ArrayList<>();

		for (CartProduct cartProduct : cartProducts) {
			CartProductDTO cartProductDTO = convertCartProductEntityToDTO(cartProduct);
			cartProductDTOs.add(cartProductDTO);
		}
		return cartProductDTOs;
	}

	/**
	 *
	 * @param cartProductDTOs
	 * @return
	 */
	public static List<CartProduct> convertCartProductDTOsToCartProducts(List<CartProductDTO> cartProductDTOs) {
		List<CartProduct> cartProducts = new ArrayList<>();

		for (CartProductDTO cartProductDTO : cartProductDTOs) {
			CartProduct cartProduct = convertCartProductDTOToEntity(cartProductDTO);
			cartProducts.add(cartProduct);
		}
		return cartProducts;
	}

}
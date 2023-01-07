package com.ideas2it.onlinestore.util.mapper;

import com.ideas2it.onlinestore.dto.CartDTO;
import com.ideas2it.onlinestore.model.Cart;

import lombok.Builder;
/**
 *
 * @author Aabid
 * @version 1.0
 * @since 16-12-2022
 *
 */
public class CartMapper {

	/**
	 *
	 * @param cart
	 * @return
	 */
	@Builder(builderMethodName = "CartDTOBuilder")
	public static CartDTO convertCartEntityToDTO(Cart cart) {
		CartDTO cartDTO = null;

		if (null != cart) {
			cartDTO = CartDTO.builder()
					.id(cart.getId())
					.cartTotal(cart.getCartTotal())
					.cartProducts(CartProductMapper.convertCartProductsToDTOs(cart.getCartProducts()))
					.build();
		}
		return cartDTO;
	}

	@Builder(builderMethodName = "CartEntityBuilder")
	public static Cart convertCartDTOToEntity(CartDTO cartDTO) {
		Cart cart = null;

		if (null != cartDTO) {
			cart = Cart.builder()
					.id(cartDTO.getId())
					.user(UserMapper.convertUserDTOToDAO(cartDTO.getUser()))
					.cartProducts(CartProductMapper.convertCartProductDTOsToCartProducts(cartDTO.getCartProducts()))
					.build();
		}
		return cart;
	}
}
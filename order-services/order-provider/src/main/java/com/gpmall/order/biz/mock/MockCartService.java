package com.pmall.order.biz.mock;

import com.pmall.shopping.ICartService;
import com.pmall.shopping.dto.*;


public class MockCartService implements ICartService {
    @Override
    public CartListByIdResponse getCartListById(CartListByIdRequest request) {
        return null;
    }

    @Override
    public AddCartResponse addToCart(AddCartRequest request) {
        return null;
    }

    @Override
    public UpdateCartNumResponse updateCartNum(UpdateCartNumRequest request) {
        return null;
    }

    @Override
    public CheckAllItemResponse checkAllCartItem(CheckAllItemRequest request) {
        return null;
    }

    @Override
    public DeleteCartItemResponse deleteCartItem(DeleteCartItemRequest request) {
        return null;
    }

    @Override
    public DeleteCheckedItemResposne deleteCheckedItem(DeleteCheckedItemRequest request) {
        return null;
    }

    @Override
    public ClearCartItemResponse clearCartItemByUserID(ClearCartItemRequest request) {
        return null;
    }
}

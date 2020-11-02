package com.fsbs.src.module.order;

import java.util.List;

import com.fsbs.src.model.Gift;
import com.fsbs.src.model.Order;
import com.fsbs.src.model.OrderDetails;

public interface IOrder {
    interface IPresenterOrder{
        void addCartToServer(Order order,  List<OrderDetails> orderDetails);
        void resultAddCart(boolean result, String msg);


        void checkGift(String codeGift);
        void resultCheckGift(boolean result, Gift gift, String msg);


    }
    interface IViewOrder{
        void onSuccess(String msg);
        void onFailed(String msg);

        void onCheckGiftSuccess(Gift gift);
        void onCheckGiftFailed(String msg);
    }
}

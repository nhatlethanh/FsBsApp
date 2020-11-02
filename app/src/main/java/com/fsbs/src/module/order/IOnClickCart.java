package com.fsbs.src.module.order;

import android.widget.TextView;

import com.fsbs.src.model.OrderProvisional;

public interface IOnClickCart {
    void increase(OrderProvisional orderProvisional, TextView numberCart);
    void reduce(OrderProvisional orderProvisional, TextView numberCart);
}

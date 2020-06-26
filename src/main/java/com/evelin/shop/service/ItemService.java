package com.evelin.shop.service;

import com.evelin.shop.model.service.ItemServiceModel;
import com.evelin.shop.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    void addItem(ItemServiceModel itemServiceModel);
    List<ItemViewModel> findAllItems();

    ItemViewModel findById(String id);

    void delete(String id);
}

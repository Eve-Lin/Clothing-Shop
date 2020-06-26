package com.evelin.shop.service.serviceImpl;

import com.evelin.shop.model.entity.Item;
import com.evelin.shop.model.service.CategoryServiceModel;
import com.evelin.shop.model.service.ItemServiceModel;
import com.evelin.shop.model.view.ItemViewModel;
import com.evelin.shop.repository.ItemRepository;
import com.evelin.shop.service.CategoryService;
import com.evelin.shop.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {
        CategoryServiceModel categoryServiceModel = this
                .categoryService.findByCategoryName(itemServiceModel.getCategory().getName());

        itemServiceModel.setCategory(categoryServiceModel);

        this.itemRepository.saveAndFlush(this.modelMapper.map(itemServiceModel, Item.class));
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item ->{
                    ItemViewModel itemViewModel =
                            this.modelMapper
                            .map(item, ItemViewModel.class);
                    itemViewModel.setImgUrl(String
                    .format("/img/%s-%s.jpg", item.getGender(), item.getCategory().getName()));
                    return itemViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findById(String id) {
        return this.itemRepository.findById(id)
                .map(item ->{
                    ItemViewModel itemViewModel =
                            this.modelMapper
                                    .map(item, ItemViewModel.class);
                    itemViewModel.setImgUrl(String
                            .format("/img/%s-%s.jpg", item.getGender(), item.getCategory().getName()));
                    return itemViewModel;
                })
                .orElse(null);

    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }
}

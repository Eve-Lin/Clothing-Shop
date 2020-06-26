package com.evelin.shop.web;

import com.evelin.shop.model.binding.ItemBindingModel;
import com.evelin.shop.model.service.ItemServiceModel;
import com.evelin.shop.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemsController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String addItem(Model model) {
        if(!model.containsAttribute("itemBindingModel")){
            model.addAttribute("itemBindingModel", new ItemBindingModel());
        }

     return "add-item";
    }

    @PostMapping("/add")
    public String addItemConfirm(@Valid @ModelAttribute("itemBindingModel") ItemBindingModel itemBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemBindingModel", itemBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemBindingModel", bindingResult);
            return "redirect:add";
        }

        this.itemService.addItem(this.modelMapper.map(itemBindingModel, ItemServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/item/details")
    public String getItemDetails() {
        return "details-item";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("item", this.itemService.findById(id));
        modelAndView.setViewName("details-item");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id){
        this.itemService.delete(id);
        return"redirect:/";
    }
}

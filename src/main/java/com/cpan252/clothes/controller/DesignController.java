package com.cpan252.clothes.controller;

import java.util.EnumSet;

import com.cpan252.clothes.repository.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cpan252.clothes.model.Cloth;
import com.cpan252.clothes.model.Cloth.Brand;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {

    @Autowired
    private ClothRepository clothRepository;

    @GetMapping
    public String design() {
        return "design";
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
        log.info("brands converted to string:  {}", brands);
    }

    @ModelAttribute
    // This model attribute has a lifetime of a request
    public Cloth cloth() {
        return Cloth
                .builder()
                .build();
    }

    @PostMapping
    public String processClothAddition(@Valid Cloth cloth, BindingResult result) {
        if (result.hasErrors()) {
            return "design";
        }
        log.info("Processing cloth: {}", cloth);
        clothRepository.save(cloth);
        log.info("saved");
        return "redirect:/clothlist";
    }

}

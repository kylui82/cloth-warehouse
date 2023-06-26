package com.cpan252.clothes.controller;

import com.cpan252.clothes.model.Cloth;
import com.cpan252.clothes.model.dto.ClothSearchByBrandDto;
import com.cpan252.clothes.repository.ClothRepositoryPaginated;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cpan252.clothes.repository.ClothRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/clothlist")
public class ClothListController {
    private static final int PAGE_SIZE = 5;
    private ClothRepository clothRepository;
    private ClothRepositoryPaginated clothRepositoryPaginated;
    public ClothListController(ClothRepository clothRepository, ClothRepositoryPaginated clothRepositoryPaginated) {
        this.clothRepository = clothRepository;
        this.clothRepositoryPaginated = clothRepositoryPaginated;
    }



    @GetMapping
    public String showClothes(@SortDefault(sort="brand",direction = Sort.Direction.ASC)
                                          Sort sort, Model model) {
        return "clothlist";
    }

    @ModelAttribute
    public void clothes(Model model) {
        var clothPage = clothRepositoryPaginated.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("clothes", clothPage);
        model.addAttribute("currentPage", clothPage.getNumber());
        model.addAttribute("totalPages", clothPage.getTotalPages());
    }

    @ModelAttribute
    public void clothesByBrandDto(Model model) {
        model.addAttribute("clothesByBrandDto", new ClothSearchByBrandDto());
    }

    @PostMapping
    public String searchClothesByDate(@ModelAttribute ClothSearchByBrandDto clothesByBrandDto,
                                       Model model) {
        model.addAttribute("clothes",
                clothRepository.findTitleByBrandAndYearcreated(
                    clothesByBrandDto.getBrand().toUpperCase(Locale.ROOT),
                    clothesByBrandDto.getYearcreated()
                )
        );
        return "clothlist";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
                             @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "clothlist";
        }
        var clothPage = clothRepositoryPaginated.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("clothes", clothPage.getContent());
        model.addAttribute("currentPage", clothPage.getNumber());
        return "clothlist";
    }
}


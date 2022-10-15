package ru.job4j.cars.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class SearchController {
    private final PostService postService;
    private final BrandService brandService;
    private final BodyService bodyService;
    private final CategoryService categoryService;
    private final ModelService modelService;
    private final SearchService searchService;

    public SearchController(PostService postService,
                            BrandService brandService,
                            BodyService bodyService,
                            CategoryService categoryService,
                            ModelService modelService,
                            SearchService searchService) {
        this.postService = postService;
        this.brandService = brandService;
        this.bodyService = bodyService;
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.searchService = searchService;
    }

    @GetMapping("/formFilterCategory")
    public String formFilterCategory(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        searchService.setIdCategory(0);
        model.addAttribute("category", categoryService.findAll());
        return "filterCategory";
    }

    @PostMapping("/filterCategory")
    public String filterCategory(@ModelAttribute Post post,
                                 HttpSession session,
                                 @RequestParam("category.id") String idCategory) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdCategory(Integer.parseInt(idCategory));
        return "redirect:/formFilterBody";
    }

    @GetMapping("/formFilterBody")
    public String formFilterBody(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        searchService.setIdBody(0);
        model.addAttribute("body", bodyService.findBodyByCategory(searchService.getIdCategory()));
        return "filterBody";
    }

    @PostMapping("/filterBody")
    public String filterBody(@ModelAttribute Post post,
                             HttpSession session,
                             @RequestParam("body.id") String idBody) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdBody(Integer.parseInt(idBody));
        return "redirect:/formFilterBrand";
    }

    @GetMapping("/formFilterBrand")
    public String formFilterBrand(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        searchService.setIdBrand(0);
        model.addAttribute("brand", brandService.findAll());
        return "filterBrand";
    }

    @PostMapping("/filterBrand")
    public String filterBrand(@ModelAttribute Post post,
                              HttpSession session,
                              @RequestParam("brand.id") String idBrand) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdBrand(Integer.parseInt(idBrand));
        return "redirect:/formFilterModel";
    }

    @GetMapping("/formFilterModel")
    public String formFilterModel(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("model", modelService.findModelByBrand(searchService.getIdBrand()));
        searchService.setIdModel(0);
        return "filterModel";
    }

    @PostMapping("/filterModel")
    public String filterModel(@ModelAttribute Post post,
                              HttpSession session,
                              @RequestParam("model.id") String idModel) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdModel(Integer.parseInt(idModel));
        return "redirect:/formAdsFilter";
    }

    @GetMapping("/formAdsFilter")
    public String formAdsFilter(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("ads", postService.findAdCategoryAndBodyAndBrandAndModel(
                searchService.getIdCategory(),
                searchService.getIdBody(),
                searchService.getIdBrand(),
                searchService.getIdModel())
        );
        return "ads";
    }
}

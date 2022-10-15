package ru.job4j.cars.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@ThreadSafe
@Controller
public class PostController {

    private final PostService postService;
    private final CarService carService;
    private final EngineService engineService;
    private final BrandService brandService;
    private final BodyService bodyService;
    private final CategoryService categoryService;
    private final ModelService modelService;
    private final SearchService searchService;

    public PostController(PostService postService,
                          CarService carService,
                          EngineService engineService,
                          BrandService brandService,
                          BodyService bodyService,
                          CategoryService categoryService,
                          ModelService modelService,
                          SearchService searchService) {
        this.postService = postService;
        this.carService = carService;
        this.engineService = engineService;
        this.brandService = brandService;
        this.bodyService = bodyService;
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.searchService = searchService;
    }

    @GetMapping("/formSelectCategory")
    public String formSelectCategory(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("category", categoryService.findAll());
        return "selectCategory";
    }

    @PostMapping("/selectCategory")
    public String selectCategory(@ModelAttribute Post post,
                                 HttpSession session,
                                 @RequestParam("category.id") String idCategory) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdCategory(Integer.parseInt(idCategory));
        return "redirect:/formSelectBody";
    }

    @GetMapping("/formSelectBody")
    public String formSelectBody(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("body", bodyService.findBodyByCategory(searchService.getIdCategory()));
        return "selectBody";
    }

    @PostMapping("/selectBody")
    public String selectBody(@ModelAttribute Post post,
                             HttpSession session,
                             @RequestParam("body.id") String idBody) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdBody(Integer.parseInt(idBody));
        return "redirect:/formSelectBrand";
    }

    @GetMapping("/formSelectBrand")
    public String formSelectBrand(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("brand", brandService.findAll());
        return "selectBrand";
    }

    @PostMapping("/selectBrand")
    public String saveBrand(@ModelAttribute Post post,
                            HttpSession session,
                            @RequestParam("brand.id") String idBrand) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdBrand(Integer.parseInt(idBrand));
        return "redirect:/formSelectModel";
    }

    @GetMapping("/formSelectModel")
    public String formSelectModel(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("model", modelService.findModelByBrand(searchService.getIdBrand()));
        return "selectModel";
    }

    @PostMapping("/selectModel")
    public String saveModel(@ModelAttribute Post post,
                            HttpSession session,
                            @RequestParam("model.id") String idModel) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdModel(Integer.parseInt(idModel));
        return "redirect:/formSelectEngine";
    }

    @GetMapping("/formSelectEngine")
    public String formSelectEngine(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("engine", engineService.findAll());
        return "selectEngine";
    }

    @PostMapping("/selectEngine")
    public String saveEngine(@ModelAttribute Post post,
                             @ModelAttribute Car car,
                             HttpSession session,
                             @RequestParam("engine.id") String idEngine) {
        post.setUser((User) session.getAttribute("user"));
        searchService.setIdEngine(Integer.parseInt(idEngine));
        car.setCategory(categoryService.findById(searchService.getIdCategory()));
        car.setBody(bodyService.findById(searchService.getIdBody()));
        car.setBrand(searchService.getIdBrand());
        car.setModel(modelService.findById(searchService.getIdModel()));
        car.setEngine(searchService.getIdEngine());
        carService.add(car);
        searchService.setIdCar(car.getId());
        return "redirect:/formAddDescription";
    }

    @GetMapping("/formAddDescription")
    public String formAddDescription(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("car", carService.findById(searchService.getIdCar()));
        return "addAd";
    }

    @PostMapping("/addDescription")
    public String saveDescription(@ModelAttribute Post post,
                                  HttpSession session,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        post.setUser((User) session.getAttribute("user"));
        post.setCreated(new Date(System.currentTimeMillis()));
        post.setCar(carService.findById(searchService.getIdCar()));
        if (file.getBytes().length > 0) {
            post.setPhoto(file.getBytes());
        }
        postService.add(post);
        return "redirect:/ads";
    }

    @GetMapping("/deleteAd/{adId}")
    public String deleteAd(Model model, @PathVariable("adId") int id, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        postService.delete(id);
        return "redirect:/ads";
    }

    @GetMapping("/setSold/{adId}")
    public String setSold(Model model, @PathVariable("adId") int id, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        postService.setSold(id);
        return "redirect:/ads";
    }

    @GetMapping("/photoAd/{adId}")
    public ResponseEntity<Resource> download(@PathVariable("adId") Integer adId) {
        Post post = postService.findById(adId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(post.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(post.getPhoto()));
    }

    @GetMapping("/descriptionAd/{adId}")
    public String descriptionItem(Model model, @PathVariable("adId") int id, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("post", postService.findById(id));
        return "descriptionAd";
    }
}
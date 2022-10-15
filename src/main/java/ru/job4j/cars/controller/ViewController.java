package ru.job4j.cars.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.PostService;

@ThreadSafe
@Controller
public class ViewController {
    private final PostService postService;

    public ViewController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/ads")
    public String ads(Model model, HttpSession session) {
        return general(model, session, postService.findSoldAll(false));
    }

    @GetMapping("/adsNew")
    public String adsNew(Model model, HttpSession session) {
        return general(model, session, postService.findNewCar(true, false));
    }

    @GetMapping("/adsNoNew")
    public String adsNoNew(Model model, HttpSession session) {
        return general(model, session, postService.findNewCar(false, false));
    }

    @GetMapping("/adsLast")
    public String adsLastDay(Model model, HttpSession session) {
        return general(model, session, postService.findPostLastDay());
    }

    @GetMapping("/adsSold")
    public String adsSold(Model model, HttpSession session) {
        return general(model, session, postService.findSoldAll(true));
    }

    @GetMapping("/adsAll")
    public String adsAll(Model model, HttpSession session) {
        return general(model, session, postService.findAll());
    }

    @GetMapping("/passenger")
    public String passenger(Model model, HttpSession session) {
        return general(model, session, postService.findPostCategoryAndSold("Легковые", false));

    }

    @GetMapping("/commercial")
    public String commercial(Model model, HttpSession session) {
        return general(model, session, postService.findPostCategoryAndSold("Коммерческие", false));

    }

    @GetMapping("/special")
    public String special(Model model, HttpSession session) {
        return general(model, session, postService.findPostCategoryAndSold("Спец. техника", false));
    }



    @GetMapping("/myAds")
    public String myAds(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        User user = (User) model.getAttribute("user");
        if (user != null) {
            model.addAttribute("ads", postService.findMyAds(user.getEmail()));
        }
        return "ads";
    }

    private String general(Model model, HttpSession session, Object attributeValue) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("ads", attributeValue);
        return "ads";
    }
}
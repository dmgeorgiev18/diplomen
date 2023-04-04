package com.project.motohotel.controller;

import com.project.motohotel.binding.SearchDto;
import com.project.motohotel.binding.SubscriptionEdit;
import com.project.motohotel.binding.SubscriptionRegistration;
import com.project.motohotel.entity.Subscription;
import com.project.motohotel.repository.ModelService;
import com.project.motohotel.repository.SubscriptionService;
import com.project.motohotel.repository.SubscriptionTypeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private SubscriptionService subscriptionService;
    private ModelService modelService;
    private SubscriptionTypeService subscriptionTypeService;
    private ModelMapper mapper;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, ModelService modelService, SubscriptionTypeService subscriptionTypeService, ModelMapper mapper) {
        this.subscriptionService = subscriptionService;
        this.modelService = modelService;
        this.subscriptionTypeService = subscriptionTypeService;
        this.mapper = mapper;
    }

    @GetMapping
    public String start(
            @RequestParam(required = false, defaultValue = " ") String firstName,
            @RequestParam(required = false, defaultValue = " ") String lastName,
            @RequestParam(required = false, defaultValue = " ") String motorId,
            @RequestParam(required = false, defaultValue = " ") String phoneNumber,
            @RequestParam(required = false, defaultValue = "ASC") String sortType,
            Model model) {

        if (!sortType.equals("ASC") && !sortType.equals("DESC")) {
            sortType = "ASC";
        }
        List<Subscription> allSubscriptions;
        if (firstName.equals(" ") && lastName.equals(" ") && motorId.equals(" ") && phoneNumber.equals(" ")) {
            allSubscriptions = subscriptionService.findAll(sortType);
        } else {
            allSubscriptions = subscriptionService.findAllBySearch(firstName
                    , lastName, motorId, phoneNumber, sortType);
        }
        model.addAttribute("orders", List.of("ASC", "DESC"));
        model.addAttribute("subscriptions", allSubscriptions);
        model.addAttribute("searchDto", new SearchDto());
        model.addAttribute("subTypes", subscriptionTypeService.findAll());

        return "home";
    }


    @GetMapping("/{id}")
    public String getSubscription(@PathVariable String id, Model model) {
        model.addAttribute("sub", subscriptionService.findById(id));
        return "subscription";
    }

    @GetMapping("/create")
    public String createSubscription(Model model) {
        if (!model.containsAttribute("subscription")) {
            model.addAttribute("subscription", new SubscriptionRegistration());

        }
        model.addAttribute("models", modelService.findAllMogels());
        model.addAttribute("subTypes", subscriptionTypeService.findAll());

        return "create-subscription";
    }

    @GetMapping("/edit/{id}")
    public String editSubGet(Model model, @PathVariable String id) {
        if(!model.containsAttribute("sub")){
            model.addAttribute("sub", mapper.map(subscriptionService.findById(id), SubscriptionEdit.class));

        }
        model.addAttribute("models", modelService.findAllMogels());
        model.addAttribute("subTypes", subscriptionTypeService.findAll());
        return "edit-subscriptions";

    }

    @PostMapping("/edit/{id}")
    public String editSubPost(@PathVariable String id, @ModelAttribute @Valid SubscriptionEdit subscription, BindingResult result,
                              RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("sub", subscription);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.sub", result);
            return "redirect:/subscriptions/edit/{id}";
        }

        subscriptionService.editSubscription(subscription, id);
        return "redirect:/subscriptions";
    }

    @PostMapping("/create")
    public String createSubscription(@ModelAttribute @Valid SubscriptionRegistration subscription, BindingResult result,
                                     RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("subscription", subscription);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.subscription", result);
            return "redirect:/subscriptions/create";
        }

        subscriptionService.createSubscription(subscription);
        return "redirect:/subscriptions";
    }

    @GetMapping("/delete/{id}")
    public String delte(@PathVariable String id) {
        subscriptionService.deleteById(id);
        return "redirect:/subscriptions";
    }

    @ModelAttribute("auth")
    public Authentication requestURI(final Authentication authentication) {
        return authentication;
    }
}

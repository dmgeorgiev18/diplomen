package com.project.motohotel.service;

import com.project.motohotel.binding.SubscriptionEdit;
import com.project.motohotel.binding.SubscriptionRegistration;
import com.project.motohotel.entity.*;
import com.project.motohotel.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository repository;
    private SubscriptionTypeService subscriptionTypeService;
    private ModelService modelService;
    private UserService userService;

    private ModelMapper mapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository, SubscriptionTypeService subscriptionTypeService, ModelService modelService, UserService userService, ModelMapper mapper) {
        this.repository = repository;
        this.subscriptionTypeService = subscriptionTypeService;
        this.modelService = modelService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    @Override
    public void createSubscription(SubscriptionRegistration registration) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = String.valueOf(authentication.getName());
        User user = userService.findByUsername(principal);
        Subscription entity = this.mapper.map(registration, Subscription.class);
        SubscriptionType subType = subscriptionTypeService.findById(registration.getSubscriptionTypeId());
        Model model = modelService.findById(registration.getModelId());
        setAdditionalDetails(user, entity, subType, model);
        repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Subscription> findAllBySearch(String firstName, String lastName, String motorId, String phoneNumber, String sortType) {
        Sort.Direction direction = Sort.Direction.valueOf(sortType);
        return this.repository.
                findAllByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCaseOrMotorIdStartingWithIgnoreCaseOrPhoneNumberStartingWithIgnoreCase
                        (firstName,lastName,motorId,phoneNumber,Sort.by(direction,"finishDate"));
    }

    @Override
    public List<Subscription> findAll(String sortType) {
        Sort.Direction direction = Sort.Direction.valueOf(sortType);
        return repository.findAll(Sort.by(direction,"finishDate"));
    }

    @Override
    public Subscription findById(String id) {
        return repository.findById(id).get();
    }


    @Override
    public void editSubscription(SubscriptionEdit subscription, String id) {
        Subscription entity = this.mapper.map(subscription, Subscription.class);
        Subscription original = repository.findById(id).get();
        SubscriptionType subType = subscriptionTypeService.findById(subscription.getSubscriptionTypeId());
        Model model = modelService.findById(subscription.getModelId());
        setAdditionalDetails(entity, subType, model);
        entity.setAddedBy(original.getAddedBy());
        repository.save(entity);
    }


    public static void setAdditionalDetails(Subscription entity, SubscriptionType subType, Model model) {
        entity.setModel(model);
        entity.setFinishDate(LocalDate.now().plusDays(subType.getDays()));
        entity.setSubscriptionType(subType);

    }
    private static void setAdditionalDetails(User user, Subscription entity, SubscriptionType subType, Model model) {
        entity.setModel(model);
        entity.setFinishDate(LocalDate.now().plusDays(subType.getDays()));
        entity.setSubscriptionType(subType);
        entity.setAddedBy(user);
    }
}

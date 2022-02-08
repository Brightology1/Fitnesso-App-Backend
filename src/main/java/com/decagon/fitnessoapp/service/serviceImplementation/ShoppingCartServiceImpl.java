package com.decagon.fitnessoapp.service.serviceImplementation;

import com.decagon.fitnessoapp.dto.CartResponse;
import com.decagon.fitnessoapp.model.product.*;
import com.decagon.fitnessoapp.model.user.Person;
import com.decagon.fitnessoapp.repository.IntangibleProductRepository;
import com.decagon.fitnessoapp.repository.PersonRepository;
import com.decagon.fitnessoapp.repository.ShoppingCartRepository;
import com.decagon.fitnessoapp.repository.TangibleProductRepository;
import com.decagon.fitnessoapp.service.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@NoArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    private TangibleProductRepository tangibleProductRepository;

    private IntangibleProductRepository intangibleProductRepository;

    private PersonRepository personRepository;

    private ObjectMapper mapper;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   TangibleProductRepository tangibleProductRepository,
                                   IntangibleProductRepository intangibleProductRepository,
                                   PersonRepository personRepository,
                                   ObjectMapper mapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.tangibleProductRepository = tangibleProductRepository;
        this.intangibleProductRepository = intangibleProductRepository;
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public Cart addToCart(Long productId, CHANGE_QUANTITY status, PersonDetails personDetails) {
        TangibleProduct product = mapper.convertValue(tangibleProductRepository.findById(productId), TangibleProduct.class);
        IntangibleProduct service = mapper.convertValue(intangibleProductRepository.findById(productId), IntangibleProduct.class);

        Cart cart = getCarts(personDetails);

        if(tangibleProductRepository.findById(productId).isPresent()) {
            cart.getTangibleProduct().putIfAbsent(product.getProductName(), 0);
            int quantity = cart.getTangibleProduct().get(product.getProductName());
            if (status == CHANGE_QUANTITY.INCREASE && quantity <= 10) {
                cart.getTangibleProduct().put(product.getProductName(), ++quantity);
            } else if (status == CHANGE_QUANTITY.DECREASE && quantity > 0) {
                cart.getTangibleProduct().put(product.getProductName(), --quantity);
            } else {
                cart.getTangibleProduct().put(product.getProductName(), 1);
            }
        } else if (intangibleProductRepository.findById(productId).isPresent()) {
            cart.getIntangibleProduct().putIfAbsent(service.getProductName(), 0);
            int quantity = cart.getIntangibleProduct().get(service.getProductName());
            if(status == CHANGE_QUANTITY.INCREASE && quantity <= 10) {
                cart.getIntangibleProduct().put(service.getProductName(), ++quantity);
            } else if (status == CHANGE_QUANTITY.DECREASE && quantity > 0) {
                cart.getIntangibleProduct().put(service.getProductName(), --quantity);
            } else {
                cart.getIntangibleProduct().put(service.getProductName(), 1);
            }
        } else {
            throw new IllegalStateException("Product with id " + productId + " does not exist");
        }
        return shoppingCartRepository.save(cart);
    }

    @NotNull
    private Cart getCarts(PersonDetails personDetails) {
        Person person = personRepository.findPersonByUserName(personDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Name does not Exist"));
        Cart cart = shoppingCartRepository.findByPerson(person).orElse(null);
        Cart newCart = new Cart();
        if (cart == null) {
            newCart.setPerson(person);
            return newCart;
        } else return cart;
    }



    @Override
    public Cart removeFromCart (Long productId, PersonDetails personDetails){
        TangibleProduct product = mapper.convertValue(tangibleProductRepository.findById(productId), TangibleProduct.class);
        IntangibleProduct service = mapper.convertValue(intangibleProductRepository.findById(productId), IntangibleProduct.class);

        Person person = personRepository.findPersonByUserName(personDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Name does not Exist"));

        Cart cart = shoppingCartRepository.findByPerson(person).orElse(null);
        if (cart == null) {
            throw new IllegalStateException("You have no item in cart");
        }
        if(tangibleProductRepository.findById(productId).isPresent()) {
            Integer pv = cart.getTangibleProduct().remove(product.getProductName());
        } else if(intangibleProductRepository.findById(productId).isPresent()) {
            Integer sv = cart.getIntangibleProduct().remove(service.getProductName());
        } else {
            throw new IllegalStateException("You do not have item in cart");
        }
        return cart;
    }

    @Override
    public CartResponse getCartById(Long productId) {
        return CartResponse.builder().cartItem(shoppingCartRepository.getById(productId)).build();
    }

    @Override
    public CartResponse viewCartItems() {
        return CartResponse.builder().carts(shoppingCartRepository.findAll()).build();
    }
}


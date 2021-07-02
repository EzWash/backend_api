package com.ezwash.backend.service.business;

import com.ezwash.backend.domain.model.accounts.Customer;
import com.ezwash.backend.domain.model.business.Cart;
import com.ezwash.backend.domain.repository.business.CartRepository;
import com.ezwash.backend.domain.repository.business.ServiceRepository;
import com.ezwash.backend.domain.service.business.CartService;
import com.ezwash.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<com.ezwash.backend.domain.model.business.Service> getServiceByCartId(Long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFoundException("Cart","Id", cartId));
        return cart.getServiceList();
    }

    @Override
    public Cart addServiceToCart(Long cartId, Long serviceId) {
        com.ezwash.backend.domain.model.business.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));

        return cartRepository.findById(cartId).map(
                cart -> cartRepository.save(cart.addServiceToCart(service)))
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
    }

    @Override
    public Cart deleteServiceFromCart(Long cartId, Long serviceId) {
        com.ezwash.backend.domain.model.business.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));
        return cartRepository.findById(cartId).map(
                user -> cartRepository.save(user.deleteServiceFromCart(service)))
                .orElseThrow(()->new ResourceNotFoundException("Cart","Id", cartId));
    }
}

package com.pack.shopping.cart.api.serviceImplement;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.shopping.cart.api.dto.CardDTO;
import com.pack.shopping.cart.api.dto.NewOrderDTO;
import com.pack.shopping.cart.api.dto.OrderDTO;
import com.pack.shopping.cart.api.dto.PaymentDTO;
import com.pack.shopping.cart.api.dto.UserDTO;
import com.pack.shopping.cart.api.entity.CardEntity;
import com.pack.shopping.cart.api.entity.CartEntity;
import com.pack.shopping.cart.api.entity.ItemEntity;
import com.pack.shopping.cart.api.entity.OrderEntity;
import com.pack.shopping.cart.api.entity.OrderItemEntity;
import com.pack.shopping.cart.api.entity.UserEntity;
import com.pack.shopping.cart.api.exception.ResourceNotFoundException;
import com.pack.shopping.cart.api.repository.CardRepository;
import com.pack.shopping.cart.api.repository.CartRepository;
import com.pack.shopping.cart.api.repository.ItemRepository;
import com.pack.shopping.cart.api.repository.OrderItemRepository;
import com.pack.shopping.cart.api.repository.OrderRepository;
import com.pack.shopping.cart.api.repository.UserRepository;
import com.pack.shopping.cart.api.service.OrderService;
import com.pack.shopping.cart.api.util.StatusEnum;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private CardRepository cardRepository;
    private OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository repository, UserRepository userRepository, ItemRepository itemRepository,
            CartRepository cartRepository, OrderItemRepository orderItemRepository, CardRepository cardRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<OrderEntity> addOrder(@Valid NewOrderDTO newOrder) {

        if (Strings.isEmpty(newOrder.getCustomerId())
                || !userRepository.existsById(UUID.fromString(newOrder.getCustomerId()))) {
            throw new ResourceNotFoundException("Invalid customer id.");
        }

        if (Objects.isNull(newOrder.getCardId()) || Strings.isEmpty(newOrder.getCardId()) || !cardRepository.existsById(UUID.fromString(newOrder.getCardId()))) {
            throw new ResourceNotFoundException("Invalid card id.");
        }
        
        Iterable<ItemEntity> dbItems = itemRepository.findByCustomerId(UUID.fromString(newOrder.getCustomerId()));
        List<ItemEntity> items = StreamSupport.stream(dbItems.spliterator(), false).collect(Collectors.toList());
        if (items.size() < 1) {
            throw new ResourceNotFoundException(String
                    .format("There is no item found in customer's (ID: %s) cart.", newOrder.getCustomerId()));
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ItemEntity i : items) {
            total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
        }
        Timestamp orderDate = Timestamp.from(Instant.now());

        OrderDTO order = new OrderDTO();
        order.card(new CardDTO(newOrder.getCardId())).customer(new UserDTO(newOrder.getCustomerId()))
                .date(orderDate).total(total).status(StatusEnum.CREATED).payment(new PaymentDTO().paymentId("0677d504-3862-4073-8cd1-5762f02c0288"));
        // Save Order
        OrderEntity entity = repository.save(toEntity(order));

        Optional<CartEntity> oCart = cartRepository.findByCustomerId(UUID.fromString(newOrder.getCustomerId()));
        CartEntity cart = oCart.orElseThrow(() -> new ResourceNotFoundException(
                String.format("Cart not found for given customer (ID: %s)", newOrder.getCustomerId())));
        itemRepository.deleteCartItemJoinById(
                cart.getItems().stream().map(i -> i.getItemId()).collect(Collectors.toList()), cart.getCartId());
        orderItemRepository.saveAll(cart.getItems().stream()
                .map(i -> new OrderItemEntity().setOrderId(entity.getOrderId()).setItemId(i.getItemId()))
                .collect(Collectors.toList()));
        return Optional.of(entity);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId) {
        return repository.findByCustomerId(UUID.fromString(customerId));
    }

    @Override
    public Optional<OrderEntity> getByOrderId(String id) {
        return repository.findById(UUID.fromString(id));
    }

    private OrderEntity toEntity(OrderDTO order) {
        OrderEntity entity = new OrderEntity();
        System.out.println(order.toString());
        entity.setCardEntity(new CardEntity(UUID.fromString(order.getCard().getCardId())))
                .setUserEntity(new UserEntity(UUID.fromString(order.getCustomer().getUserId())))
                .setOrderDate(order.getDate()).setTotal(order.getTotal()).setStatus(order.getStatus());
        return entity;
    }

}

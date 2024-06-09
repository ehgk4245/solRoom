package com.solRoom.solspring.service.mallService;

import com.solRoom.solspring.domain.Member;
import com.solRoom.solspring.domain.mallDomain.Order;
import com.solRoom.solspring.domain.mallDomain.Product;
import com.solRoom.solspring.repository.MemberRepository;
import com.solRoom.solspring.repository.mallRepository.OrderRepository;
import com.solRoom.solspring.repository.mallRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createOrder(Long memberId, Long productId, String deliveryAddress) throws Exception {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new Exception("Member not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));

        BigDecimal price = product.getPrice();
        BigDecimal credit = member.getCredit();

        if (credit.compareTo(price) < 0) {
            throw new Exception("Insufficient credit");
        }

        member.setCredit(credit.subtract(price));
        memberRepository.save(member);

        Order order = Order.builder()
                .member(member)
                .product(product)
                .orderDate(new Timestamp(System.currentTimeMillis()))
                .deliveryAddress(deliveryAddress)
                .build();

        orderRepository.save(order);
    }

    public List<Order> getOrdersByMember(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }
}

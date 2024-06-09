package com.solRoom.solspring.controller.mallController;

import com.solRoom.solspring.domain.mallDomain.Product;
import com.solRoom.solspring.service.MemberService;
import com.solRoom.solspring.service.mallService.OrderService;
import com.solRoom.solspring.service.mallService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/mall")
public class MallController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/products/category/{category}")
    public String getProductsByCategory(@PathVariable String category, Model model) {
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        return "productList"; // 타임리프 템플릿 이름
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam String name, Model model) {
        List<Product> products = productService.searchProductsByName(name);
        model.addAttribute("products", products);
        return "productList"; // 타임리프 템플릿 이름
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam Long memberId, @RequestParam Long productId, @RequestParam String deliveryAddress, Model model) {
        try {
            orderService.createOrder(memberId, productId, deliveryAddress);
            return "redirect:/shop/orders/member/" + memberId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "orderError";
        }
    }

}

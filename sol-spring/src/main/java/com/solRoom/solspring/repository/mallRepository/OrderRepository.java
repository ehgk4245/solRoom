package com.solRoom.solspring.repository.mallRepository;

import com.solRoom.solspring.domain.mallDomain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.member.id = :memberId")
    List<Order> findByMemberId(@Param("memberId") Long memberId);
}

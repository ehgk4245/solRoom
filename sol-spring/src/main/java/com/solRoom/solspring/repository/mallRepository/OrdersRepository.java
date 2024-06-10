package com.solRoom.solspring.repository.mallRepository;

import com.solRoom.solspring.domain.mallDomain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.member.id = :memberId")
    List<Orders> findByMemberId(@Param("memberId") Long memberId);
}

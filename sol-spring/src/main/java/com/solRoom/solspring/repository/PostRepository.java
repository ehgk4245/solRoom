package com.solRoom.solspring.repository;

import com.solRoom.solspring.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

}

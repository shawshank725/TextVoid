package com.example.TextVoid.dao;

import com.example.TextVoid.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    public List<Post> findByMessageContainingIgnoreCase(String keyword);

    public List<Post> findByGenre(String genre);

    public List<Post> findAllByOrderByDateDesc();

}

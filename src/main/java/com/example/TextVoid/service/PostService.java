package com.example.TextVoid.service;

import com.example.TextVoid.entity.Post;

import java.util.List;

public interface PostService {


    List<Post> findAll();

    Post save(Post thePost);

    List<Post> findByMessage(String keyword);

    List<Post> findByGenre(String genre);

    List<Post> findLatestPosts();
}

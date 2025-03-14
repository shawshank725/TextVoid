package com.example.TextVoid.service;

import com.example.TextVoid.dao.PostRepository;
import com.example.TextVoid.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository thePostRepository){
        postRepository = thePostRepository;
    }
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post thePost) {
        return postRepository.save(thePost);
    }

    @Override
    public List<Post> findByMessage(String keyword) {
        return postRepository.findByMessageContainingIgnoreCase(keyword);
    }

    @Override
    public List<Post> findByGenre(String genre) {
        List<Post> postsByGenre = postRepository.findByGenre(genre);
        return postsByGenre;
    }

    @Override
    public List<Post> findLatestPosts() {
        return postRepository.findAllByOrderByDateDesc();
    }

}

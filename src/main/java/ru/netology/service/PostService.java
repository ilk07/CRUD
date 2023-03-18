package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(String postContent) {
        return repository.save(postContent);
    }

    public Post update(Long id, String postContent) {
        return repository.update(id, postContent);
    }

    public boolean removeById(long id) {
        return repository.removeById(id);
    }
}


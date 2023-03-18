package ru.netology.repository;

import ru.netology.model.Post;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class PostRepository {
    private final Map<Long, Post> postList = new ConcurrentHashMap<>();
    private final AtomicLong postId = new AtomicLong();

    public List<Post> all() {
        if (postList.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Post> posts = postList.values();
        List<Post> list = new ArrayList<>(posts);
        return list;
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postList.get(id));
    }

    public Post save(String postContent) {
        Long newId = postId.incrementAndGet();
        postList.put(newId, new Post(newId, postContent));
        return postList.get(newId);
    }

    public Post update(Long id, String postContent) {
        if (postList.containsKey(id)) {
            postList.get(id).setContent(postContent);
            return postList.get(id);
        }

        return null;
    }

    public boolean removeById(long id) {
        if (postList.containsKey(id)) {
            postList.remove(id, postList.get(id));
            return true;
        }

        return false;
    }
}

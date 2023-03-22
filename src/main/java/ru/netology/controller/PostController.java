package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private final Gson gson;

    public PostController(PostService service) {
        this.service = service;
        this.gson = new Gson();
    }

    public void all(HttpServletResponse response) throws IOException {
        final var data = service.all();
        writeJsonResponse(response, gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        final var data = service.getById(id);
        writeJsonResponse(response, gson.toJson(data));
    }

    public void save(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String postBody = request.getReader().lines().collect(Collectors.joining("\n"));
        final var data = service.save(id, postBody);
        if(data == null){
            writeJsonResponse(response, gson.toJson("Post " + id + " can't be create or update"));
        } else {
            writeJsonResponse(response, gson.toJson(data));
        }
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        if(service.removeById(id)){
            writeJsonResponse(response, gson.toJson("Post " + id + " removed"));
        } else {
            writeJsonResponse(response, gson.toJson("Post " + id + " can't be deleted"));
        }
    }

    public void writeJsonResponse(HttpServletResponse response, String jsonData) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.setContentLength(jsonData.length());
        response.getWriter().print(jsonData);
    }


}
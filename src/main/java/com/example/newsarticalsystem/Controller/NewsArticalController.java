package com.example.newsarticalsystem.Controller;

import com.example.newsarticalsystem.Model.NewsArtical;
import com.example.newsarticalsystem.Service.NewsArticalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/newsartical")
public class NewsArticalController {

    private final NewsArticalService newsArticalService;

    public NewsArticalController(NewsArticalService newsArticalService) {
        this.newsArticalService = newsArticalService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<NewsArtical>> getNewsArticals() {
        return ResponseEntity.ok(newsArticalService.getNewsArticals());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewsArtical(@RequestBody @Valid NewsArtical newsArtical, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        newsArticalService.addNewsArtical(newsArtical);
        return ResponseEntity.ok("Added News Article Successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateNewsArtical(@PathVariable String id, @RequestBody @Valid NewsArtical updatedArticle, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        return newsArticalService.updateNewsArtical(id, updatedArticle);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNewsArtical(@PathVariable String id) {
        return newsArticalService.deleteNewsArtical(id);
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<String> publishNewsArtical(@PathVariable String id) {
        return newsArticalService.publishNewsArtical(id);
    }

    @GetMapping("/published")
    public ResponseEntity<List<NewsArtical>> getPublishedNewsArticals() {
        return ResponseEntity.ok(newsArticalService.getPublishedNewsArticals());
    }

    @GetMapping("/getcategory/{category}")
    public ResponseEntity<List<NewsArtical>> getNewsArticalsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(newsArticalService.getNewsArticalsByCategory(category));
    }
}

package com.example.newsarticalsystem.Service;

import com.example.newsarticalsystem.Model.NewsArtical;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: مانرجع هنا ريسبونس اينتيتي

@Service
public class NewsArticalService {
    private final List<NewsArtical> newsArticals = new ArrayList<>();

    // 1. Get all NewsArticles
    public List<NewsArtical> getNewsArticals() {
        return newsArticals;
    }

    // 2. Add a NewsArticle
    public NewsArtical addNewsArtical(NewsArtical newsArtical) {
        newsArticals.add(newsArtical);
        return newsArtical;
    }

    public String updateNewsArtical(String id, NewsArtical updatedArtical) {
        for (int i = 0; i < newsArticals.size(); i++) {
            NewsArtical existingArtical = newsArticals.get(i);
            if (existingArtical.getId().equals(id)) {
                newsArticals.set(i, updatedArtical);
                return "Updated News Article Successfully";
            }
        }
        return "News Article Not Found";
    }


    public String deleteNewsArtical(String id) {
        for (int i = 0; i < newsArticals.size(); i++) {
            if (newsArticals.get(i).getId().equals(id)) {
                newsArticals.remove(i);
                return "Deleted News Article Successfully";
            }
        }
        return "News Article Not Found";
    }

    


    public String publishNewsArtical(String id) {
        for (NewsArtical article : newsArticals) {
            if (article.getId().equals(id)) {
                article.setPublished(true);
            return    "Published News Article Successfully";
            }
        }
        return "News Article Not Found";
    }


    public List<NewsArtical> getPublishedNewsArticals() {
        return newsArticals.stream()
                .filter(NewsArtical::isPublished)
                .collect(Collectors.toList());
    }


    public List<NewsArtical> getNewsArticalsByCategory(String category) {
        return newsArticals.stream()
                .filter(article -> article.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}

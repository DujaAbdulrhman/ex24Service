/*
package com.example.newsarticalsystem.Service;

import com.example.newsarticalsystem.Model.NewsArtical;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class NewsArticalService {
    ArrayList<NewsArtical> newsArticals;

    public ArrayList<NewsArtical> getNewsArticals() {
        return newsArticals;
    }

    public NewsArtical addNewsArtical(NewsArtical newsArtical) {
        newsArticals.add(newsArtical);
        return newsArtical;
    }

    public ResponseEntity<String > updateNewsArtical(String index, NewsArtical newsArtical) {
        for (int i = 0; i < newsArticals.size(); i++) {
            NewsArtical newsArtical1 = newsArticals.get(i);
            if (newsArtical.getId().equals(index)){
                newsArtical.setId(index);
                return ResponseEntity.status(200).body("Updated Successfully");
            }
        }
        return ResponseEntity.status(404).body("News Article Not Found");
    }


    //TODO: اسوي ديليت و هي بترجع بوليان بس

}
*/
package com.example.newsarticalsystem.Service;

import com.example.newsarticalsystem.Model.NewsArtical;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // 3. Update a NewsArticle
    public ResponseEntity<String> updateNewsArtical(String id, NewsArtical updatedArtical) {
        for (int i = 0; i < newsArticals.size(); i++) {
            NewsArtical existingArtical = newsArticals.get(i);
            if (existingArtical.getId().equals(id)) {
                newsArticals.set(i, updatedArtical);
                return ResponseEntity.ok("Updated News Article Successfully");
            }
        }
        return ResponseEntity.status(404).body("News Article Not Found");
    }

    // 4. Delete a NewsArticle
    public ResponseEntity<String> deleteNewsArtical(String id) {
        for (int i = 0; i < newsArticals.size(); i++) {
            if (newsArticals.get(i).getId().equals(id)) {
                newsArticals.remove(i);
                return ResponseEntity.ok("Deleted News Article Successfully");
            }
        }
        return ResponseEntity.status(404).body("News Article Not Found");
    }

    // 5. Publish NewsArticles
    public ResponseEntity<String> publishNewsArtical(String id) {
        for (NewsArtical article : newsArticals) {
            if (article.getId().equals(id)) {
                article.setPublished(true);
                return ResponseEntity.ok("Published News Article Successfully");
            }
        }
        return ResponseEntity.status(404).body("News Article Not Found");
    }

    // 6. Get all Published NewsArticles
    public List<NewsArtical> getPublishedNewsArticals() {
        return newsArticals.stream()
                .filter(NewsArtical::isPublished)
                .collect(Collectors.toList());
    }

    // 7. Get NewsArticles by Category
    public List<NewsArtical> getNewsArticalsByCategory(String category) {
        return newsArticals.stream()
                .filter(article -> article.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}

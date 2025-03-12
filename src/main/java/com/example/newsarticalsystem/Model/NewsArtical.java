package com.example.newsarticalsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class NewsArtical {

    @NotEmpty(message = "the id cannot be empty")
    @NotNull
    @Size(min = 4)
    private String id;

    @NotNull
    @Size(min = 4,  max=20 )
    private String author;

    @NotNull
   // @Min(value = 200,message = "let it more than 200 ")
    private String content;

    @NotNull
    //TODO:Must be either "politics", " sports" or " technology" only.
    private String category;
    /*@NotNull
    private String imageUrl;*/

    @NotNull
    private boolean isPublished=false;

    //المفروض اشيل التايم بس ماعرف
    private Date publishDate;
}

package com.url.shortner_sb.Dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDTO {
   private Long id;
    private int clickCount;
    private LocalDateTime createdDate;
    private String shortUrl;
    private String originalUrl;
    private String username;

}

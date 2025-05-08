package com.url.shortner_sb.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "urlmapping")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalurl;
    private String shorturl;
    private int clickcount = 0 ;
    private LocalDateTime createDate ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "urlMapping")
    private List<ClickEvent> clickEvents;
}

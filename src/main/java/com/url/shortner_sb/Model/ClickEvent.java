package com.url.shortner_sb.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "clickevent")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime clickDate;



    @ManyToOne
    @JoinColumn(name = "url_mapping_id")
    private UrlMapping urlMapping;


}

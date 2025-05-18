package com.url.shortner_sb.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClickEventDTO {
    private Long  clickCount;
    private LocalDate clickDate;
}





package com.url.shortner_sb.Dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClickEventDTO {
    private int clickCount;
    private LocalDate clickDate;
}





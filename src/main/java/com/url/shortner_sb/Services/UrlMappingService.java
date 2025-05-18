package com.url.shortner_sb.Services;

import com.url.shortner_sb.Dtos.ClickEventDTO;
import com.url.shortner_sb.Dtos.UrlMappingDTO;
import com.url.shortner_sb.Model.ClickEvent;
import com.url.shortner_sb.Model.UrlMapping;
import com.url.shortner_sb.Model.User;
import com.url.shortner_sb.Repository.ClickEventRepository;
import com.url.shortner_sb.Repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

//import static java.util.stream.Nodes.collect;

@Service
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private ClickEventRepository clickEventRepository;


    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalurl(originalUrl);
        urlMapping.setShorturl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreateDate(LocalDateTime.now());

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);

        return convertToDTO(savedUrlMapping);
    }
    public UrlMappingService(UrlMappingRepository urlMappingRepository, ClickEventRepository clickEventRepository) {
        this.urlMappingRepository = urlMappingRepository;
        this.clickEventRepository = clickEventRepository;
    }


    private UrlMappingDTO convertToDTO(UrlMapping urlMapping) {
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalurl());
        urlMappingDTO.setShortUrl(urlMapping.getShorturl());
        urlMappingDTO.setClickCount(urlMapping.getClickcount());
        urlMappingDTO.setCreatedDate(urlMapping.getCreateDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDTO;
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    public List<UrlMappingDTO> getUrlsByUser(User user) {
        return urlMappingRepository.findByUser(user).stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<ClickEventDTO> getClickEventsByDate(String shorturls, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShorturl(shorturls);
        if(urlMapping != null){
                    return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping,start,end).stream()
                    .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(),Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                                    ClickEventDTO clickEventDTO = new ClickEventDTO();
                                   clickEventDTO.setClickDate(entry.getKey());
                                   clickEventDTO.setClickCount(entry.getValue());
                                   return clickEventDTO;
                            })
                            .collect(Collectors.toList());
        }

        return null;
    }

//

    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings,start.atStartOfDay() ,end.plusDays(1).atStartOfDay());
        return  clickEvents.stream()
                .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(),Collectors.counting()));

    }
}

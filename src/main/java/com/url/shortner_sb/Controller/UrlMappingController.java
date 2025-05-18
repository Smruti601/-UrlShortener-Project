package com.url.shortner_sb.Controller;

import com.url.shortner_sb.Dtos.ClickEventDTO;
import com.url.shortner_sb.Dtos.UrlMappingDTO;
import com.url.shortner_sb.Model.ClickEvent;
import com.url.shortner_sb.Model.UrlMapping;
import com.url.shortner_sb.Model.User;
import com.url.shortner_sb.Services.UrlMappingService;
import com.url.shortner_sb.Services.UserDetailsImpl;
import com.url.shortner_sb.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/url")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserService userService;
    //{ "Original url" : "https:example.com" }

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String,String> request, Principal principal){

        String originalUrl = request.get("originalUrl");
        User user =  userService.findByUserName(principal.getName());

        //call Service method
        UrlMappingDTO urlMappingDTO = urlMappingService.createShortUrl(originalUrl,user);
        return ResponseEntity.ok(urlMappingDTO);
    }


    @GetMapping("/myurls")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<UrlMappingDTO>>getUserUrls(Principal principal){

        User user = userService.findByUserName(principal.getName());
        List<UrlMappingDTO> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/api/url/shorten/analytics/{shortUrl}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ClickEventDTO>>getUrlAnalytics(@PathVariable String shorturls,
                                                              @RequestParam("startDate") String startDate,
                                                              @RequestParam("endDate") String endDate){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;//2024-12-01T00:00:12
        LocalDateTime start = LocalDateTime.parse(startDate,formatter);
        LocalDateTime end = LocalDateTime.parse(endDate,formatter);
        List<ClickEventDTO> clickEventDTOS = urlMappingService.getClickEventsByDate(shorturls,start,end);

        return ResponseEntity.ok(clickEventDTOS);
    }

    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<LocalDate,Long>> getTotalClicksByDate(Principal principal,
                                                            @RequestParam("startDate") String startDate,
                                                            @RequestParam("endDate") String endDate){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;//2024-12-01
        User user = userService.findByUserName(principal.getName());
        LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);
        Map<LocalDate , Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user,start,end);

        return ResponseEntity.ok(totalClicks);
    }
}

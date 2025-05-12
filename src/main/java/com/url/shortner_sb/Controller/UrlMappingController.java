package com.url.shortner_sb.Controller;

import com.url.shortner_sb.Dtos.UrlMappingDTO;
import com.url.shortner_sb.Model.UrlMapping;
import com.url.shortner_sb.Model.User;
import com.url.shortner_sb.Services.UrlMappingService;
import com.url.shortner_sb.Services.UserDetailsImpl;
import com.url.shortner_sb.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
}

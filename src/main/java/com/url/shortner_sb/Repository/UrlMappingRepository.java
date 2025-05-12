package com.url.shortner_sb.Repository;

import com.url.shortner_sb.Model.UrlMapping;
import com.url.shortner_sb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShorturl(String shorturl);
    List<UrlMapping> findByUser(User user);
}

package it.course.springjsp.repository;

import it.course.springjsp.model.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Long> {

    WebSiteInfo findFirstByOrderByIdDesc();

    List<WebSiteInfo> findAll();

}
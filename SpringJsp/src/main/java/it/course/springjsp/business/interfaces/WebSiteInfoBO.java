package it.course.springjsp.business.interfaces;

import it.course.springjsp.model.WebSiteInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WebSiteInfoBO {

    WebSiteInfo getInfo(long id) throws DataAccessException;

    WebSiteInfo getWebSiteInfo() throws DataAccessException;

    List<WebSiteInfo> getAllWebSiteInfo() throws DataAccessException;

    void insertWebSiteInfo(WebSiteInfo webSiteInfo) throws DataAccessException;

    void updateWebSiteInfo(WebSiteInfo webSiteInfo) throws DataAccessException;

    void deleteWebSiteInfo(long id) throws DataAccessException;

}

package it.course.springjsp.business.impl;

import it.course.springjsp.business.interfaces.WebSiteInfoBO;
import it.course.springjsp.model.WebSiteInfo;
import it.course.springjsp.repository.WebSiteInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSiteInfoBOImpl implements WebSiteInfoBO {
    @Autowired
    WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public WebSiteInfo getWebSiteInfo() throws DataAccessException {
        return this.webSiteInfoRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public List<WebSiteInfo> getAllWebSiteInfo() throws DataAccessException {
        return this.webSiteInfoRepository.findAll();
    }

    @Override
    public void insertWebSiteInfo(WebSiteInfo webSiteInfo) throws DataAccessException {
        this.webSiteInfoRepository.save(webSiteInfo);
    }

}

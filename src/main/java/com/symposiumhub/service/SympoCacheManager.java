package com.symposiumhub.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import com.symposiumhub.Enum.CacheIdentifier;
import com.symposiumhub.Enum.CacheNameEnum;
import com.symposiumhub.database.dao.SymposiumDao;

@Component
public class SympoCacheManager implements InitializingBean, ServletContextListener {

	@Autowired
	SymposiumDao sDao;

	static Logger log = Logger.getLogger(SympoCacheManager.class.getName());

	private CacheManager CACHEMANAGEROBJECT;

	@Override
	public void afterPropertiesSet() throws Exception {

		CacheManager manager = CacheManager.newInstance();
		this.CACHEMANAGEROBJECT = manager;
		Cache cacheObject = null;
		try {
			this.CACHEMANAGEROBJECT.addCache(CacheNameEnum.SymposiumCache.name());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		cacheObject = getCacheObject(CacheNameEnum.SymposiumCache.name());
		cacheObject.put(new Element(CacheIdentifier.SymposiumListVIewCache.name(), sDao.getAllSymposium()));

	}

	public Cache getCacheObject(String cache) {

		Cache cacheObject = this.CACHEMANAGEROBJECT.getCache(cache);

		if (cacheObject == null) {
			CacheManager manager = CacheManager.newInstance();
			this.CACHEMANAGEROBJECT = manager;
			this.CACHEMANAGEROBJECT.addCache(CacheNameEnum.SymposiumCache.name());
			cacheObject = getCacheObject(CacheNameEnum.SymposiumCache.name());
			cacheObject.put(new Element(CacheIdentifier.SymposiumListVIewCache.name(), sDao.getAllSymposium()));
		}

		return cacheObject;

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		try {
			this.CACHEMANAGEROBJECT.shutdown();
		} catch (Exception e) {
			log.info("bean not shutdown");
		}

	}


	public void refreshCache(String cacheType) {
		try {
			if (cacheType.equals("symposiumList")) {

				Cache cacheObject = getCacheObject(CacheNameEnum.SymposiumCache.name());
				cacheObject.remove(CacheIdentifier.SymposiumListVIewCache.name());

				cacheObject.put(new Element(CacheIdentifier.SymposiumListVIewCache.name(), sDao.getAllSymposium()));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

}

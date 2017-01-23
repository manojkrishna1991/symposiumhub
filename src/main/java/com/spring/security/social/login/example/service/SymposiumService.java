package com.spring.security.social.login.example.service;



import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.social.login.example.Enum.CacheIdentifier;
import com.spring.security.social.login.example.Enum.CacheNameEnum;
import com.spring.security.social.login.example.database.dao.CordinatorDao;
import com.spring.security.social.login.example.database.dao.RegsiterForSymposiumDao;
import com.spring.security.social.login.example.database.dao.SubscribeDao;
import com.spring.security.social.login.example.database.dao.SymposiumDao;
import com.spring.security.social.login.example.database.dao.SymposiumFieldsDao;
import com.spring.security.social.login.example.database.model.Coordinator;
/*import com.spring.security.social.login.example.database.model.ImageUrl;
*/import com.spring.security.social.login.example.database.model.RegisterForASymposium;
import com.spring.security.social.login.example.database.model.Subscribe;
import com.spring.security.social.login.example.database.model.Symposium;
import com.spring.security.social.login.example.database.model.SymposiumRegistrationFields;

@Service("sympService")
public class SymposiumService implements SymposiumServiceInterface {
	static Logger log = Logger.getLogger(SymposiumService.class.getName());
	
    @Autowired
    private SymposiumDao sympDAO;
    

    
    @Autowired
    private SubscribeDao subDAO;
    
    @Autowired
    private CordinatorDao cordinatorDao;
    
    @Autowired
    private RegsiterForSymposiumDao regsiterForSymposiumDao;
    
    @Autowired
    private SymposiumFieldsDao sDao;
    
	@Autowired
	private SympoCacheManager sympoCache;
    

	
	/*@Override
	@Transactional
	public void saveImage(ImageUrl image){
		imageDao.save(image);
	}
*/
	
	
	
	@Override
	@Transactional(value = "transactionManager")
	public Symposium SaveNewSymposium(Symposium symposium) {
		// TODO Auto-generated method stub
		sympDAO.save(symposium);
		sympDAO.flush();
		sympoCache.refreshCache("symposiumList");
		return symposium;
	}
	
	@Override
	@Transactional(value = "transactionManager")
	public Symposium updateSymposium(Symposium symposium) {
		// TODO Auto-generated method stub
		sympDAO.saveOrUpdate(symposium);
		sympDAO.flush();
		sympoCache.refreshCache("symposiumList");
		return symposium;
	}
	
	
	@Override
	@Transactional(value = "transactionManager")
	public RegisterForASymposium regsiterForSymposium(
			RegisterForASymposium regsymposium) {
		// TODO Auto-generated method stub
		regsiterForSymposiumDao.save(regsymposium);
		regsiterForSymposiumDao.flush();
		return regsymposium;
	}

	
	@Override
	public Symposium getSymposium(String id) {
		// TODO Auto-generated method stub
		
		
		return sympDAO.get(id);
	}
	
	@Override
	@Transactional(value = "transactionManager",readOnly=true )
	public List<Symposium> getSymposiumByUserId(String userId) {
		// TODO Auto-generated method stub
		
		
		return sympDAO.getSymposiums(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Symposium> getSymposium() {
		List<Symposium> symposiumListFromCache = null;

		log.info("starting to retrieve the symposium list from cache");
		try {
			Cache cacheObject = sympoCache
					.getCacheObject(CacheNameEnum.SymposiumCache.name());
			symposiumListFromCache = (List<Symposium>) cacheObject.get(
					CacheIdentifier.SymposiumListVIewCache.name())
					.getObjectValue();
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info(" retrieve the symposium list from cache failed");
		}
		log.info(" retrieve the symposium list from cache ended");
		if (symposiumListFromCache == null || symposiumListFromCache.isEmpty()) {
			log.info(" retrieve the symposium list from database since the cache failed");
			return sympDAO.getAllSymposium();
		}
		return symposiumListFromCache;

	}
	
	public List<Symposium> getSymposiumsBySymposiumId(String userId,String symposiumId)
	{
		return sympDAO.getSymposiumsBySymposiumId(userId,symposiumId);
	}
	public List<Symposium> getSymposiumName(String userId)
	{
		return sympDAO.getSymposiumName(userId); 
	}

	
	@Override
	@Transactional(value = "transactionManager")
	public void saveSubcribtion(Subscribe subscribe) {
		// TODO Auto-generated method stub
		subDAO.save(subscribe);
		subDAO.flush();
	}
	@Override
	@Transactional(value = "transactionManager")
	public void saveCordinator(Coordinator coordinator) {
		// TODO Auto-generated method stub
		cordinatorDao.saveOrUpdate(coordinator);
		cordinatorDao.flush();
	}
	@Override
	@Transactional(value = "transactionManager")
	public void saveREgisterFields(SymposiumRegistrationFields sFields) {
		// TODO Auto-generated method stub
		sDao.saveOrUpdate(sFields);
		sDao.flush();
	}
	
	@Override
	@Transactional(value = "transactionManager",readOnly=true )
	public List<RegisterForASymposium> getSymposiumRegistrations(String symposiumId) {
		// TODO Auto-generated method stub
		
		
		return regsiterForSymposiumDao.getSymposiumRegistrationaBySympId(symposiumId);
	}
	@Transactional(value = "transactionManager")
	public List<Object[]> getValidSymposiumRegistrations() {
		
		return sympDAO.getValidSymposiumRegistrations();
		
	}
	@Transactional(value = "transactionManager")
	public void updateStatusToIsMailSent(List<String> list) {
		 sympDAO.updateStatusToIsMailSent(list);

	}
	
}

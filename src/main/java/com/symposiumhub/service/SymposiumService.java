package com.symposiumhub.service;



import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.symposiumhub.Enum.CacheIdentifier;
import com.symposiumhub.Enum.CacheNameEnum;
import com.symposiumhub.database.dao.CordinatorDao;
import com.symposiumhub.database.dao.PapersDao;
import com.symposiumhub.database.dao.RegsiterForSymposiumDao;
import com.symposiumhub.database.dao.SubscribeDao;
import com.symposiumhub.database.dao.SymposiumCommentDao;
import com.symposiumhub.database.dao.SymposiumCommentsReplyDao;
import com.symposiumhub.database.dao.SymposiumDao;
import com.symposiumhub.database.dao.SymposiumFieldsDao;
import com.symposiumhub.dto.SymposiumCommentDto;
import com.symposiumhub.dto.SymposiumDto;
import com.symposiumhub.model.Coordinator;
import com.symposiumhub.model.Papers;
import com.symposiumhub.model.RegisterForASymposium;
import com.symposiumhub.model.Subscribe;
import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.SymposiumComment;
import com.symposiumhub.model.SymposiumCommentsReply;
import com.symposiumhub.model.SymposiumRegistrationFields;

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
    private PapersDao papersDao;
    
    @Autowired
    private RegsiterForSymposiumDao regsiterForSymposiumDao;
    
    @Autowired
    private SymposiumFieldsDao sDao;
    
    @Autowired
    private SymposiumCommentDao symposiumCommentDao;
    
    @Autowired
    private SymposiumCommentsReplyDao symposiumCommentsReplyDao;
    
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
	@Transactional(value = "transactionManager",readOnly=true )
	public SymposiumDto getSymposium(String id) {
		// TODO Auto-generated method stub
		
		Symposium symposium = sympDAO.get(id);
		
		SymposiumDto symposiumDto = new SymposiumDto(symposium);
		
		return  symposiumDto ;
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
	
	@Transactional(value = "transactionManager",readOnly=true )
	@Override
	public List<Symposium> getSymposiumByLimit(){
		
		return sympDAO.getAllSymposiumByLimit();
		
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
	public void savePapers(Papers papers) {
		// TODO Auto-generated method stub
		papersDao.saveOrUpdate(papers);
		papersDao.flush();
	}
	@Override
	@Transactional(value = "transactionManager")
	public void saveSymposiumComments(SymposiumComment symposiumComment) {
		// TODO Auto-generated method stub
		symposiumCommentDao.saveOrUpdate(symposiumComment);
		symposiumCommentDao.flush();
	}
	@Override
	@Transactional(value = "transactionManager",readOnly=true )
	public SymposiumCommentDto getSymposiumComments(String commentId){
		return     new SymposiumCommentDto(symposiumCommentDao.get(commentId));
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

	@Override
	@Transactional(value = "transactionManager")
	public void saveSymposiumCommentsReply(SymposiumCommentsReply symposiumCommentsReply) {
		// TODO Auto-generated method stub
		symposiumCommentsReplyDao.saveOrUpdate(symposiumCommentsReply);
		symposiumCommentsReplyDao.flush();
		
	}
	
}

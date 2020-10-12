package bld.generator.report.db2.persistence.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bld.generator.report.db2.persistence.domain.UserEntity;
import bld.generator.report.db2.persistence.domain.UserEntityRepository;

/**
 * The Class UserEntityServiceImpl.
 */
@Service
@Transactional
public class UserEntityServiceImpl implements UserEntityService {
    
    /** The user entity repository. */
    @Autowired
    private UserEntityRepository userEntityRepository;
    
    /**
     * Count all user entitys.
     *
     * @return the long
     */
    public long countAllUserEntitys() {
        return userEntityRepository.count();
    }
    
    /**
     * Delete user entity.
     *
     * @param userEntity the user entity
     */
    public void deleteUserEntity(UserEntity userEntity) {
        userEntityRepository.delete(userEntity);
    }
    
    /**
     * Find user entity.
     *
     * @param id the id
     * @return the user entity
     */
    public UserEntity findUserEntity(String id) {
        return userEntityRepository.findById(id).get();
    }
    
    /**
     * Find all user entitys.
     *
     * @return the list
     */
    public List<UserEntity> findAllUserEntitys() {
        return userEntityRepository.findAll();
    }
    
    /**
     * Find user entity entries.
     *
     * @param firstResult the first result
     * @param maxResults the max results
     * @return the list
     */
    public List<UserEntity> findUserEntityEntries(int firstResult, int maxResults) {
        return userEntityRepository.findAll(PageRequest.of(firstResult / maxResults, maxResults)).getContent();
    }
    
    /**
     * Save user entity.
     *
     * @param userEntity the user entity
     */
    public void saveUserEntity(UserEntity userEntity) {
        userEntityRepository.save(userEntity);
    }
    
    /**
     * Update user entity.
     *
     * @param userEntity the user entity
     * @return the user entity
     */
    public UserEntity updateUserEntity(UserEntity userEntity) {
        return userEntityRepository.save(userEntity);
    }
    
   
}

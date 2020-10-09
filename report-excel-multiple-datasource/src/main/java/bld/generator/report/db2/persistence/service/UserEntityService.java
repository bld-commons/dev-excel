package bld.generator.report.db2.persistence.service;
import java.util.List;

import bld.generator.report.db2.persistence.domain.UserEntity;

/**
 * The Interface UserEntityService.
 */
public interface UserEntityService {
    
    /**
     * Count all user entitys.
     *
     * @return the long
     */
    public abstract long countAllUserEntitys();    
    
    /**
     * Delete user entity.
     *
     * @param userEntity the user entity
     */
    public abstract void deleteUserEntity(UserEntity userEntity);    
    
    /**
     * Find user entity.
     *
     * @param id the id
     * @return the user entity
     */
    public abstract UserEntity findUserEntity(String id);    
    
    /**
     * Find all user entitys.
     *
     * @return the list
     */
    public abstract List<UserEntity> findAllUserEntitys();    
    
    /**
     * Find user entity entries.
     *
     * @param firstResult the first result
     * @param maxResults the max results
     * @return the list
     */
    public abstract List<UserEntity> findUserEntityEntries(int firstResult, int maxResults);    
    
    /**
     * Save user entity.
     *
     * @param userEntity the user entity
     */
    public abstract void saveUserEntity(UserEntity userEntity);    
    
    /**
     * Update user entity.
     *
     * @param userEntity the user entity
     * @return the user entity
     */
    public abstract UserEntity updateUserEntity(UserEntity userEntity);
    
  
}

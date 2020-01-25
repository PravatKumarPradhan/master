package com.sdgt.medcare.master.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@NoRepositoryBean
public interface MastersBaseRepository<T, ID> extends JpaRepository<T, ID> {
    /**
     * Get {@link T} by given desc
     * @param desc given.
     * @return return {@link T} if found. Else null.
     */
    T findByDescIgnoreCase(String desc);
}

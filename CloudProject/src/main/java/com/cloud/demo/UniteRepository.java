package com.cloud.demo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data SQL repository for the Unit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UniteRepository extends JpaRepository<Unite, Long>, JpaSpecificationExecutor<Unite> {}

package com.domain.department.chevelleuimicro.authorization;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class AuthRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BigDecimal> getRoleByUserId(String userId){
        Query query = entityManager.createNativeQuery("SELECT ROLE_ID FROM EDA_CORE.EDA_META_USR_ROLE WHERE USR_ID = ?");
        query.setParameter(1,userId);

        return query.getResultList();
    }
}

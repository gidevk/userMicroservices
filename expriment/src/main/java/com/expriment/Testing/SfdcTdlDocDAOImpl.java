package com.expriment.Testing;


import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SfdcTdlDocDAOImpl implements SfdcTdlDocDAO {

    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public SfdcTdlDocResponse saveOrUpdateSfdcTdlDoc(SfdcTdlDocResponse sfdcTdlDocResponse) {
        return hibernateUtils.saveOrUpdateEntity(sfdcTdlDocResponse);
    }

    @Override
    public SfdcTdlDocResponse getSfdcTdlDocByLeadId(Integer leadId){
        return hibernateUtils.findEntityById(SfdcTdlDocResponse.class, leadId);
    }
}

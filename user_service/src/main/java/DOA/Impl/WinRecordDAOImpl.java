package DOA.Impl;

import Entity.WinRecord;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WinRecordDAOImpl implements DOA.WinRecordDAO {

    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public WinRecord saveWinRecord(WinRecord winRecord) {
        return hibernateUtils.saveOrUpdateEntity(winRecord);
    }
    @Override
    public WinRecord getWinRecordById(int Id){
       return hibernateUtils.findEntityById(WinRecord.class, Id);
    }
}

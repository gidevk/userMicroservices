package DOA.Impl;

import DOA.ColourDAO;
import Entity.Colour;
import com.expriment.utils.audit.Hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColourDAOImpl implements ColourDAO {

    @Autowired
    HibernateUtils hibernateUtils;

    @Override
    public Colour saveColour(Colour colour) {
        return hibernateUtils.saveOrUpdateEntity(colour);
    }
    @Override
    public Colour getColourById(int Id){
       return hibernateUtils.findEntityById(Colour.class, Id);
    }
}

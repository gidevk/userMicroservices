package com.expriment.service.serviceImpl;

import com.expriment.entity.vo.OperationValue;
import com.expriment.service.OperationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    final Logger logger= LogManager.getLogger("EmudraServiceImpl");

    @Override
    public Integer addition(OperationValue operationValue){
        int result =0;
        try {
            result= operationValue.getNumber1()+operationValue.getNumber2();
            logger.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

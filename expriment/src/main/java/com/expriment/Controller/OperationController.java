package com.expriment.Controller;

import com.expriment.entity.vo.OperationValue;
import com.expriment.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/oper/")
public class OperationController {

    @Autowired
    OperationService operationService;

    @PostMapping(value ="add",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer add(@RequestBody OperationValue operationValue){
        return operationService.addition(operationValue);
    }
}

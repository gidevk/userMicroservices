package com.expriment.utils.audit.ExceptionHandling;

import com.expriment.utils.ProjectConstants;
//import org.molgenis.util.exception.BadRequestException;
import com.expriment.utils.ProjectUtils;
import com.expriment.utils.audit.entity.vo.RootResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.expriment.utils.audit.entity.vo.DefaultResponse;

@ControllerAdvice
public class GenericExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GenericExceptionHandler.class);




    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGenericException(Throwable e) {
        logger.error(ProjectConstants.EXCEPTION_OCCURRED, e);
        DefaultResponse response = new DefaultResponse();
        response.setMessage(ProjectConstants.API_FAIL);
        response.setStatus(ProjectConstants.FAILURE);
        response.setStatusCode(500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<?> handleGenericException(ProjectException projectException) {
        RootResponse rootResponse = new RootResponse();
        rootResponse.setRetStatus(projectException.getCause().toString());
        rootResponse.setSysErrorCode(ProjectConstants.PROJECT_ERROR_CODE);
        rootResponse.setSysErrorMessage(projectException.getMessage());
        return new ResponseEntity<>(rootResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
//  @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<?> handleGenericException(BadRequestException badRequest) {
//        return new ResponseEntity<>(ProjectUtils.prepareDefaultResponse(badRequest.getMessage(), 400), HttpStatus.BAD_REQUEST);
//    }
//
//
//  @ExceptionHandler(FileNotFoundException.class)
//    public ResponseEntity<?> handleGenericException(FileNotFoundException fileNotFound) {
//        logger.error(ProjectConstants.EXCEPTION_OCCURRED, fileNotFound);
//        return new ResponseEntity<>(ProjectUtils.prepareDefaultResponse("File Not Found", 404), HttpStatus.NOT_FOUND);
//    }


}

package com.expriment.utils.audit.ExceptionHandling;

import com.expriment.utils.ProjectConstants;
import com.expriment.utils.ProjectUtils;
//import org.molgenis.util.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.expriment.utils.audit.entity.vo.DefaultResponse;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GenericExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GenericExceptionHandler.class);

    /**
     * Handling generic expections if not handled in controllers and sending default response to UI
     *
     * @param e
     * @return
     */

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGenericException(Throwable e) {
        logger.error(ProjectConstants.EXCEPTION_OCCURRED, e);
        DefaultResponse response = new DefaultResponse();
        response.setMessage(ProjectConstants.API_FAIL);
        response.setStatus(ProjectConstants.FAIL);
        response.setStatusCode(500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

   /* @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> handleGenericException(NoDataFoundException noDataFound) {
        return new ResponseEntity<>(ProjectUtils.prepareDefaultResponse(noDataFound.getMessage(), 404), HttpStatus.NOT_FOUND);
    }*/

  /*  @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleGenericException(BadRequestException badRequest) {
        return new ResponseEntity<>(ProjectUtils.prepareDefaultResponse(badRequest.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }*/

  /*  @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleGenericException(FileNotFoundException fileNotFound) {
        logger.error(ProjectConstants.EXCEPTION_OCCURRED, fileNotFound);
        return new ResponseEntity<>(ProjectUtils.prepareDefaultResponse("File Not Found", 404), HttpStatus.NOT_FOUND);
    }*/

}

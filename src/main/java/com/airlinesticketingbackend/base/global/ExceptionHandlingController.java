package com.airlinesticketingbackend.base.global;

import com.airlinesticketingbackend.base.exception.ProcessResultException;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.dto.common.GenericResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.airlinesticketingbackend"})
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProcessResultException.class})
    public ResponseEntity<?> handleProcessResultException(final ProcessResultException exception) {
        if (exception.isInvolvedGeneric()) {
            final GenericResult<?> response = new GenericResult<>();
            response.setProcessResult(exception.getProcessResult());

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.badRequest().body(exception.getProcessResult());
    }

    @ExceptionHandler({ProvideExceptionHandler.class})
    public ResponseEntity<?> handleProvideExceptionHandler(final ProvideExceptionHandler ex) {
        logger.error(ex.getClass().getName());
        final String detailedMessage = StringUtils.isEmpty(ex.getDetailedMessage()) ? ex.getMessage() : ex.getDetailedMessage();
        return ResponseEntity.badRequest().body(detailedMessage);
    }
}

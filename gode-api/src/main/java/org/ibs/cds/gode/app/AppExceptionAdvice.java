package org.ibs.cds.gode.app;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.exception.GodeException;
import org.ibs.cds.gode.exception.GodeRuntimeException;
import org.ibs.cds.gode.status.BinaryStatus;
import org.ibs.cds.gode.web.context.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.BindException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class AppExceptionAdvice {
    public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";
    protected static final Logger pageNotFoundLogger = LoggerFactory.getLogger(PAGE_NOT_FOUND_LOG_CATEGORY);
    private final static String LOG_TEMPLATE = "Type: {} | Stack : {}";


    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageConversionException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class
    })
    @Nullable
    public final ResponseEntity<ReturnMessage> handleException(Exception ex, WebRequest request) throws Exception {
        logException(ex);
        return handleExceptionInternal(ex, processMessageException(ex), request);
    }

    private Pair<Error, HttpStatus> processMessageException(Exception ex) {
        String exceptionMessage = "Message: "+ExceptionUtils.getMessage(ex);
        String exceptionRootCause = "Root cause message: "+ExceptionUtils.getRootCauseMessage(ex);
        Error error = new Error(100,"Message origin or chain of command error",String.join("|", exceptionMessage, exceptionRootCause));
        return Pair.of(error, HttpStatus.BAD_REQUEST);
    }

    private Pair<Error, HttpStatus> processException(Throwable ex) {
        String exceptionMessage = "Message: "+ExceptionUtils.getMessage(ex);
        String exceptionRootCause = "Root cause message: "+ExceptionUtils.getRootCauseMessage(ex);
        Error error = new Error(101,"Application runtime exception",String.join("|", exceptionMessage, exceptionRootCause));
        return Pair.of(error, HttpStatus.BAD_REQUEST);
    }

    private void logException(Exception ex) {
        log.error(LOG_TEMPLATE, ex.getClass(), ExceptionUtils.getStackTrace(ex));
    }


    protected ResponseEntity<ReturnMessage> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        pageNotFoundLogger.warn(ex.getMessage());

        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }
        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleServletRequestBindingException(
            ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleConversionNotSupported(
            ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleMissingServletRequestPart(
            MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<ReturnMessage> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    @Nullable
    protected ResponseEntity<ReturnMessage> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                    log.warn("Async request timed out");
                return null;
            }
        }

        return handleExceptionInternal(ex, null, headers, status, webRequest);
    }


    protected ResponseEntity<ReturnMessage> handleExceptionInternal(
            Exception ex, @Nullable Error body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logException(ex);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setError(body);
        returnMessage.setStatus(BinaryStatus.FAILURE);
        return new ResponseEntity<>(returnMessage, headers, status);
    }

    protected ResponseEntity<ReturnMessage> handleExceptionInternal(
            Exception ex, @Nullable Pair<Error, HttpStatus> body, WebRequest request) {
        HttpStatus httpStatus = body.getRight();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return newResponseEntity(body);
    }

    @ExceptionHandler(GodeException.class)
    public ResponseEntity<ReturnMessage> handleGodeException(GodeException ex) {
        logException(ex);
        return newResponseEntity(Pair.of(ex.getError(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(GodeRuntimeException.class)
    public ResponseEntity<ReturnMessage> handleGodeRuntimeException(GodeException ex) {
        logException(ex);
        return newResponseEntity(Pair.of(ex.getError(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ReturnMessage> handleException(RuntimeException ex, WebRequest req) {
        logException(ex);
        return newResponseEntity(processException(ex));
    }

    private ResponseEntity<ReturnMessage> newResponseEntity(Pair<Error, HttpStatus> classifiedError) {
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setError(classifiedError.getLeft());
        returnMessage.setStatus(BinaryStatus.FAILURE);
        return newResponseEntity(returnMessage, classifiedError.getRight());
    }

    private ResponseEntity<ReturnMessage> newResponseEntity(ReturnMessage returnMessage, HttpStatus status) {
        return new ResponseEntity(returnMessage, new HttpHeaders(), status);
    }
}

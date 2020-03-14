package co.udea.heroes.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity handleBusinessException(
            HttpServletRequest request, BusinessException ex) {
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity<>( ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({DataDuplicatedException.class})
    protected ResponseEntity handleDataDuplicatedException(
            HttpServletRequest request, DataDuplicatedException ex) {
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity<>( ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity handleNotFoundException(
            HttpServletRequest request, NotFoundException ex) {
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity<>( ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity handleUnhandledException(
            HttpServletRequest request, Throwable ex) {
        //TODO: Enviar correo electrónico con error no manejado
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity<>( "No se ha podido procesar su solicitud. Contacte al administrador.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

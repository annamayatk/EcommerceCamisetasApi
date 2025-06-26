package com.stylescoder.exception;

import com.stylescoder.exception.UsuarioException;
import com.stylescoder.exception.ErroResposta;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + ": " + erro.getDefaultMessage());
        }

        ErroResposta resposta = new ErroResposta(
                status.value(),
                "Campos inválidos na requisição",
                LocalDateTime.now(),
                erros
        );

        return super.handleExceptionInternal(ex, resposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        List<String> erros = new ArrayList<>();
        erros.add("Formato de JSON inválido");

        ErroResposta resposta = new ErroResposta(
                status.value(),
                "Erro de leitura da requisição",
                LocalDateTime.now(),
                erros
        );

        return super.handleExceptionInternal(ex, resposta, headers, status, request);
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<Object> handleUsuarioException(UsuarioException ex) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());

        ErroResposta resposta = new ErroResposta(
                404,
                "Erro ao processar usuário",
                LocalDateTime.now(),
                erros
        );

        return ResponseEntity.status(404).body(resposta);
    }
}

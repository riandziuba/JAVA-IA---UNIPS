package dev.rdziuba.aula_01.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UpperCase")
public class MessageUpperCaseService implements IMessageService{
    @Override
    public String sayCustomMessage(String original) {
        return original.toUpperCase();
    }
}

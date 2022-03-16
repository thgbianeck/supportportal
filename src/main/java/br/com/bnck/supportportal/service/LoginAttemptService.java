package br.com.bnck.supportportal.service;

import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 22/02/2022
 * Hora: 23:44
 */
public class LoginAttemptService {

    private static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 5;
    private static final int ATTEMPT_INCREMENT = 1;
    private LoadingCache<String, Integer> loginAttemptCache;

    public void evictUserFromLoginAttemptCache(String username) {
        loginAttemptCache.invalidate(username);
    }

    public boolean hasExceededMaxAttempts(String username) {
        try{
            return loginAttemptCache.get(username) >= MAXIMUM_NUMBER_OF_ATTEMPTS;
        } catch(final ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}

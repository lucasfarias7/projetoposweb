package br.edu.unipe.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CacheControllerTest {

    @InjectMocks
    private CacheController cacheController;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(cacheManager.getCache(eq("cache_nome"))).thenReturn(cache);
    }

    @Test
    void testConsultarNome() {
        // Teste para verificar se o método consultarNome retorna o valor esperado.
        String result = cacheController.consultarNome();

        assertEquals("NOME CONSULTADO", result);
    }

    @Test
    void testResetCache() {
        // Teste para verificar se o cache é limpo corretamente.
        doNothing().when(cache).clear();

        cacheController.resetCache();

        verify(cacheManager, times(1)).getCache("cache_nome");
        verify(cache, times(1)).clear();
    }
}

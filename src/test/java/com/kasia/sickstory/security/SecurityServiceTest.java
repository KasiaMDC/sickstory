package com.kasia.sickstory.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityServiceTest {

    private SecurityService securityService;

    @BeforeEach
    public void setUp() {
        securityService = new SecurityService();
    }

    @Test
    public void testGenerateBasicAuthHeader() throws Exception {
        // given
        String username = "coea44";
        String password = "a";

        // when
        String actualBasicAuthHeader = securityService.generateBasicAuthHeader(username, password);

        // then
        String expectedBasicAuthHeader = "Basic Y29lYTQ0OmE=";
        assertEquals(expectedBasicAuthHeader, actualBasicAuthHeader);
    }

    @Test
    public void testGenerateMD5() throws Exception {
        // given

        // when
        String hashedString = securityService.generateMD5("a");

        // then
        assertEquals("0cc175b9c0f1b6a831c399e269772661", hashedString);
    }
}
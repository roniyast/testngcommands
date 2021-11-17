package com.obsqura.testngcommands;

import org.testng.annotations.Test;

public class LoginTest {
    @Test(priority = 3,enabled = true,groups = {"regressionTest","sanityTest"})
    public void verifyLoginPageTitle(){
        System.out.println("Verifying Login Page Title");
    }

    @Test(priority = 4,enabled = true,groups = {"sanityTest"},dependsOnMethods = {"verifyLoginPageTitle"})
    public void verifyValidLogin(){
        System.out.println("Verifying Login ");

    }
}

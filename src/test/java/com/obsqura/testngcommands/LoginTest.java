package com.obsqura.testngcommands;

import org.testng.annotations.Test;

public class LoginTest {
    @Test(priority = 3,enabled = true)
    public void verifyLoginPageTitle(){
        System.out.println("3");
    }

    @Test(priority = 4,enabled = true)
    public void verifyValidLogin(){
        System.out.println("4");

    }
}

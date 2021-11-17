package com.obsqura.testngcommands;
import org.testng.annotations.Test;


public class HomeTest {

    @Test(priority =1,enabled= true,groups={"regressionTest"})
    public void verifyHomePageTitle(){
        System.out.println("Verifying Home Page Title");
    }

    @Test(priority=2,enabled=true,groups={"regressionTest","sanityTest"})
    public void verifyHomePageUserMenu(){
        System.out.println("Verifying Home Page User menu");
    }

}

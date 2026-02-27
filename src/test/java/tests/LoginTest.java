package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class LoginTest extends BaseTest{
	@Test
	public void verifyLoginWithValidUsernameAndPassword() {
	}
	@Test
	public void verifyLoginWithValidUsernameAndInvalidPassword() {
		Assert.assertTrue(false);
	}
	//@Test
	public void verifyLoginWithInvalidUsernameAndValidPassword() {
		System.out.println("Test3");
	}
	//@Test
	public void verifyLoginWithInvalidUsernameAndPassword() {
		System.out.println("Test4");
	}
}

package com.snow.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class LoginControllerPowerMockTest {

    @InjectMocks
    private LoginControllerPowerMock login;

    @Mock
    private LoginUtil loginUtil;


    @PrepareForTest(LoginControllerPowerMock.class)
    @Test
    public void testConstructor() throws Exception {
        LoginUtil loginUtilStub = Mockito.mock(LoginUtil.class);
        Mockito.when(loginUtilStub.toString()).thenReturn("This is a new string");
        PowerMockito.whenNew(LoginUtil.class).withArguments("aa", "bb")
                .thenReturn(loginUtilStub);
        String re = login.getConstructLogin("aa", "bb");
        Assert.assertEquals("This is a new string", re);
    }

    @Test
    @PrepareForTest(LoginUtil.class)
    public void testFinal() {
        PowerMockito.when(loginUtil.getCurrentUserName()).thenReturn("final current user");
        String re = login.getFinalLogin();
        Assert.assertEquals("final current user", re);
    }

    @Test
    @PrepareForTest(LoginUtil.class)
    public void testPrivate() throws Exception {
        PowerMockito.when(loginUtil.getDefaultLogin()).thenCallRealMethod();
        PowerMockito.when(loginUtil, "getDefaultLoginUserName", "aaa").thenReturn("default private user");
        String re = loginUtil.getDefaultLogin();
        Assert.assertEquals("incorrect", "default private user", re);
    }

    @Test
    @PrepareForTest(LoginUtil.class)
    public void testStatic() {
        PowerMockito.mockStatic(LoginUtil.class);
        PowerMockito.when(LoginUtil.getStaticUserName()).thenReturn("abc");
        String re = login.getStaticLogin();
        Assert.assertEquals("incorrent message", "abc", re);
    }


}

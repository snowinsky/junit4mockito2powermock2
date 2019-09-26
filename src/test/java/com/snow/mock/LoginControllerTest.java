package com.snow.mock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * LoginController Tester.
 *
 * @author current user
 * @version 1.0
 * @since <pre> 09/25/2019</pre>
 */
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Spy
    @InjectMocks
    private LoginService spiedLoginService;
    @Mock
    private LoginService loginService;


    @Mock
    private LoginDao loginDao;

    @Before
    public void before() throws Exception {
        loginController = new LoginController();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: login(UserForm user)
     */
    @Test
    public void testLoginByNull() throws Exception {
        //String re = loginController.login(null);
        //Assert.assertEquals("ERROR", re, "When the user is null, login would return ERROR");
        //Mockito.verifyZeroInteractions(loginService);
    }

    @Test
    public void assertTwoMethodsHaveBeenCalled() {
        UserForm userForm = new UserForm();
        userForm.setUsername("foo");
        Mockito.when(loginService.login(userForm)).thenReturn(true);

        String login = loginController.login(userForm);
        Assert.assertEquals("OK", login);

        Mockito.verify(loginService).login(userForm);
        Mockito.verify(loginService).setCurrentUser("foo");
    }

    @Test
    public void assertOnlyOneMethodHasBeenCalled() {
        UserForm userForm = new UserForm();
        userForm.username = "foo";
        Mockito.when(loginService.login(userForm)).thenReturn(false);

        String login = loginController.login(userForm);
        Assert.assertEquals("Fail", login);

        Mockito.verify(loginService).login(userForm);
        Mockito.verifyNoMoreInteractions(loginService);
    }

    @Test
    public void mockExceptionThrowin() {
        UserForm userForm = new UserForm();
        Mockito.when(loginService.login(userForm)).thenThrow(IllegalArgumentException.class);

        String login = loginController.login(userForm);
        Assert.assertEquals("ERROR EXCEPTION", login);

        Mockito.verify(loginService).login(userForm);
        Mockito.verifyZeroInteractions(loginService);
    }

    @Test
    public void mockAnObjectToPassAround() {
        UserForm userForm = Mockito.when(Mockito.mock(UserForm.class).getUsername())
                .thenReturn("foo").getMock();
        Mockito.when(loginService.login(userForm)).thenReturn(true);

        String login = loginController.login(userForm);
        Assert.assertEquals("OK", login);

        Mockito.verify(loginService).login(userForm);
        Mockito.verify(loginService).setCurrentUser("foo");
    }

    @Test
    public void argumentMatching() {
        UserForm userForm = new UserForm();
        userForm.username = "foo";
        // default matcher
        Mockito.when(loginService.login(Mockito.any(UserForm.class))).thenReturn(true);

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        Mockito.verify(loginService).login(userForm);
        // complex matcher
        Mockito.verify(loginService).setCurrentUser(ArgumentMatchers.argThat(
                new ArgumentMatcher<String>() {
                    @Override
                    public boolean matches(String argument) {
                        return argument.startsWith("foo");
                    }
                }
        ));
    }

    @Test
    public void partialMocking() {
        // use partial mock
        loginController.setLoginService(spiedLoginService);
        UserForm userForm = new UserForm();
        userForm.username = "foo";
        // let service's login use implementation so let's mock DAO call
        Mockito.when(loginDao.login(userForm)).thenReturn(1);

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        // verify mocked call
        Mockito.verify(spiedLoginService).setCurrentUser("foo");
    }

    @Test
    public void partialMockService(){
        loginController.setLoginService(spiedLoginService);

        UserForm userForm = new UserForm();
        userForm.username = "foo";

        Mockito.doReturn(true).when(spiedLoginService).login(userForm);
        //Mockito.doNothing().when(spiedLoginService).setCurrentUser(userForm.getUsername());

        String login = loginController.login(userForm);
        Assert.assertEquals("OK", login);
    }


}

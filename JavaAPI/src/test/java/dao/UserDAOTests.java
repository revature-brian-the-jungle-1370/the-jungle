package dao;

import dev.com.thejungle.customexception.*;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTests {

    UserDAO userDAO = new UserDAO();

    // TEST FOR USER CREATION/REGISTRATION
    @Test
    void testCreateNewUser() {
        long date = 742892400000L;
        User newJungleUser = new User(0, "Test", "Tester", "avawoianv@.c0",
                "useravoinavoin", "passcode", "I like social media.", date,
                "imagesourcefile");
        User createdUser = userDAO.createNewUser(newJungleUser);
        Assert.assertEquals(createdUser.getFirstName(), "Test");
        userDAO.deleteUser(createdUser);
    }

    // SAD PATH TEST DUPLICATE USERNAME
    @Test
    void testDuplicateUsernameException() {
        try {
            long date = 742892400000L;
            User newJungleUser = new User(0, "Test", "Tester", "email123@emai",
                    "username", "passcode", "I like social media.", date,
                    "imagesourcefile");
            userDAO.createNewUser(newJungleUser);
        } catch (DuplicateUsername d) {
            Assert.assertEquals("This username is already taken", d.getMessage());
        }
    }


    // SAD PATH TEST DUPLICATE EMAIL
    @Test
    void testDuplicateEmailException() {
        try {
            long date = 742892400000L;
            User newJungleUser = new User(0, "Test", "Tester", "email",
                    "usernamehsrtn", "passcode", "I like social media.", date,
                    "imagesourcefile");
            userDAO.createNewUser(newJungleUser);
        } catch (DuplicateEmail e) {
            Assert.assertEquals("Email is already in use", e.getMessage());
        }
    }

    // TEST GET USER BY USERNAME
    @Test
    public void testSearchForUserAndExists(){
        ArrayList<User> actual = userDAO.searchForUser("test");
        Assert.assertTrue(actual.size() > 0);
    }

    @Test
    public void testSearchForUserAndNotExists(){
        ArrayList<User> actual = userDAO.searchForUser("bugalsmfoaisejfase");
        Assert.assertTrue(actual.size() == 0);
    }

    @Test
    void testSearchForUserSuccess() {
        String testUsername = "test";
        ArrayList<User> results = userDAO.searchForUser(testUsername);
        for(User u: results){
            Assert.assertTrue(u.getUsername().toLowerCase().contains(testUsername));
        }
    }

    @Test
    void testSearchForUserNoResults(){
        String testUsername = "testusernotfound";
        ArrayList<User> results = userDAO.searchForUser(testUsername);
        Assert.assertTrue(results.size()==0);
    }

    // TEST REQUEST LOGIN
    @Test
    public void testRequestLoginSuccess() {
        User user = userDAO.requestLogin("username", "newpasscode");
        Assert.assertTrue(user.getUsername().equals("username"));
    }

    @Test
    public void testRequestLoginSuccess2() {
        User user = userDAO.requestLogin("test", "createpost");
        Assert.assertTrue(user.getUsername().equals("test"));
    }

    @Test
    void testGetUserByIdSuccess() {
        User user = userDAO.getUserById(13);
        Assert.assertTrue(user.getUserId() == 13);
    }

    //  GET ALL USERS
    @Test
    void testGetAllUsers() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users)
            Assert.assertTrue(users.size() >= 1);
    }

    @Test
    void testGetAllUsersTwo() {
        List<User> users = userDAO.getAllUsers();
        for (User u : users)
            System.out.println(u);
        Assert.assertTrue(users.size() <= 200);
    }


    // GET GROUPS
    @Test
    void testGetGroups() {
        ArrayList<Integer> arrayList = userDAO.getGroups(10000);
        Assert.assertTrue(arrayList.size() >= 1);
    }

    @Test
    void testGetGroupsTwo() {
        ArrayList<Integer> arrayList = userDAO.getGroups(13);
        Assert.assertTrue(arrayList.size() >= 1);
    }


    //  ------------------------------  DAO Sad path -----------------------------------

    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input Exception")
    void testGetGroupsBad() {
        userDAO.getGroups(9000000);
    }


    @Test(expectedExceptions = UserNotFound.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testGetUserByIdFailNoUserFound() {
        userDAO.getUserById(9996784);
    }

    @Test(expectedExceptions = UsernameOrPasscodeException.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongPassword() {
        userDAO.requestLogin("username", "33");
    }

    @Test(expectedExceptions = UsernameOrPasscodeException.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongUsername() {
        userDAO.requestLogin("ttt", "passcode");
    }

    @Test(expectedExceptions = UsernameOrPasscodeException.class, expectedExceptionsMessageRegExp = "User Not Found")
    void testRequestLoginFailWrongUsernameAndPassword() {
        userDAO.requestLogin("wert", "asdghj");
    }
}

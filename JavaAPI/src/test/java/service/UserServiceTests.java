package service;

import dev.com.thejungle.customexception.*;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.entity.User;
import dev.com.thejungle.service.implementations.UserService;
import dev.com.thejungle.service.interfaces.UserServiceInt;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTests {

    public static UserDAO userDAO = new UserDAO();
    public static UserServiceInt userService = new UserService(userDAO);

    static User userProfile;
    static User userProfile2;
    static User returnedProfile;
    static User badUsername;
    static User duplicateUsername;
    static User usernameSpaces;
    static User passwordSpaces;
    static List<User> newList;
    static ArrayList<User> anotherList = new ArrayList<>();
    static User badPasscode;
    static User otherProfile;
    static User blankSpaces;
    static User duplicateEmailUser;

 public static void main(String[] args) {
     UserServiceTests obj = new UserServiceTests();
     obj.testSearchForUserByProperUsername();
    }

    @BeforeClass
    public void setup() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserService(userDAO);
        long date = 742892400000L;
        userProfile = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", date, "image");
        badPasscode = new User(0, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILove", "Wrong", "I enjoy the wrestling life", date, "image");
        userProfile2 = new User(1, "Razor", "Ramon", "iwrestleforaliving@gmail.com", "ILoveToWrestle", "MySimplePasscode", "I enjoy the wrestling life", date, "image");
        returnedProfile = new User(2, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", date, "image");
        otherProfile = new User(3, "Solomon", "Grundy", "solomon@gmail.com", "BornOnMonday", "Tuesday", "I have a poem", date, "image");
        badUsername = new User(0, "Solomon", "Grundy", "solomon@gmail.com", "IAmSolomonGrundy", "Tuesday", "I have a poem", date, "image");
        duplicateUsername = new User(0, "Dup", "Testing", "dup@email.com", "username", "password", "I like social media.", date, "imagesrc");
        usernameSpaces = new User(0, "User", "Testing", "space@email.com", "user name", "password", "I like social media.", date, "imagesrc");
        passwordSpaces = new User(0, "User", "Testing", "space2@email.com", "username", "password space", "I like social media.", date, "imagesrc");
        anotherList.add(userProfile);
        anotherList.add(userProfile2);
        blankSpaces = new User(0, "Test", "", "email@testemail.com", "", "", "Social media is fun.", date, "imagesrc");
        duplicateEmailUser = new User(0, "Dup", "Testing", "testingemail@gmail.com", "avoihoih", "password", "I like social media.", date, "imagesrc");

    }


//  ------------------------------------ MOCK TESTS ----------------------------------------


    // DUPLICATE USERNAME
    @Test(expectedExceptions = DuplicateUsername.class, expectedExceptionsMessageRegExp = "This username is already taken")
    public void cannotHaveDuplicateUsernameSuccess() {
        Mockito.when(userDAO.checkForDuplicateUsername(duplicateUsername.getUsername())).thenReturn(true);
        Mockito.when(userDAO.checkForDuplicateEmail(duplicateUsername.getEmail())).thenReturn(false);
        userService.verifyNoExistingUsernameAndEmail(duplicateUsername.getUsername(), duplicateUsername.getEmail());
    }

    // DUPLICATE EMAIL
    @Test(expectedExceptions = DuplicateEmail.class, expectedExceptionsMessageRegExp = "Email is already in use")
    public void cannotHaveDuplicateEmailSuccess() {
        Mockito.when(userDAO.checkForDuplicateUsername(duplicateEmailUser.getUsername())).thenReturn(false);
        Mockito.when(userDAO.checkForDuplicateEmail(duplicateEmailUser.getEmail())).thenReturn(true);
        userService.verifyNoExistingUsernameAndEmail(duplicateEmailUser.getUsername(), duplicateEmailUser.getEmail());
    }

    // SPACES IN USERNAME
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInUsernameSuccess() {
        Mockito.when(userDAO.createNewUser(usernameSpaces)).thenReturn(Mockito.any());
        userService.createNewUserService(usernameSpaces);
    }

    // SPACES IN PASSWORD
    @Test(expectedExceptions = UnallowedSpaces.class, expectedExceptionsMessageRegExp = "No spaces allowed in username or password")
    public void cannotHaveSpacesInPasswordSuccess() {
        Mockito.when(userDAO.createNewUser(passwordSpaces)).thenReturn(Mockito.any());
        userService.createNewUserService(passwordSpaces);
    }

    // BLANK INPUTS
    @Test(expectedExceptions = BlankInputs.class, expectedExceptionsMessageRegExp = "Please fill in the blanks")
    public void missingInputsForUserRegistrationSuccess() {
        Mockito.when(userDAO.createNewUser(blankSpaces)).thenReturn(Mockito.any());
        userService.createNewUserService(blankSpaces);
    }

    // Get GroupId
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "User Id needs to be positive and in range")
    public void getGroupMockito() {
        Mockito.when(userDAO.getGroups(-2)).thenReturn(Mockito.any());
        userService.getGroups(-2);
    }

    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "User Id needs to be positive and in range")
    public void getGroupNoIdMockito() {
        Mockito.when(userDAO.getGroups(0)).thenReturn(Mockito.any());
        userService.getGroups(0);
    }

    // Get All
    @Test
    public void getAllUsersMockito() {
        Mockito.when(userDAO.getAllUsers()).thenReturn(newList);
        List<User> result = userService.getAllUsersService();
        Assert.assertEquals(result, newList);
    }

    // REQUEST LOGIN
    @Test(expectedExceptions = NoValuePasscode.class, expectedExceptionsMessageRegExp = "You must enter username and password")
    public void loginServiceFailEmptyCredentials() {
        userService.loginService("", "");
    }

    @Test(expectedExceptions = TooManyCharacters.class, expectedExceptionsMessageRegExp = "You are exceeding your character limit")
    public void loginServiceFailLongCredentials() {
        userService.loginService("123456789012345678901234567890123455786829034586902348690324806823904608932406892309486098209234068809", "hello");
    }

    // GET USER BY ID
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: UserId Must Be A Non 0 Positive")
    public void getUserByIdServiceFailInvalidUserId() {
        userService.getUserByIdService(0);
    }


    // TEST SEARCH BY USER SERVICE
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: Empty Username")
    public void testSearchForUserServiceFailBlankUsername(){
        userService.searchForUserService("");
    }
    
    // TEST SEARCH BY USER SERVICE
    @Test(expectedExceptions = InvalidInputException.class, expectedExceptionsMessageRegExp = "Invalid Input: UserName Exceeds 50 Characters")
    public void testSearchForUserServiceFailLongUsername(){
        userService.searchForUserService("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa123");
    }

    @Test
    public void testSearchForUserByProperUsername(){
        Mockito.when(userDAO.searchForUser("BornOnMonday")).thenReturn(anotherList);
        ArrayList<User>users= userService.searchForUserService("BornOnMonday");
        Assert.assertTrue(users!=null && !users.isEmpty());
    }

}

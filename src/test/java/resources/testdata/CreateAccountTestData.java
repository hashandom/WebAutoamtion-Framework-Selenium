package resources.testdata;


import org.testng.annotations.DataProvider;

public class CreateAccountTestData {

    @DataProvider(name = "getEmailData")
    public static Object[][] getEmailData() {
        return new Object[][] {
                {"hashan", "invalid"},
                {"hashan@", "invalid"},
                {"gmail.com", "invalid"},

        };
    }

    @DataProvider(name = "getPasswordConfirmPasswordData")
    public static Object[][] getPasswordConfirmPasswordData() {
        return new Object[][] {
                {"aaaa", "aaaa", "invalid"},
                {"aaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaa", "invalid"},
                {"1111", "1111", "invalid"},
                {"1111111111111111", "1111111111111111", "invalid"},
                {"aaaaa1111111rrrrrrr", "aaaaa1111", "invalid"},
                {"hashan123ab", "hashan123ab", "valid"}
        };
    }

    @DataProvider(name = "getAllUserRegData")
    public static Object[][]getAllUserRegData() {
        return new Object[][] {
                {"dom12@gmail.com","97119@ABdss","97119@ABdss","valid"}
        };
    }

    @DataProvider(name = "getAllUserLoginData")
    public static Object[][]getAllUserLoginData() {
        return new Object[][] {
                {"sampathdh199@gmail.com","valid"},
                {"sampathdh","invalid"}
        };
    }

    @DataProvider(name = "getAllLocationData")
    public static Object[][] getAllLocationData() {
        return new Object[][]{
                {"anuradhapura"},

        };
    }


}


package Ultilities;

public class Login {
    private static String Name;
    private static String Password;
    private static String Quyen;

    public static String getPassword() {
        return Password;
    }

    public static String getQuyen() {
        return Quyen;
    }

    public static String getUserName() {
        return Name;
    }

    public static void setPassword(String Pass) {
        Password = Pass;
    }

    public static void setQuyen(String strQuyen) {
        Quyen = strQuyen;
    }

    public static void setUserName(String strUserName) {
        Name = strUserName;
    }
}

package shrikant.com.quizapp.Config;

/**
 * Created by ShrikantIngole on 19-02-2018.
 */

public class WebConfig
{
    private String Registration="http://192.168.43.233:85/Quiz/Registration.php";

    public String getAllDescQue() {
        return AllDescQue;
    }

    private String AllDescQue="http://192.168.43.233:85/Quiz/GetAllDescQue.php";

    public String getAllMultiQue() {
        return AllMultiQue;
    }

    private String AllMultiQue="http://192.168.43.233:85/Quiz/GetAllMultiQue.php";

    public String getLogin() {
        return Login;
    }

    public String getAddMultique() {
        return AddMultique;
    }

    public String getAddDescQue() {
        return AddDescQue;
    }

    private String AddDescQue="http://192.168.43.233:85/Quiz/AddDescript.php";
    private String UpdateDescQue="http://192.168.43.233:85/Quiz/UpdateDescript.php";
    private String UpdateMultique="http://192.168.43.233:85/Quiz/UpdateMultichoice.php";

    public String getUpdateDescQue() {
        return UpdateDescQue;
    }

    public String getUpdateMultique() {
        return UpdateMultique;
    }

    private String AddMultique="http://192.168.43.233:85/Quiz/AddMultichoice.php";
    private String Login="http://192.168.43.233:85/Quiz/Login.php";

    public String getRegigtration() {
        return Registration;
    }

}

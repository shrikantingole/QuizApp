package shrikant.com.quizapp.Config;

/**
 * Created by ShrikantIngole on 19-02-2018.
 */

public class WebConfig
{
    private String Base="http://192.168.56.1:85/";
    private String Registration=Base+"Quiz/Registration.php";

    public String getAllDescQue() {
        return AllDescQue;
    }

    private String AllDescQue=Base+"Quiz/GetAllDescQue.php";

    public String getAllMultiQue() {
        return AllMultiQue;
    }

    private String AllMultiQue=Base+"Quiz/GetAllMultiQue.php";

    public String getLogin() {
        return Login;
    }

    public String getAddMultique() {
        return AddMultique;
    }

    public String getAddDescQue() {
        return AddDescQue;
    }

    private String AddDescQue=Base+"Quiz/AddDescript.php";
    private String UpdateDescQue=Base+"Quiz/UpdateDescript.php";
    private String UpdateMultique=Base+"Quiz/UpdateMultichoice.php";

    public String getUpdateDescQue() {
        return UpdateDescQue;
    }

    public String getUpdateMultique() {
        return UpdateMultique;
    }

    private String AddMultique=Base+"Quiz/AddMultichoice.php";
    private String Login=Base+"Quiz/Login.php";

    public String getRegigtration() {
        return Registration;
    }

}

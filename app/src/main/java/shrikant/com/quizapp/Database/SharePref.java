package shrikant.com.quizapp.Database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ShrikantIngole on 19-02-2018.
 */

public class SharePref
{
    SharedPreferences login;
    private Context c;

    public SharePref(Context c) {
        this.c = c;
        login = this.c.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
    }

    public void setLogin(String id, String fname, String lname, String mail, String add, String type)
    {
        SharedPreferences.Editor editor = login.edit();
        editor.putString("Id", id);
        editor.putString("Fname", fname);
        editor.putString("Lname", lname);
        editor.putString("Mail", mail);
        editor.putString("Address", add);
        editor.putString("Type", type);
        editor.apply();
    }

    public String getId() {
        if (login.contains("Id"))
            return String.valueOf(login.getString("Id", ""));
        else
            return "";
    }
    public String getFname()
    {
        if (login.contains("Fname"))
            return String.valueOf(login.getString("Fname", ""));
        else
            return "";
    }
  public String getLname()
    {
        if (login.contains("Lname"))
            return String.valueOf(login.getString("Lname", ""));
        else
            return "";
    }
  public String getType()
    {
        if (login.contains("Type"))
            return String.valueOf(login.getString("Type", ""));
        else
            return "";
    }
  public String getMail()
    {
        if (login.contains("Mail"))
            return String.valueOf(login.getString("Mail", ""));
        else
            return "";
    }
  public String getAddress()
    {
        if (login.contains("Address"))
            return String.valueOf(login.getString("Address", ""));
        else
            return "";
    }
}

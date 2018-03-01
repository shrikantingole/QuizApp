package shrikant.com.quizapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shrikant.com.quizapp.Admin.Dashboard;
import shrikant.com.quizapp.Admin.LoginPage;
import shrikant.com.quizapp.Database.SharePref;
import shrikant.com.quizapp.Student.StudentDahboard;

public class StartupPage extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_page);
        new Handler().postDelayed(new Runnable() {
            public void run()
            {
                if (new SharePref(getApplicationContext()).getId().isEmpty())
                {
                    startActivity(new Intent(getApplicationContext(), LoginPage.class));
                }else
                    {
                        if (new SharePref(getApplicationContext()).getType().equals("Admin"))
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        else
                            startActivity(new Intent(getApplicationContext(), StudentDahboard.class));
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();

            }
        }, 2000);


    }
}

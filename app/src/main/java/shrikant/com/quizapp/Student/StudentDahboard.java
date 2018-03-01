package shrikant.com.quizapp.Student;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import shrikant.com.quizapp.Admin.LoginPage;
import shrikant.com.quizapp.Admin.ViewDescList;
import shrikant.com.quizapp.Admin.ViewQuestions;
import shrikant.com.quizapp.Database.SharePref;
import shrikant.com.quizapp.R;

public class StudentDahboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dahboard);
        if (!(new SharePref(this).getType().equals("Student")))
        {
            startActivity(new Intent(this, LoginPage.class));
            finish();
        }

    }

    public void ViewQuestions(View view)
    {
       startActivity(new Intent(this, ViewQuestions.class));

    }

    public void Profile(View view)
    {
        startActivity(new Intent(this, Profile.class));
    }
}

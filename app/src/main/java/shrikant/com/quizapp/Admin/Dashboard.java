package shrikant.com.quizapp.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import shrikant.com.quizapp.R;
import shrikant.com.quizapp.Student.Profile;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);






    }
    public void Profile(View view)
    {
        startActivity(new Intent(this, Profile.class));
    }
    public void addMultipleChoice(View view)
    {
        startActivity(new Intent(this,MultichoiceQuestion.class));
    }

    public void addDescriptive(View view)
    {
        startActivity(new Intent(this,Descriptive_Question.class));
    }

    public void viewQuestions(View view)
    {
        startActivity(new Intent(this,ViewQuestions.class));
    }
}

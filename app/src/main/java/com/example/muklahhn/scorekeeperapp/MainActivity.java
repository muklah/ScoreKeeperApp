package com.example.muklahhn.scorekeeperapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int goalsTeamA = 0;
    int goalsTeamB = 0;

    TextView teamAGoals;
    TextView teamBGoals;

    EditText teamAET;
    EditText teamBET;
    Editable teamAEditable;
    Editable teamBEditable;

    String teamA;
    String teamB;

    String teams;
    String result;
    String thank_you;
    String vs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding the views by their id.
        teamAGoals = (TextView) findViewById(R.id.team_a_goals);
        teamBGoals = (TextView) findViewById(R.id.team_b_goals);
        teamAET = (EditText) findViewById(R.id.team_a);
        teamBET = (EditText) findViewById(R.id.team_b);

        teams = getString(R.string.teams);
        result = getString(R.string.result);
        thank_you = getString(R.string.thank_you);
        vs = getString(R.string.vs);
    }

    /**
     * This method reset the result for Team A and Team B to 0.
     */
    public void resetResult(View view) {
        goalsTeamA = 0;
        goalsTeamB = 0;
        displayGoalsTeamA(goalsTeamA);
        displayGoalsTeamB(goalsTeamB);
    }

    /**
     * This method increase the result for Team A by 1.
     */
    public void incrementOneTeamA(View view) {
        goalsTeamA = goalsTeamA + 1;
        displayGoalsTeamA(goalsTeamA);
    }

    /**
     * This method increase the result for Team B by 2.
     */
    public void incrementTwoTeamA(View view) {
        goalsTeamA = goalsTeamA + 2;
        displayGoalsTeamA(goalsTeamA);
    }

    /**
     * This method decrease the result for Team B by 1.
     */
    public void decrementOneTeamA(View view) {
        if (goalsTeamA == 0) {
            return;
        }
        goalsTeamA = goalsTeamA - 1;
        displayGoalsTeamA(goalsTeamA);
    }

    /**
     * This method increase the result for Team B by 1.
     */
    public void incrementOneTeamB(View view) {
        goalsTeamB = goalsTeamB + 1;
        displayGoalsTeamB(goalsTeamB);
    }

    /**
     * This method increase the result for Team B by 2.
     */
    public void incrementTwoTeamB(View view) {
        goalsTeamB = goalsTeamB + 2;
        displayGoalsTeamB(goalsTeamB);
    }

    /**
     * This method decrease the result for Team B by 1.
     */
    public void decrementOneTeamB(View view) {
        if (goalsTeamB == 0) {
            return;
        }
        goalsTeamB = goalsTeamB - 1;
        displayGoalsTeamB(goalsTeamB);
    }

    /**
     * This method share the result of match.
     */
    public void shareResult(View view) {
        teamAEditable = teamAET.getText();
        teamA = teamAEditable.toString();

        teamBEditable = teamBET.getText();
        teamB = teamBEditable.toString();

        String message = createMatchSummary(teamA, teamB);

        String teams = teamA + "\t" + vs + "\t" + teamB;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject, teams));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method prepare the result to be shared.
     */
    private String createMatchSummary(String teamA, String teamB) {
        String resultMessage = teams + "\t" + teamA + "\t" + vs + "\t" + teamB;
        resultMessage += "\n" + result + "\t" +  goalsTeamA + " - " + goalsTeamB;
        resultMessage += "\n\n" + thank_you;
        return resultMessage;
    }

    /**
     * This method displays the result for Team A.
     */
    private void displayGoalsTeamA(int goalsTeamA) {
        teamAGoals.setText("" + goalsTeamA);
    }

    /**
     * This method displays the result for Team B.
     */
    private void displayGoalsTeamB(int goalsTeamB) {
        teamBGoals.setText("" + goalsTeamB);
    }
}

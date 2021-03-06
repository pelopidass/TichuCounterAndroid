package com.newbiedev.panos.tichucounter;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Initiate FragmentManager. It's responsible for fragment initiation.
    FragmentManager fragmentManager = getFragmentManager();
    PlayFragment playFragment;
    MainFragment mainFragment;

    private int counterA, counterB;
    private int tempA = 0, tempB = 0;

    List<Integer> scoreTeamA = new ArrayList<Integer>();
    List<Integer> scoreTeamB = new ArrayList<Integer>();
    ArrayAdapter<Integer> adapterA, adapterB;

    ListView scoreListA;
    ListView scoreListB;

    CheckBox tichuMadeA;
    CheckBox tichuMadeB;
    CheckBox gTichuMadeA;
    CheckBox gTichuMadeB;
    CheckBox tichuLostA;
    CheckBox gTichuLostA;
    CheckBox tichuLostB;
    CheckBox gTichuLostB;
    CheckBox oneTwoA;
    CheckBox oneTwoB;

    EditText scoreA;
    EditText scoreB;

    RadioButton smallGame;
    RadioButton normalGame;

    TextView totalScoreA;
    TextView totalScoreB;
    TextView nameTeamA;
    TextView nameTeamB;

    EditText teamAEditText;
    EditText teamBEditText;

    ImageButton resetNameButton;
    ImageButton saveNamesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init components
        playFragment = (PlayFragment)fragmentManager.findFragmentById(R.id.play_fragment_container);
        mainFragment = (MainFragment) fragmentManager.findFragmentById(R.id.main_fragment_container);
        scoreListA = (ListView) findViewById(R.id.ScoreListA);
        scoreListB = (ListView) findViewById(R.id.ScoreListB);
        tichuMadeA  = (CheckBox) findViewById(R.id.tichuMadeA);
        tichuMadeB = (CheckBox) findViewById(R.id.tichuMadeB);
        gTichuMadeA = (CheckBox) findViewById(R.id.gTichuMadeA);
        gTichuMadeB = (CheckBox) findViewById(R.id.gTichuMadeB);
        tichuLostA = (CheckBox) findViewById(R.id.tichuLostA);
        gTichuLostA = (CheckBox) findViewById(R.id.gTichuLostA);
        tichuLostB = (CheckBox) findViewById(R.id.tichuLostB);
        gTichuLostB = (CheckBox) findViewById(R.id.gTichuLostB);
        oneTwoA = (CheckBox) findViewById(R.id.oneTwoA);
        oneTwoB = (CheckBox) findViewById(R.id.oneTwoB);
        scoreA = (EditText) findViewById(R.id.scoreAEditText);
        scoreB = (EditText) findViewById(R.id.scoreBEditText);
        smallGame = (RadioButton) findViewById(R.id.smallGame);
        normalGame = (RadioButton) findViewById(R.id.normalGame);
        totalScoreA = (TextView) findViewById(R.id.totalScoreA);
        totalScoreB = (TextView) findViewById(R.id.totalScoreB);
        nameTeamA = (TextView) findViewById(R.id.TextViewTeamA);
        nameTeamB = (TextView) findViewById(R.id.TextViewTeamB);
        teamAEditText = (EditText) findViewById(R.id.editTextTeamA);
        teamBEditText = (EditText) findViewById(R.id.editTextTeamB);
        resetNameButton = (ImageButton) findViewById(R.id.resetNamesButton);
        saveNamesButton = (ImageButton) findViewById(R.id.saveNameButton);

        nameTeamA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                nameTeamA.setVisibility(View.INVISIBLE);
                teamAEditText.setVisibility(View.VISIBLE);
                resetNameButton.setVisibility(View.INVISIBLE);
                saveNamesButton.setVisibility(View.VISIBLE);
                return true;
            }
        });
        nameTeamB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                nameTeamB.setVisibility(View.INVISIBLE);
                teamBEditText.setVisibility(View.VISIBLE);
                resetNameButton.setVisibility(View.INVISIBLE);
                saveNamesButton.setVisibility(View.VISIBLE);
                return true;
            }
        });

        //FragmentTransaction allow us to call methods for fragments.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Init our two fragments. The PlayFragment which contains the Checkboxes etc.
        //And the MainFragment that it's for the main screen of the app.

        //OnCreate we want to show the MainFragment and hide the PlayFragment.
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.hide(playFragment);

        //Committing our changes.
        fragmentTransaction.commit();

        adapterA = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, scoreTeamA);
        adapterB = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, scoreTeamB);


        scoreListA.setAdapter(adapterA);
        scoreListB.setAdapter(adapterB);

        deseselectCheckBoxes();
    }

    private void reInitGame() {


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Init our two fragments. The PlayFragment which contains the Checkboxes etc.
        //And the MainFragment that it's for the main screen of the app.


        //OnCreate we want to show the MainFragment and hide the PlayFragment.
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.hide(playFragment);

        //Committing our changes.
        fragmentTransaction.commit();
    }

    public void deseselectCheckBoxes() {


        tichuMadeA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeB.setChecked(false);
                tichuLostA.setChecked(false);
                gTichuMadeA.setChecked(false);
                gTichuLostA.setChecked(false);
                gTichuMadeB.setChecked(false);
                oneTwoB.setChecked(false);
            }
        });
        tichuMadeB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeA.setChecked(false);
                tichuLostB.setChecked(false);
                gTichuMadeB.setChecked(false);
                gTichuLostB.setChecked(false);
                oneTwoA.setChecked(false);
            }
        });
        tichuLostA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeA.setChecked(false);
                gTichuMadeA.setChecked(false);
                gTichuLostA.setChecked(false);
            }
        });
        gTichuMadeA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gTichuMadeB.setChecked(false);
                tichuMadeB.setChecked(false);
                tichuLostA.setChecked(false);
                tichuMadeA.setChecked(false);
                gTichuLostA.setChecked(false);
                oneTwoB.setChecked(false);
            }
        });
        gTichuLostA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeA.setChecked(false);
                gTichuMadeA.setChecked(false);
                tichuLostA.setChecked(false);
            }
        });
        tichuLostB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeB.setChecked(false);
                gTichuMadeB.setChecked(false);
                gTichuLostB.setChecked(false);
            }
        });
        gTichuMadeB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gTichuMadeA.setChecked(false);
                tichuMadeA.setChecked(false);
                tichuLostB.setChecked(false);
                gTichuLostB.setChecked(false);
                tichuMadeB.setChecked(false);
                oneTwoA.setChecked(false);
            }
        });
        gTichuLostB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeB.setChecked(false);
                gTichuMadeB.setChecked(false);
                tichuLostB.setChecked(false);
            }
        });
        oneTwoA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeB.setChecked(false);
                gTichuMadeB.setChecked(false);
                oneTwoB.setChecked(false);
            }
        });
        oneTwoB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tichuMadeA.setChecked(false);
                gTichuMadeA.setChecked(false);
                oneTwoA.setChecked(false);
            }
        });
    }

    private void teamACalculation(){
        if(tichuLostB.isChecked())
            tempB -= 100;
        else if (gTichuLostB.isChecked())
            tempB -= 200;
        if (tichuMadeA.isChecked())
            tempA += 100;
        else if (tichuLostA.isChecked())
            tempA -= 100;
        else if (gTichuMadeA.isChecked())
            tempA += 200;
        else if (gTichuLostA.isChecked())
            tempA -= 200;
    }

    private void teamBCalculation(){
        if (tichuLostA.isChecked())
            tempA -= 100;
        else if(gTichuLostA.isChecked())
            tempA -= 200;
        if (tichuMadeB.isChecked())
            tempB += 100;
        else if (tichuLostB.isChecked())
            tempB -= 100;
        else if (gTichuMadeB.isChecked())
            tempB += 200;
        else if (gTichuLostB.isChecked())
            tempB -= 200;
    }

    public void playButtonClick(View view) {

        if (scoreA.getText().toString().isEmpty() && scoreB.getText().toString().isEmpty()) {
            if (oneTwoA.isChecked() && !oneTwoB.isChecked()) {
                tempA = 200;
                tempB = 0;
                teamACalculation();
                scoreTeamA.add(0, tempA);
                scoreTeamB.add(0, tempB);
            } else if (!oneTwoA.isChecked() && oneTwoB.isChecked()) {
                tempB = 200;
                tempA = 0;
                teamBCalculation();
                scoreTeamA.add(0, tempA);
                scoreTeamB.add(0, tempB);
            }
        } else if (scoreB.getText().toString().isEmpty()) {
            int sA = Integer.parseInt(scoreA.getText().toString());
            if (sA % 5 == 0 && sA <= 125 && sA >= -25) {
                tempA = sA;
                tempB = 100 - tempA;
                if (tichuMadeA.isChecked()) {
                    tempA += 100;
                    if (tichuLostB.isChecked())
                        tempB -= 100;
                    else if (gTichuLostB.isChecked())
                        tempB -= 200;
                } else if (tichuLostA.isChecked()) {
                    tempA -= 100;
                    if (tichuMadeB.isChecked())
                        tempB += 100;
                    else if (gTichuMadeB.isChecked())
                        tempB += 200;
                    else if (tichuLostB.isChecked())
                        tempB -= 100;
                    else if (gTichuLostB.isChecked())
                        tempB -= 200;
                } else if (gTichuMadeA.isChecked()) {
                    tempA += 200;
                    if (tichuLostB.isChecked())
                        tempB -= 100;
                    else if (gTichuLostB.isChecked())
                        tempB -= 200;
                } else if (gTichuLostA.isChecked()) {
                    tempA -= 200;
                    if (tichuMadeB.isChecked())
                        tempB += 100;
                    else if (gTichuMadeB.isChecked())
                        tempB += 200;
                    else if (tichuLostB.isChecked())
                        tempB -= 100;
                    else if (gTichuLostB.isChecked())
                        tempB -= 200;
                }
                scoreTeamA.add(0, tempA);
                scoreTeamB.add(0, tempB);
            }
        } else if (scoreA.getText().toString().isEmpty()) {
            int sB = Integer.parseInt(scoreB.getText().toString());
            if (sB % 5 == 0 && sB <= 125 && sB >= -25) {
                tempB = sB;
                tempA = 100 - tempB;
                if (tichuMadeB.isChecked()) {
                    tempB += 100;
                    if (tichuLostA.isChecked())
                        tempA -= 100;
                    else if (gTichuLostA.isChecked())
                        tempA -= 200;
                } else if (tichuLostB.isChecked()) {
                    tempB -= 100;
                    if (tichuMadeA.isChecked())
                        tempA += 100;
                    else if (tichuLostA.isChecked())
                        tempA -= 100;
                    else if (gTichuMadeA.isChecked())
                        tempA += 200;
                    else if (gTichuLostA.isChecked())
                        tempA -= 200;
                } else if (gTichuMadeB.isChecked()) {
                    tempB += 200;
                    if (tichuLostA.isChecked())
                        tempA -= 100;
                    else if (gTichuLostA.isChecked())
                        tempA -= 200;
                } else if (gTichuLostB.isChecked()) {
                    tempB -= 200;
                    if (tichuMadeA.isChecked())
                        tempA += 100;
                    else if (tichuLostA.isChecked())
                        tempA -= 100;
                    else if (gTichuMadeA.isChecked())
                        tempA += 200;
                    else if (gTichuLostA.isChecked())
                        tempA -= 200;
                }
                scoreTeamA.add(0, tempA);
                scoreTeamB.add(0, tempB);
            }
        } else if (!scoreA.getText().toString().isEmpty() && !scoreB.getText().toString().isEmpty()) {
            int sA = Integer.parseInt(scoreA.getText().toString());
            int sB = Integer.parseInt(scoreB.getText().toString());
            if ((sA % 5 == 0 && sB % 5 == 0) && (sA >= -25 && sA <= 125) && (sB >= -25 && sB <= 125)) {
                if (sA == 100 - sB) {
                    tempA = sA;
                    tempB = sB;
                    if (tichuMadeB.isChecked()) {
                        tempB += 100;
                        if (tichuLostA.isChecked())
                            tempA -= 100;
                        else if (gTichuLostA.isChecked())
                            tempA -= 200;
                    } else if (tichuLostB.isChecked()) {
                        tempB -= 100;
                        if (tichuMadeA.isChecked())
                            tempA += 100;
                        else if (tichuLostA.isChecked())
                            tempA -= 100;
                        else if (gTichuMadeA.isChecked())
                            tempA += 200;
                        else if (gTichuLostA.isChecked())
                            tempA -= 200;
                    } else if (gTichuMadeB.isChecked()) {
                        tempB += 200;
                        if (tichuLostA.isChecked())
                            tempA -= 100;
                        else if (gTichuLostA.isChecked())
                            tempA -= 200;
                    } else if (gTichuLostB.isChecked()) {
                        tempB -= 200;
                        if (tichuMadeA.isChecked())
                            tempA += 100;
                        else if (tichuLostA.isChecked())
                            tempA -= 100;
                        else if (gTichuMadeA.isChecked())
                            tempA += 200;
                        else if (gTichuLostA.isChecked())
                            tempA -= 200;
                    }
                    scoreTeamA.add(0, tempA);
                    scoreTeamB.add(0, tempB);
                } else if (sB == 100 - sA) {
                    tempA = sA;
                    tempB = sB;
                    if (tichuMadeA.isChecked()) {
                        tempA += 100;
                        if (tichuLostB.isChecked())
                            tempB -= 100;
                        else if (gTichuLostB.isChecked())
                            tempB -= 200;
                    } else if (tichuLostA.isChecked()) {
                        tempA -= 100;
                        if (tichuMadeB.isChecked())
                            tempB += 100;
                        else if (gTichuMadeB.isChecked())
                            tempB += 200;
                        else if (tichuLostB.isChecked())
                            tempB -= 100;
                        else if (gTichuLostB.isChecked())
                            tempB -= 200;
                    } else if (gTichuMadeA.isChecked()) {
                        tempA += 200;
                        if (tichuLostB.isChecked())
                            tempB -= 100;
                        else if (gTichuLostB.isChecked())
                            tempB -= 200;
                    } else if (gTichuLostA.isChecked()) {
                        tempA -= 200;
                        if (tichuMadeB.isChecked())
                            tempB += 100;
                        else if (gTichuMadeB.isChecked())
                            tempB += 200;
                        else if (tichuLostB.isChecked())
                            tempB -= 100;
                        else if (gTichuLostB.isChecked())
                            tempB -= 200;
                    }
                    scoreTeamA.add(0, tempA);
                    scoreTeamB.add(0, tempB);
                }
            }
        }

        counterA += tempA;
        counterB += tempB;

        totalScoreA.setText(String.valueOf(counterA));
        totalScoreB.setText(String.valueOf(counterB));

        ClearFields();

        if (smallGame.isChecked()) {
            if (counterA >= 500 || counterB >= 500) {
                AlertDialog builder = new AlertDialog.Builder(MainActivity.this).create();
                builder.setMessage("Win!");
                if (counterA >= 500) {
                    builder.setTitle(nameTeamA.getText().toString() + " are damn good!");
                    builder.setMessage(nameTeamB.getText().toString() + " are LOSERS!");
                } else if (counterB >= 500) {
                    builder.setTitle(nameTeamB.getText().toString() + " are damn good!");
                    builder.setMessage(nameTeamA.getText().toString() + " are LOSERS!");
                }
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGamePreparation();
                        reInitGame();
                        dialog.dismiss();
                    }
                });
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        } else if (normalGame.isChecked()) {
            if (counterA >= 1000 || counterB >= 1000) {
                AlertDialog builder = new AlertDialog.Builder(MainActivity.this).create();
                builder.setMessage("Win!");
                if (counterA >= 1000) {
                    builder.setTitle(nameTeamA.getText().toString() + " are damn good!");
                    builder.setMessage(nameTeamB.getText().toString() + " are LOSERS!");
                } else if (counterB >= 1000) {
                    builder.setTitle(nameTeamB.getText().toString() + " are damn good!");
                    builder.setMessage(nameTeamA.getText().toString() + " are LOSERS!");
                }
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGamePreparation();
                        reInitGame();
                        dialog.dismiss();
                    }
                });
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        }

        adapterA.notifyDataSetChanged();
        adapterB.notifyDataSetChanged();
    }

    private void ClearFields() {
        tempA = 0;
        tempB = 0;

        scoreA.setText("");
        scoreB.setText("");

        tichuMadeA.setChecked(false);
        tichuLostA.setChecked(false);
        gTichuMadeA.setChecked(false);
        gTichuLostA.setChecked(false);

        tichuMadeB.setChecked(false);
        tichuLostB.setChecked(false);
        gTichuMadeB.setChecked(false);
        gTichuLostB.setChecked(false);

        oneTwoA.setChecked(false);
        oneTwoB.setChecked(false);
    }

    public void newGameButtonClick(View view) {

        if (smallGame.isChecked() || normalGame.isChecked()) {


                    /*Every time we want to make a FragmentTransaction in different methods
            we must initiate a new FragmentTransaction and and beginTransaction of FragmentManager that
            we already initiate.*/
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            PlayFragment playFragment = (PlayFragment) fragmentManager.findFragmentById(R.id.play_fragment_container);
            MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentById(R.id.main_fragment_container);

            //addToBackStack let us go back if we press the back button.
            //NULL because we don't have/need any parameter we going back.
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.show(playFragment);
            fragmentTransaction.hide(mainFragment);
            fragmentTransaction.commit();

        }
    }

    public void changeTeamANameButton(View view) {
        /*Change the visibility of the components to be right*/
        nameTeamA.setVisibility(View.INVISIBLE);
        teamAEditText.setVisibility(View.VISIBLE);
        resetNameButton.setVisibility(View.INVISIBLE);
        saveNamesButton.setVisibility(View.VISIBLE);
    }

    public void changeTeamBNameButton(View view) {
        nameTeamB.setVisibility(View.INVISIBLE);
        teamBEditText.setVisibility(View.VISIBLE);
        resetNameButton.setVisibility(View.INVISIBLE);
        saveNamesButton.setVisibility(View.VISIBLE);
    }

    public void saveNamesButtonClick(View view) {
        //set the visibility to be visible
        nameTeamA.setVisibility(View.VISIBLE);
        teamAEditText.setVisibility(View.INVISIBLE);
        nameTeamB.setVisibility(View.VISIBLE);
        teamBEditText.setVisibility(View.INVISIBLE);

        //Change the TeamATextView text with whatever the teamAEditText contains
        //_teamATextView.setText(teamAEditText.getText());
        //_teamBTextView.setText(teamBEditText.getText());

        if (teamAEditText.getText().toString().isEmpty()) {
            nameTeamA.setText("Team A");
        } else {
            nameTeamA.setText(teamAEditText.getText());
        }
        if (teamBEditText.getText().toString().isEmpty()) {
            nameTeamB.setText("Team B");
        } else {
            nameTeamB.setText(teamBEditText.getText());
        }

        //And then make the buttons invisible and visible
        resetNameButton.setVisibility(View.VISIBLE);
        saveNamesButton.setVisibility(View.INVISIBLE);
    }

    public void resetNamesButton(View view) {

        AlertDialog builder = new AlertDialog.Builder(MainActivity.this).create();
        builder.setTitle("Reset names");
        builder.setMessage("Are you sure you want to reset team names?");
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                nameTeamA.setText("Team A");
                nameTeamB.setText("Team B");
                teamAEditText.setText("");
                teamBEditText.setText("");
                dialog.dismiss();
            }
        });
        builder.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void newGamePreparation() {
        ClearFields();
        scoreTeamA.clear();
        scoreTeamB.clear();
        totalScoreA.setText("");
        totalScoreB.setText("");
        counterA = 0;
        counterB = 0;
    }

    private void undoButtonClick() {

        if (scoreTeamA.isEmpty() || scoreTeamB.isEmpty()) {
            AlertDialog builder = new AlertDialog.Builder(MainActivity.this).create();
            builder.setTitle("Undo Failed.");
            builder.setMessage("There is nothing to undo.");
            builder.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else if (scoreTeamA.size() == 1 || scoreTeamB.size() == 1) {
            counterA = 0;
            counterB = 0;
            scoreTeamA.clear();
            scoreTeamB.clear();

            totalScoreA.setText("");
            totalScoreB.setText("");
        } else {
            counterA -= scoreTeamA.get(scoreTeamA.size() - 1);
            counterB -= scoreTeamB.get(scoreTeamB.size() - 1);

            scoreTeamA.remove(scoreTeamA.size() - 1);
            scoreTeamB.remove(scoreTeamB.size() - 1);

            totalScoreA.setText(String.valueOf(counterA));
            totalScoreB.setText(String.valueOf(counterB));
        }

        adapterA.notifyDataSetChanged();
        adapterB.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newGameButton:
                //something
                AlertDialog builder = new AlertDialog.Builder(MainActivity.this).create();
                builder.setTitle("New Game?");
                builder.setMessage("Are you sure you want to start a new game?");
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reInitGame();
                        newGamePreparation();
                        dialog.dismiss();
                    }
                });
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            case R.id.undoButton:
                //something
                undoButtonClick();
                return true;
            case R.id.aboutButton:
                //Init an Intent and connect it with AboutActivity
                Intent intent = new Intent(this, AboutActivity.class);
                //Start the Activity and then it'll get it from AboutActivity.java
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

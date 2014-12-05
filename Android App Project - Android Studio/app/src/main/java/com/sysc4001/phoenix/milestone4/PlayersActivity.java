package com.sysc4001.phoenix.milestone4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.sysc4001.phoenix.milestone4.Games.Othello;
import com.sysc4001.phoenix.milestone4.PlayerTypes.RandomPlayerType;
import com.sysc4001.phoenix.milestone4.PlayerTypes.TouchPlayerType;
import com.sysc4001.phoenix.milestone4.PlayerTypes.PlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Alternative.ObstructPlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Alternative.OthelloObstruct;
import com.sysc4001.phoenix.milestone4.Strategies.Minimax.MinimaxPlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Minimax.OthelloMinimax;


public class PlayersActivity extends Activity {
    private PlayerType currentPlayer;
    private Othello runningGame;
    private Intent othelloView;
    private int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        othelloView = new Intent(this, OthelloActivity.class);
        number = 1;
        runningGame = new Othello();
        currentPlayer = new RandomPlayerType(runningGame.getBoard(), number);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.clearCheck();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                Toast.makeText(PlayersActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                if (rb.getText() instanceof String)
                    if (((String) rb.getText()).contains("Human"))
                        currentPlayer = new TouchPlayerType(runningGame.getBoard(), number);
                    else if (((String) rb.getText()).contains("Random"))
                        currentPlayer = new RandomPlayerType(runningGame.getBoard(), number);
                    else if (((String) rb.getText()).contains("Minimax"))
                        currentPlayer = new OthelloMinimax(new MinimaxPlayerType(runningGame.getBoard(), number));
                    else if (((String) rb.getText()).contains("Obstruction"))
                        currentPlayer = new OthelloObstruct(new ObstructPlayerType(runningGame.getBoard(), number));
            }
        };
        RadioButton rb1 = (RadioButton) findViewById(R.id.rad_human);
        rb1.setOnClickListener(listener);

        RadioButton rb2 = (RadioButton) findViewById(R.id.rad_random);
        rb2.setOnClickListener(listener);

        RadioButton rb3 = (RadioButton) findViewById(R.id.rad_minimax);
        rb3.setOnClickListener(listener);

        RadioButton rb4 = (RadioButton) findViewById(R.id.rad_obstruct);
        rb4.setOnClickListener(listener);
    }

    public void onP1Submit(View v){
        final EditText editText1 = (EditText)findViewById(R.id.playername1);

        String plName = editText1.getText().toString();
        currentPlayer.setName(plName);
        if (number == 1) {
            runningGame.setPlayer1Info(currentPlayer);
            editText1.setText("Player 2");
            this.setTitle("Second Player");
            number++;
        }else if (number == 2) {
            runningGame.setPlayer2Info(currentPlayer);
            othelloView.putExtra("OthelloGame", runningGame);
            startActivity(othelloView);
            //number = 1;
            //editText1.setText("Player 1");
            //this.setTitle("First Player");
            finishActivity(0);
        }
    }

}

package com.sysc4001.phoenix.milestone4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sysc4001.phoenix.milestone4.Boards.OthelloBoard;
import com.sysc4001.phoenix.milestone4.Games.Othello;
import com.sysc4001.phoenix.milestone4.PlayerTypes.PlayerType;
import com.sysc4001.phoenix.milestone4.PlayerTypes.TouchPlayerType;
import java.io.Serializable;

public class OthelloActivity extends Activity implements Serializable {
    Othello runningGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othello);

        runningGame = (Othello)getIntent().getSerializableExtra("OthelloGame");
        PlayerType [] players = runningGame.getPlayers();

        if(players[0] instanceof TouchPlayerType)
            ((TouchPlayerType)players[0]).setActivity(this);

        if(players[1] instanceof TouchPlayerType)
            ((TouchPlayerType)players[1]).setActivity(this);


        ( (OthelloBoard)runningGame.getBoard() ).printBoard(this, runningGame.getPlayers()[0].getName(),runningGame.getPlayers()[1].getName());
        play();
    }
    public void onClickSpace(View v){
        ImageView currentSpace = (ImageView)v;
        TextView editText1 = (TextView)findViewById(R.id.current_player);
        String strID = (String)currentSpace.getContentDescription();
        int button_num = 0;
        try {
            button_num = Integer.parseInt(strID);
        }catch(NumberFormatException e){
            if (strID.contains("a"))
                button_num = 10;
            else if (strID.contains("b"))
                button_num = 11;
            else if (strID.contains("c"))
                button_num = 12;
            else if (strID.contains("d"))
                button_num = 13;
            else if (strID.contains("e"))
                button_num = 14;
            else if (strID.contains("f"))
                button_num = 15;
        }

        int row = button_num/4;
        int column = button_num;
        for(int i = 1; column>3;i++){
            column-=4;
        }
        editText1.setText("Row: "+row+" Column: "+column);

        switch( runningGame.getBoard().getCurrentPlayer() ){
            case PLAYER1:
                if (runningGame.getPlayers()[0] instanceof TouchPlayerType)
                    ((TouchPlayerType)runningGame.getPlayers()[0]).setMove(row,column);
                break;
            case PLAYER2:
                if (runningGame.getPlayers()[1] instanceof TouchPlayerType)
                    ((TouchPlayerType)runningGame.getPlayers()[1]).setMove(row,column);
                break;
        }

    }
    public void onClickReplay(View v){
        Intent i = new Intent(this, PlayersActivity.class);
        startActivity(i);
        finishActivity(0);
    }
    public void play(){
        runningGame.play(this, runningGame.getPlayers()[0].getName(),runningGame.getPlayers()[1].getName());
    }
}

package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    //Player representation:- 0 = X, 1 = 0
    boolean gameActive = true;
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    //State meanings :- 0 = X, 1 = 0, 2 = NULL

    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8}, //Horizontal wins
                            {0,3,6},{1,4,7},{2,5,8},  //Vertical wins
                            {0,4,8},{2,4,6}};         //Diagonal wins

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){ //if game isnt active then reset function
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) { //2 checks if the place is empty or not
            gameState[tappedImage] = activePlayer; //wherever u tap, it will mark the place
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
        }
        //Checking if any of the players have won
        for(int[] winningPosition: winPositions){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] !=2){
                //Finding out who won
                String winnerStr;
                gameActive = false;
                if(gameState[winningPosition [0]] == 0){
                    winnerStr = "X has won!";
                }
                else {
                    winnerStr = "O has won!";
                }
                //update the status bar for winner announcement
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.winner_popup);
                TextView status = dialog.findViewById(R.id.winnerdeclare);
                status.setText(winnerStr);

                Button btnOkay = dialog.findViewById(R.id.btnOkay);

                //OnClickListener for Button to close the popup
                btnOkay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        }
    }
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        Toast.makeText(this, "Successfully reset", Toast.LENGTH_SHORT).show();

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");

        TextView topText = findViewById(R.id.welcome);
        topText.setText("Game has Started!");
    }
}

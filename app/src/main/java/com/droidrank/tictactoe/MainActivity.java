package com.droidrank.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TicTacToe game;

    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);

        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game != null || restart.getText().toString().equals(getString(R.string.restart_button_text_in_middle_of_game))) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.app_name)
                            .setMessage(R.string.restart_message)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    startGame();
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, null).show();
                } else {
                    startGame();
                }
            }
        });
    }

    public void placeMark(View v) {
        if (game != null) {
            String position = v.getTag().toString();
            String[] pArray = position.split(",");
            int x = Integer.parseInt(pArray[0]);
            int y = Integer.parseInt(pArray[1]);
            game.placeMark(x, y);

            Button btn = (Button) v;
            btn.setText(game.getCurrentPlayerMark());
            // Did we have a winner?
            if (game.checkForWin()) {
                if (game.getCurrentPlayerMark().equals(getString(R.string.player_1_move))) {
                    result.setText(R.string.player_1_wins);
                } else {
                    result.setText(R.string.player_2_wins);
                }
            } else if (game.isBoardFull()) {
                result.setText(R.string.draw);
            } else {
                // No winner or draw, switch players to 'o'
                game.changePlayer();
            }
        }
    }

    public void startGame() {
        // Create game and initialize it.
        // First player will be 'O'
        game = new TicTacToe(getString(R.string.player_1_move).charAt(0), getString(R.string.player_2_move).charAt(0));
        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");
        result.setText("");
        restart.setText(R.string.restart_button_text_in_middle_of_game);
    }
}

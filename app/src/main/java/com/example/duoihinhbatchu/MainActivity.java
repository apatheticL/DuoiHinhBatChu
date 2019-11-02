package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvNumberPlay;
    private TextView tvNumberMoney;
    private LinearLayout linearLayout1, linearLayout2;
    private LinearLayout linear1, linear2;
    private List<Answer> answers;
    private List<Character> allChar;
    private List<Character> charButton;
    private int currentIndex;
    private ImageView imageView;
    private int currentIndexChildAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innit();
        innitButtonAnswer();
        fireButton();
    }

    private void innitButtonAnswer() {
        String currentAnswer = answers.get(currentIndex).getAnswer();
        if (currentAnswer.length() > 8) {
            for (int i = 0; i < 8; i++) {
                Button btn = inflateButton(linearLayout2);
                linearLayout2.addView(btn);
            }
            linearLayout2.setVisibility(View.VISIBLE);
            for (int i = 0; i < currentAnswer.length() - 8; i++) {
                Button btn = inflateButton(linearLayout1);
                linearLayout1.addView(btn);
            }
        } else {
            for (int i = 0; i < currentAnswer.length(); i++) {
                Button btn = inflateButton(linearLayout1);
                linearLayout1.addView(btn);
            }
            linearLayout2.setVisibility(View.GONE);
        }
    }

    private Button inflateButton(LinearLayout linearLayout1) {
        LayoutInflater inflater = LayoutInflater.from(this);
        return (Button) inflater.inflate(R.layout.layout_button, linearLayout1, false);
    }

    private void fireButton() {
        innitAllChar();
        charButton = new ArrayList<>();
        for (int i = 0; i < answers.get(currentIndex).getAnswer().length(); i++) {
            charButton.add(answers.get(currentIndex).getAnswer().charAt(i));
        }
        int number2 = 16 - charButton.size();
        Random random = new Random();
        for (int j = 0; j < number2; j++) {
            charButton.add(allChar.get(random.nextInt(allChar.size())));
        }
        Collections.shuffle(charButton);
        linear1 = findViewById(R.id.ll_bottom_1);

        for (int i = 0; i < linear1.getChildCount(); i++) {
            Button bt = (Button) linear1.getChildAt(i);
            bt.setText(charButton.get(i) + "");
            bt.setOnClickListener(this);
        }
        linear2 = findViewById(R.id.ll_bottom_2);
        for (int i = 0; i < linear2.getChildCount(); i++) {
            Button btn = (Button) linear2.getChildAt(i);
            btn.setText(charButton.get(i + 8) + "");
            btn.setOnClickListener(this);
        }
        imageView.setImageResource(answers.get(currentIndex).getIdImage());
    }

    private void innit() {
        tvNumberPlay = findViewById(R.id.tv_number_play);
        tvNumberMoney = findViewById(R.id.number_money);
        linearLayout1 = findViewById(R.id.ll_1);
        imageView = findViewById(R.id.im_sound);
        linearLayout2 = findViewById(R.id.ll_2);

        answers = new ArrayList<>();
        answers.add(new Answer("CANTHIEP", R.drawable.canthiep));
        answers.add(new Answer("BAOCAO", R.drawable.baocao));
        answers.add(new Answer("AOMUA", R.drawable.aomua));
        answers.add(new Answer("CHIEUTRE", R.drawable.chieutre));
        answers.add(new Answer("CATTUONG", R.drawable.cattuong));
        answers.add(new Answer("DANHLUA", R.drawable.danhlua));
        answers.add(new Answer("GIANGMAI", R.drawable.giangmai));
        answers.add(new Answer("DANONG", R.drawable.danong));
        answers.add(new Answer("GIANDIEP", R.drawable.giandiep));
        answers.add(new Answer("HOIDONG", R.drawable.hoidong));
        answers.add(new Answer("HONGTAM", R.drawable.hongtam));
        Collections.shuffle(answers);
    }

    private void innitAllChar() {
        allChar = new ArrayList<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            allChar.add(i);
        }
    }

    @Override
    public void onClick(View view) {
        view.setVisibility(View.INVISIBLE);
        if (answers.get(currentIndex).getAnswer().length() < 8) {
            ((Button) linearLayout1.getChildAt(currentIndexChildAnswer)).
                    setText(((Button) view).getText().toString());
            currentIndexChildAnswer++;
            if (currentIndexChildAnswer == answers.get(currentIndex).
                    getAnswer().length()) {
                boolean isTrue = true;
                for (int i = 0; i < currentIndexChildAnswer; i++) {
                    if (answers.get(currentIndex).getAnswer().charAt(i) !=
                            ((Button) linearLayout1.getChildAt(i)).getText().charAt(0)) {
                        isTrue = false;
                        break;
                    }
                }
                if (isTrue) {
                    nextTrue();
                }
            }
        } else {
            if (answers.get(currentIndex).getAnswer().length() < 8) {
                ((Button) linearLayout2.getChildAt(currentIndexChildAnswer))
                        .setText(((Button) view).getText().toString());
                currentIndexChildAnswer++;

            } else {
                ((Button) linearLayout1.getChildAt(currentIndexChildAnswer)).
                        setText(((Button) view).getText().toString());
                currentIndexChildAnswer++;
            }
            if (currentIndexChildAnswer == answers.get(currentIndex).
                    getAnswer().length()) {

                boolean isCorrect = true;
                for (int i = 0; i < currentIndexChildAnswer; i++) {
                    if (i < 8) {
                        if (answers.get(currentIndex).getAnswer().charAt(i)
                                != ((Button) linearLayout2.getChildAt(i)).getText().charAt(0)) {
                            isCorrect = false;
                            break;
                        } else {
                            if (answers.get(currentIndex).getAnswer().charAt(i)
                                    != ((Button) linearLayout1.getChildAt(i)).getText().charAt(0)) {
                                isCorrect = false;
                                break;
                            }
                        }
                    }
                }
                if (isCorrect) {
                    nextTrue();
                }
            }
        }
    }

    private void nextTrue() {
        currentIndexChildAnswer = 0;
        currentIndex++;
        imageView.setImageResource(answers.get(currentIndex).getIdImage());
        linearLayout1.removeAllViews();
        linearLayout2.removeAllViews();
        linear1 = findViewById(R.id.ll_bottom_1);
        linear2 = findViewById(R.id.ll_bottom_2);
        innitButtonAnswer();
        fireButton();
        for (int i = 0; i < linear1.getChildCount(); i++) {
            linear1.getChildAt(i).setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < linear2.getChildCount(); i++) {
            linear2.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
}

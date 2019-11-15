package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
    private boolean checkDialogShowYes =true;
    private boolean checkDialogShowNo=true;
    private boolean checkRes=true;
    private List<Character> allChar;
    private List<Character> charButton;
    private int currentIndex;
    private int score=0;
    private Button btnBack;
    private ImageView imageView;
    private List<Integer> idButtonClicks = new ArrayList<>();
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
            for (int i = 0; i < currentAnswer.length()-8; i++) {
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
        btnBack = findViewById(R.id.btn_back);
        linear1 = findViewById(R.id.ll_bottom_1);
        linear2 = findViewById(R.id.ll_bottom_2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkRes=false;
                cleadContentAnswer();
                hindButton();
            }
        });
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
        answers.add(new Answer("KHOAILANG", R.drawable.khoailang));
        answers.add(new Answer("LANCAN", R.drawable.lancan));
        answers.add(new Answer("NAMBANCAU", R.drawable.nambancau));
        answers.add(new Answer("THATTINH", R.drawable.thattinh));
        answers.add(new Answer("MASAT", R.drawable.masat));
        answers.add(new Answer("QUYHANG", R.drawable.quyhang));
        answers.add(new Answer("SONGSONG", R.drawable.songsong));
        answers.add(new Answer("VUAPHALUOI", R.drawable.vuaphaluoi));
        answers.add(new Answer("TOHOAI", R.drawable.tohoai));
        answers.add(new Answer("OTO", R.drawable.oto));
        answers.add(new Answer("TOTIEN", R.drawable.totien));
        answers.add(new Answer("TICHPHAN", R.drawable.tichphan));
        answers.add(new Answer("TRANHTHU", R.drawable.tranhthu));
        answers.add(new Answer("KIEMCHUYEN", R.drawable.kiemchuyen));
        answers.add(new Answer("XAPHONG", R.drawable.xaphong));
        answers.add(new Answer("XEDAPDIEN", R.drawable.xedapdien));
        Collections.shuffle(answers);
    }
    private void cleadContentAnswer(){
        for (int i = 0; i <linearLayout1.getChildCount() ; i++) {
            ((Button)linearLayout1.getChildAt(i)).setText("");
            currentIndexChildAnswer=0;
        }
        for (int i = 0; i <linearLayout2.getChildCount() ; i++) {
            ((Button)linearLayout2.getChildAt(i)).setText("");
            currentIndexChildAnswer=0;
        }
    }
    private void innitAllChar() {
        allChar = new ArrayList<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            allChar.add(i);
        }
    }

    @Override
    public void onClick(View view) {

        if(currentIndexChildAnswer<linearLayout1.getChildCount()||currentIndexChildAnswer<(linearLayout2.getChildCount()+linearLayout1.getChildCount())){
                view.setVisibility(View.INVISIBLE);
                idButtonClicks.add(view.getId());
        }
        if (answers.get(currentIndex).getAnswer().length() < 8&&currentIndexChildAnswer<linearLayout1.getChildCount()) {
            ((Button) linearLayout1.getChildAt(currentIndexChildAnswer)).setText(((Button) view).getText().toString());
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
                    return;
                }
                else {
                    hindDialog();
                }
            }

        } if(answers.get(currentIndex).getAnswer().length() >=8&&currentIndexChildAnswer<linearLayout1.getChildCount()+linearLayout2.getChildCount()) {
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
                    if (answers.get(currentIndex).getAnswer().charAt(i) != ((Button) linearLayout1.getChildAt(i)).getText().charAt(0)) {
                        isCorrect = false;
                        break;
                    }
                    if (linearLayout2.getChildAt(i) != null) {
                        if (answers.get(currentIndex).getAnswer().charAt(i) != ((Button) linearLayout2.getChildAt(i)).getText().charAt(i)) {
                            isCorrect = false;
                            break;
                        }
                    }
                }
                if (isCorrect) {
                    nextTrue();

                }
                else{
                    hindDialog();
                }

            }
        }
    }

    private void hindDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_noti);
        dialog.setTitle("Thông báo");
        TextView txtscore = (TextView)dialog.findViewById(R.id.score);
        Button btnYes = (Button)dialog.findViewById(R.id.btn_yes);
        Button btnNo = (Button)dialog.findViewById(R.id.btn_no);
        txtscore.setText(score+"");
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterDialog();
                checkDialogShowYes =false;
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDialogShowNo=false;
                afterDialog();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void afterDialog(){
        currentIndexChildAnswer = 0;
        if(checkDialogShowNo ==false){
            score=0;
            cleadContentAnswer();
            currentIndex=0;
        }

        tvNumberMoney.setText(score+"");
        if (currentIndex == answers.size()) {
            currentIndex = 0;
        }
        imageView.setImageResource(answers.get(currentIndex).getIdImage());
        linearLayout1.removeAllViews();
        linearLayout2.removeAllViews();

        innitButtonAnswer();
        fireButton();
        for (int i = 0; i < linear1.getChildCount(); i++) {
            linear1.getChildAt(i).setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < linear2.getChildCount(); i++) {
            linear2.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
    private void nextTrue() {
        currentIndexChildAnswer = 0;
        score +=30;
        currentIndex++;
        tvNumberMoney.setText(score+"");
        if (currentIndex == answers.size()) {
            currentIndex = 0;
        }
        imageView.setImageResource(answers.get(currentIndex).getIdImage());
        linearLayout1.removeAllViews();
        linearLayout2.removeAllViews();

        innitButtonAnswer();
        fireButton();
        for (int i = 0; i < linear1.getChildCount(); i++) {
            linear1.getChildAt(i).setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < linear2.getChildCount(); i++) {
            linear2.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
    private void hindButton(){
        for (int i = 0; i <linear1.getChildCount() ; i++) {
            if(checkId(linear1.getChildAt(i).getId())){
                (linear1.getChildAt(i)).setVisibility(View.VISIBLE);
            }

        }
        for (int i = 0; i <linear2.getChildCount() ; i++) {
            if(checkId(linear2.getChildAt(i).getId())){
                (linear2.getChildAt(i)).setVisibility(View.VISIBLE);
            }
        }
        idButtonClicks.clear();
    }
    private  boolean checkId(int id){
        for (int i = 0; i <idButtonClicks.size() ; i++) {
            if(id==idButtonClicks.get(i)){
                return true;
            }
        }
        return false;
    }
}

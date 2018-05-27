package com.myroili.test;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



/**
 * Created by abouelmahassineabdelghani on 5/19/18.
 */

public class AddNewWord extends AppCompatActivity {
    private EditText editText;
    private WordViewModel wordViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word_layout);
        editText = findViewById(R.id.edit_label);
        Button saveBtn = findViewById(R.id.button_save);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        final Intent intent = new Intent(this,MainActivity.class);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word();
                word.setWord(editText.getText().toString());
                wordViewModel.insert(word);
                startActivity(intent);
                finish();
            }
        });
    }
}

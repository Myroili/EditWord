package com.myroili.test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private AdapterWord adapterWord;
   private Intent intent;
   private ConstraintLayout layout;
   private RecyclerView recyclerView;
   private ItemTouchHelper itemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.emptyLayoutWord);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
         recyclerView = findViewById(R.id.wordRecycler);
        adapterWord = new AdapterWord();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterWord);
        intent = new Intent(this,AddNewWord.class);
        recyclerView.setLayoutManager(layoutManager);
        final WordViewModel wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getmAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                adapterWord.setmWords(words);
                getTheViewVisibiblity();
                itemTouchHelper = new ItemTouchHelper(removeData(words,wordViewModel));
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

private ItemTouchHelper.SimpleCallback removeData(final List<Word> mWords, final WordViewModel wordViewModel){
    return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView1, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe

            if (direction == ItemTouchHelper.LEFT) {    //if swipe left

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //alert for confirm to delete
                builder.setMessage("Are you sure to delete?");    //set message

                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Word word = mWords.get(position);
                        adapterWord.notifyItemRemoved(position);
                        wordViewModel.delete(word);


                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapterWord.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        adapterWord.notifyItemRangeChanged(position, adapterWord.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                    }
                }).show();  //show alert dialog
            }
        }
    };
}


    private void getTheViewVisibiblity(){
        if (!adapterWord.getmWords().isEmpty()){
            recyclerView.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        } else if(adapterWord.getmWords().isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }

    }




}

package com.myroili.test;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by abouelmahassineabdelghani on 5/19/18.
 */

public class AdapterWord extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public List<Word> getmWords() {
        return mWords;
    }

    private List<Word> mWords;
    private static final int EMPTY_VIEW = 10;


    public void setmWords(List<Word> mWords) {
        this.mWords = mWords;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_holder_word, parent, false);
        return new WordHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WordHolder) {
            Word word = mWords.get(position);
            ((WordHolder) holder).wordItemView.setText(word.getWord());
        }

    }
    @Override
    public int getItemCount() {
      if(mWords != null){
          return mWords.size();
      }else {
          return 0;
      }
    }



    class WordHolder  extends RecyclerView.ViewHolder{
        private final TextView wordItemView;
        WordHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }


}

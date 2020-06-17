package com.yendu.roomdatabasemvvmarch.Presentation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yendu.roomdatabasemvvmarch.Models.Word;
import com.yendu.roomdatabasemvvmarch.R;

import java.util.List;

public class WordAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Word> mWords;
    Context mContext;

    public WordAdapters(List<Word>words,Context context){

        this.mContext=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=viewType==0? LayoutInflater.from(mContext).inflate(R.layout.normal_text_view,parent,false):
                LayoutInflater.from(mContext).inflate(R.layout.bold_text_view,parent,false);


        return viewType==0? new WordAdapterViewHolder(view):new WordAdapterViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType()==0){
            ((WordAdapterViewHolder)holder).textView.setText(mWords.get(position).getWord());
        }else{
            ((WordAdapterViewHolder2)holder).textView.setText(mWords.get(position).getWord());
        }



    }

    @Override
    public int getItemCount() {
        return mWords!=null ? mWords.size():0;

    }

    public void setWords(List<Word> words){
        this.mWords=words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2==0 ? 0:1;
    }

    public static class WordAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public WordAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.normal_text);
        }
    }
    private static class WordAdapterViewHolder2 extends RecyclerView.ViewHolder{

        TextView textView;
        public WordAdapterViewHolder2(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.bold_text);
        }
    }
}

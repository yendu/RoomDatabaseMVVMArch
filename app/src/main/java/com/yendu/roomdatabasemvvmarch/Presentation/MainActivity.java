package com.yendu.roomdatabasemvvmarch.Presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.yendu.roomdatabasemvvmarch.Models.Word;
import com.yendu.roomdatabasemvvmarch.Presentation.Adapters.WordAdapter;
import com.yendu.roomdatabasemvvmarch.R;
import com.yendu.roomdatabasemvvmarch.ViewModels.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    WordViewModel wordViewModel;
    FloatingActionButton fabs;
    RecyclerView recyclerView;
    WordAdapter wordAdapter;
    public static final int code=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabs=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        wordAdapter=new WordAdapter(this);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new SwipeToDelete(wordAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        wordViewModel=new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {

                wordAdapter.setWords(words);

            }
        });
        fabs.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity.this,NewWordActivity.class);
            startActivityForResult(intent,code);
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==code){
            if(resultCode==RESULT_OK){

                Word word=new Word(data.getStringExtra("s"));
                wordViewModel.insert(word);
            }

        }
    }

    public void delete(Word word){
        wordViewModel.delete(word);
        showUndoSnackBar(word);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actions,menu);
        return true;
    }
    private void showUndoSnackBar(Word word){
        Snackbar snackbar=Snackbar.make(new CoordinatorLayout(this),"Undo",Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo",v -> undoDelete(word));
        snackbar.show();
    }
    private void undoDelete(Word word){
        wordViewModel.insert(word);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_action:
                AlertDialog alertDialog=new AlertDialog.Builder(this)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                wordViewModel.deleteAll();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setMessage("Delete all content").create();

                alertDialog.show();

                break;



        }
        return  true;
    }
}

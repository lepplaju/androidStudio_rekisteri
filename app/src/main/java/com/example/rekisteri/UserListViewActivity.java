package com.example.rekisteri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserListViewActivity extends AppCompatActivity {
    private UserStorage storage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_view);
        storage = UserStorage.getInstance();
        recyclerView = findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserListAdapter(getApplicationContext(),storage.getUsers()));
    }

    public void navigateToFrontpage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
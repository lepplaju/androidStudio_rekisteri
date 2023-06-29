package com.example.rekisteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHelper> {
    private Context context;
    private ArrayList<User> users;

    public UserListAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHelper(LayoutInflater.from(context).inflate(R.layout.single_user_template, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHelper holder, int position) {
        holder.fullName.setText(users.get(position).getFirstName() + " " + users.get(position).getLastName());
        holder.email.setText(users.get(position).getEmail());
        holder.major.setText(users.get(position).getDegreeProgram());
        holder.avatar.setImageResource(users.get(position).getImage());
        if (users.get(position).getTutkinnot() != null) {
            if(users.get(position).getTutkinnot().size()>0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("suoritetut tutkinnot:");
                for (String tutkinto : users.get(position).getTutkinnot()) {
                    stringBuilder.append("\n").append(tutkinto);
                }
                holder.tutkinnot.setText(stringBuilder.toString());
            }
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

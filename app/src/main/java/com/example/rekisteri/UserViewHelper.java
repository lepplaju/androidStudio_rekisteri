package com.example.rekisteri;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHelper extends RecyclerView.ViewHolder {
    ImageView avatar;
    TextView fullName, email, major, tutkinnot;


    public UserViewHelper(@NonNull View itemView) {
        super(itemView);
        fullName = itemView.findViewById(R.id.fullNametv);
        email = itemView.findViewById(R.id.sahkopostitv);
        major = itemView.findViewById(R.id.paaainetv);
        avatar = itemView.findViewById(R.id.icontv);
        tutkinnot = itemView.findViewById(R.id.tutkinnot);
    }
}

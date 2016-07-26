package com.challenge.buscape.buscapeuserslist.Users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.buscape.buscapeuserslist.Data.model.User;
import com.challenge.buscape.buscapeuserslist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eliete on 7/26/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    List<User> userList;
    MainActivity.UserTouchListener userTouchListener;
    Context context;
    String imageLink;

    public UserAdapter(List<User> list, MainActivity.UserTouchListener touchListener, String link) {
        userList = list;
        userTouchListener = touchListener;
        imageLink = link;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view, userTouchListener);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        User user = getItem(position);
        if (user != null){
            holder.emailTextView.setText(user.getEmail());
            holder.nameTextView.setText(user.getName());

            Picasso.with(context)
                    .load(imageLink)
                    .placeholder(R.drawable.default_placeholder)
                    .into(holder.personImageView);

        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public User getItem(int position){
        return userList.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.person_image)
        ImageView personImageView;
        @BindView(R.id.name_text)
        TextView nameTextView;
        @BindView(R.id.email_text)
        TextView emailTextView;
        @BindView(R.id.more_button)
        TextView moreButton;

        MainActivity.UserTouchListener userTouchListener;

        public ViewHolder(View view, MainActivity.UserTouchListener touchListener) {
            super(view);
            userTouchListener = touchListener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            User user = getItem(position);
            userTouchListener.onUserClick(user);
        }
    }
}

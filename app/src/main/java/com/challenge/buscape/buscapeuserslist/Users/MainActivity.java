package com.challenge.buscape.buscapeuserslist.Users;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.challenge.buscape.buscapeuserslist.Data.model.User;
import com.challenge.buscape.buscapeuserslist.R;
import com.challenge.buscape.buscapeuserslist.UsersDetail.DetailActivity;
import com.challenge.buscape.userslist.Data.webservice.Injection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_USER = "user";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MainContract.UserActionListener userActionListener;
    private UserAdapter adapter;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String imageLink = getImageLinkBasedOnResolutionScreen();
        adapter = new UserAdapter(userList, userTouchListener, imageLink);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if (MainActivity.hasConnection(this)) {
            userActionListener = new MainPresenter(this, Injection.provideUsersList());
            userActionListener.fetchUserList();
        }else {
            showSnackBar("NO CONNECTION");
        }
    }

    public String getImageLinkBasedOnResolutionScreen(){
        String link = "";
        int density= getResources().getDisplayMetrics().densityDpi;
        switch(density)
        {
            case DisplayMetrics.DENSITY_MEDIUM:
                link = "100/150";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                link = "150/225";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                link = "200/300";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                link = "300/450";
                break;
        }
        return getResources().getString(R.string.image_link, link);

    }

    public void showSnackBar(String message) {
        Snackbar.make(this.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.WHITE)
                .show();
    }

    public static boolean hasConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo.isConnected();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showUsersList(List<User> list) {
        userList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUserDetailActivity(User user) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_USER, user);
        startActivity(intent);
    }

    UserTouchListener userTouchListener = new UserTouchListener(){
        @Override
        public void onUserClick(User clickedUser) {
            userActionListener.openItemDetails(clickedUser);
        }
    };

    public interface UserTouchListener {
        void onUserClick(User clickedUser);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userActionListener.onDestroy();
    }
}

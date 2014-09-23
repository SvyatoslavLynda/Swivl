package com.sv.swivl.app.loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import com.sv.swivl.app.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class UsersLoader  extends AsyncTaskLoader<ArrayList<User>> {

    private Context context;
    private int listItem;

    public UsersLoader(Context context, Bundle args) {
        super(context);

        this.context = context;
    }

    @Override
    public ArrayList<User> loadInBackground() {
        if(!checkInternetConnection()) return new ArrayList<User>();

        try {
            return new UsersRequestQueryBuilder().getUsers(listItem);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public int getListItem() {
        return listItem;
    }

    public void setListItem(int listItem) {
        this.listItem = listItem;
    }

    private boolean checkInternetConnection() {

        ConnectivityManager con_manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected());
    }
}
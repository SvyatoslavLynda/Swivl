package com.sv.swivl.app.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import com.sv.swivl.app.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class UsersLoader  extends AsyncTaskLoader<ArrayList<User>> {

    public int getListItem() {
        return listItem;
    }

    public void setListItem(int listItem) {
        this.listItem = listItem;
    }

    private int listItem;

    public UsersLoader(Context context, Bundle args) {
        super(context);
    }

    @Override
    public ArrayList<User> loadInBackground() {
        UsersRequestQueryBuilder usersRequestQueryBuilder = new UsersRequestQueryBuilder();

        try {
            return usersRequestQueryBuilder.getUsers(listItem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
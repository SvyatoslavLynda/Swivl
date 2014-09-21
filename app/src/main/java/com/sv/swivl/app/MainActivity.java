package com.sv.swivl.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.sv.swivl.app.loader.UsersLoader;
import com.sv.swivl.app.model.User;
import com.sv.swivl.app.widget.UsersAdapter;
import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<ArrayList<User>>, OnRefreshListener {


    private ListView usersList;
    private UsersAdapter adapter;
    private static Loader<ArrayList<User>> loader;
    private PullToRefreshLayout mPullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.ptr_layout);
        ActionBarPullToRefresh.from(this).allChildrenArePullable().listener(this).setup(mPullToRefreshLayout);
        mPullToRefreshLayout.setRefreshing(true);
        usersList = (ListView) findViewById(R.id.usersList);
        getSupportLoaderManager().initLoader(1, null, this);

    }

    @Override
    public Loader<ArrayList<User>> onCreateLoader(int id, Bundle args) {
        loader = new UsersLoader(this, args);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<User>> loader, ArrayList<User> data) {
        if(adapter == null) {
            adapter = new UsersAdapter(MainActivity.this, data);
        } else {
            for(User u : data) {
                adapter.add(u);
            }
        }
            usersList.setAdapter(adapter);
        mPullToRefreshLayout.setRefreshComplete();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<User>> loader) {

    }

    @Override
    public void onRefreshStarted(View view) {

        Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_LONG).show();
        int usersIndex = adapter.getCount();
        ((UsersLoader) loader).setListItem(((UsersLoader) loader).getListItem()+usersIndex);
        loader.forceLoad();
    }
}

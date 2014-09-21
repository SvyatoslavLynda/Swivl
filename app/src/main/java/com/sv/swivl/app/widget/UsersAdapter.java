package com.sv.swivl.app.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.sv.swivl.app.R;
import com.sv.swivl.app.model.User;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {

    private final Activity context;
    static class ViewHolder {
        TextView login;
        TextView profileURL;
    }

    public UsersAdapter(Activity context, ArrayList<User> data) {
        super(context, R.layout.list_item, data);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.login = (TextView) rowView.findViewById(R.id.login);
            holder.profileURL = (TextView) rowView.findViewById(R.id.profileURL);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        User user = getItem(position);
        holder.login.setText(user.getLogin());

        holder.profileURL.setText(user.getProfileURL());

        return rowView;
    }
}

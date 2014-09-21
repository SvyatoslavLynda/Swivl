package com.sv.swivl.app.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.sv.swivl.app.R;
import com.sv.swivl.app.listener.AnimateFirstDisplayListener;
import com.sv.swivl.app.model.User;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {

    private final Activity context;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;

    static class ViewHolder {
        TextView login;
        TextView profileURL;
        ImageView avatarUrl;
    }

    public UsersAdapter(Activity context) {
        super(context, R.layout.list_item);
        this.context = context;
    }

    public UsersAdapter(Activity context, ArrayList<User> data) {
        super(context, R.layout.list_item, data);
        this.context = context;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.placeholder)
                .showImageForEmptyUri(R.drawable.no_img)
                .showImageOnFail(R.drawable.no_img)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        initImageLoader(context.getApplicationContext());
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
            holder.avatarUrl = (ImageView) rowView.findViewById(R.id.avatarURL);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        User user = getItem(position);
        holder.login.setText(user.getLogin());

        holder.profileURL.setText(user.getProfileURL());

        if(user.getAvatarURL() == null) {
            holder.avatarUrl.setImageResource(R.drawable.no_img);
        } else {
            ImageLoader.getInstance().displayImage(user.getAvatarURL(), holder.avatarUrl,  options,  animateFirstListener);
        }

        return rowView;
    }

    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }
}

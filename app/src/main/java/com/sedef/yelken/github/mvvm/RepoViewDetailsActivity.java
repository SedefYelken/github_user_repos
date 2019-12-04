package com.sedef.yelken.github.mvvm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

public class RepoViewDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_view_details_activity);
        String username = getIntent().getStringExtra("USER_NAME");
        String open_issue = getIntent().getStringExtra("OPEN_ISSUE");
        String avatarUrl = getIntent().getStringExtra("AVATAR_URL");
        ((TextView) findViewById(R.id.tv_user_name)).setText(username);
        ((TextView) findViewById(R.id.tv_repo_open_issues_value)).setText(open_issue);
        ((TextView) findViewById(R.id.tv_repo_stars_value)).setText("0");
        if (avatarUrl != null)
            new DownloadImageTask((ImageView) findViewById(R.id.imageViewUser))
                    .execute(avatarUrl);

    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

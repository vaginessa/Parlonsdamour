package com.tumblr.parlonsdamour.parlonsdamour;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;


import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.User;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Tumblr extends AsyncTask<String, Void, Boolean>
{
    User user;
    JumblrClient client;
    String a,b,c;
    int d,e;
    @Override
    protected void onPreExecute()
    {
        client = new JumblrClient(Sensitive.userId, Sensitive.userPasswd);
        client.setToken(Sensitive.oAuthUser, Sensitive.oAuthPasswd);
    }

    protected Boolean doInBackground(final String... args)
    {

        user = client.user();
        // Make the request
        a =  user.getName();
        b = user.getDefaultPostFormat();
        c = user.toString();
        d= user.getFollowingCount();
        e = user.getLikeCount();

        List<Blog> blogs = client.userFollowing();
        for (Blog blog : blogs) {
            Log.e("USER","1"+blog.getTitle());
        }

        PhotoPost post;
        try {
            File photoFile = new File(args[0]);
            post = client.newPost(client.user().getName(), PhotoPost.class);
            post.setData(photoFile);

            post.save();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success)
    {

    }
}
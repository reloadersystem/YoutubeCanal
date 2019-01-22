package com.example.sistem03user.youtubeapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistem03user.youtubeapi.Adapter.MyCustomAdapter;
import com.example.sistem03user.youtubeapi.Model.VideoDetails;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String API_KEY= "AIzaSyD9GZLfzFIdowg9dIJyb6jfgac3P6mRp1U";

    ArrayList<VideoDetails> videoDetailsArrayList;

    MyCustomAdapter myCustomAdapter;

    String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLlEW_8OcYYBQBg5gqGlKcb4Ztp1QvUG_t&key=AIzaSyD9GZLfzFIdowg9dIJyb6jfgac3P6mRp1U";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);

        videoDetailsArrayList=new ArrayList<>();


        myCustomAdapter=  new MyCustomAdapter(MainActivity.this, videoDetailsArrayList);

        displayVideos();


    }

    private void displayVideos() {

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try {
                    JSONObject jsonObject= new JSONObject((response));
                    JSONArray jsonArray= jsonObject.getJSONArray("items");


                   for(int i=0; i<jsonArray.length(); i++)
                   {
                       JSONObject jsonObject1= jsonArray.getJSONObject(i);
                      // JSONObject jsonVideoId=jsonObject1.getJSONObject("id");
                       JSONObject jsonObjectSnippet=jsonObject1.getJSONObject("snippet");

                      JSONObject jsonObjecturl= jsonObjectSnippet.getJSONObject("thumbmnails").getJSONObject("medium");
                       JSONObject jsonObjectDefault= jsonObjectSnippet.getJSONObject("resourceId").getJSONObject("videoId");


                      // JSONObject jsonObjectvideoid= jsonObjectDefault.getJSONObject("resourceId").getJSONObject("videoId");



                       String video_id= jsonObjectDefault.getString("videoId");

                       VideoDetails vd= new VideoDetails();
                       vd.setVideoId(video_id);
                       vd.setTitle(jsonObjectSnippet.getString("title"));
                       vd.setDescription(jsonObjectSnippet.getString("description"));
                       vd.setUrl(jsonObjecturl.getString("url"));

                       videoDetailsArrayList.add(vd);


                   }

                   listView.setAdapter(myCustomAdapter);
                   myCustomAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        );

        requestQueue.add(stringRequest);
    }

}

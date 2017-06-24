package com.example.android.newsapp;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Fast_Balls on 6/23/2017.
 */


public class NetworkUtils {
    final static String  NEWS_APP_URL =
            "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=70162993d91646eb8a647c0c4276186f";

    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";

    public static URL buildUrl(String newsSearchQuery) {
        Uri buildUri = Uri.parse(NEWS_APP_URL).buildUpon().appendQueryParameter(PARAM_QUERY, newsSearchQuery).appendQueryParameter(PARAM_SORT, sortBy).build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFrinHTTPUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}



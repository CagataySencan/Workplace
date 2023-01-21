package com.tdonuk.sepetim.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.InputStream;

public class AppUtil {
    public static void init() throws Exception {
        InputStream serviceAccountStream = new FileInputStream("firebase-auth.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        FirebaseApp.initializeApp(options);
    }
}

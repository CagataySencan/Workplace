package com.tdonuk.sepetim.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.tdonuk.util.io.IO;

import java.io.ByteArrayInputStream;

public class AppUtil {
    public static void init() throws Exception {
        // InputStream serviceAccountStream = new FileInputStream("firebase-auth.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(IO.decodeToBytes(System.getenv().get("FIREBASE_AUTH")))))
                .build();

        FirebaseApp.initializeApp(options);
    }
}

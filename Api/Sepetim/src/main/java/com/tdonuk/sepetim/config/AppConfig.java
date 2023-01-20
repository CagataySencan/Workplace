package com.tdonuk.sepetim.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.tdonuk.util.io.IO;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

@Configuration
public class AppConfig {
    @PostConstruct
    public void init() throws IOException {
        Map<String, String> environment = System.getenv();

        String firebaseAuth = environment.get(Variables.FIREBASE_AUTH);
        FirebaseOptions opts = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(IO.decodeToBytes(firebaseAuth))))
                .setConnectTimeout(10000)
                .build();

        FirebaseApp.initializeApp(opts);
    }
}

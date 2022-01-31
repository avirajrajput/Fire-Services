package com.manacher.phoneauthentication.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireAuthService {
    private FirebaseAuth firebaseAuth;

    public FireAuthService() {
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public String getUserId(){
        return firebaseAuth.getCurrentUser().getUid();
    }

    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

}

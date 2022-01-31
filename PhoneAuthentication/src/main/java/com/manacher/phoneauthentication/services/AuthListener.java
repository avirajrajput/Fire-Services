package com.manacher.phoneauthentication.services;

import com.google.firebase.auth.FirebaseAuth;

public interface AuthListener {

    void onSuccess(FirebaseAuth firebaseAuth);
    void onFailure();

}

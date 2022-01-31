package com.manacher.phoneauthentication.services;

import com.google.firebase.auth.FirebaseAuth;

public interface AuthListener {

    void onSuccess(String userId);
    void onFailure();

}

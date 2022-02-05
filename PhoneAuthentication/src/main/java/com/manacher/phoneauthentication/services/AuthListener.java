package com.manacher.phoneauthentication.services;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface AuthListener {

    void authSuccessFul(Task<AuthResult> task);
    void fireUserUpdated(String userId);
    void authFailure();

}

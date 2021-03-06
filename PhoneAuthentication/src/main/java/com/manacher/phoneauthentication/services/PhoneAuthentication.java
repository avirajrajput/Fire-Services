package com.manacher.phoneauthentication.services;

import android.app.Activity;
import android.net.Uri;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.manacher.fireauthservice.FireAuthService;

import java.util.concurrent.TimeUnit;

public class PhoneAuthentication {

    private FirebaseAuth firebaseAuth;
    public String verificationId;
    private Activity context;

    private AuthListener authListener;

    private FireAuthService fireAuthService;
    public PhoneAuthProvider.ForceResendingToken token;

    public PhoneAuthentication(Activity context){
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
        this.authListener = (AuthListener)context;
        this.fireAuthService = new FireAuthService();
    }

    public void verifyCode(String code){
        if(verificationId == null){
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            authListener.authSuccessFul(task);

                        } else {
                            authListener.authFailure();
                        }
                    }
                });
    }

    public void updateFireUser(String name, Uri imageUrl){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(imageUrl)
                .build();

        fireAuthService.getCurrentUser().updateProfile(profileUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        authListener.fireUserUpdated(firebaseAuth.getUid());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        authListener.authFailure();
                    }
                });
    }

    public void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(context) // Activity (for callback binding)
                        .setCallbacks(mCallbacks) // OnVerificationStateChangedCallbacks
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String codeSent, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(codeSent, forceResendingToken);
            verificationId = codeSent;
            token = forceResendingToken;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            authListener.authFailure();
        }
    };
}

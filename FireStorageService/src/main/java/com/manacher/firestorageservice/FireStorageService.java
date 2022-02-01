package com.manacher.firestorageservice;

import android.net.Uri;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FireStorageService {

    private FirebaseStorage storage;
    private StorageReference storageRef;

    public FireStorageService(){
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    public UploadTask uploadFile(String path, String fileName, byte[] bytes){
        StorageReference storageReference = this.storageRef.child(path+"/"+fileName);
        return storageReference.putBytes(bytes);
    }

    public UploadTask uploadFile(String path, String fileName, Uri uri){
        StorageReference storageReference = this.storageRef.child(path+"/"+fileName);
        return storageReference.putFile(uri);
    }

    public UploadTask uploadFile(String path, String fileName, InputStream stream){
        StorageReference storageReference = this.storageRef.child(path+"/"+fileName);
        return storageReference.putStream(stream);
    }

    public Task<byte[]> downloadFile(String path, String fileName){
        StorageReference storageReference = this.storageRef.child(path+"/"+fileName);
        final long ONE_MEGABYTE = 1024 * 1024;
        return storageReference.getBytes(ONE_MEGABYTE);
    }

    public FileDownloadTask downloadFile(String path, String fileName, String extention, String localFile) {
        StorageReference storageReference = this.storageRef.child(path + "/" + fileName);
        File file = null;
        try {
            file = File.createTempFile(localFile, extention);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return storageReference.getFile(file);
    }

    public Task<Uri> getDownloadUrl(String path, String fileName){
        StorageReference storageReference = this.storageRef.child(path+"/"+fileName);
        return storageReference.getDownloadUrl();
    }
    public StorageReference getStorageRef(String path, String fileName){
        return this.storageRef.child(path+"/"+fileName);
    }
}

package com.example.libr;

import android.app.ProgressDialog;

import android.content.Context;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Modul  {
    public StorageReference storageRef;
    ProgressDialog progressDialog;

    public void upload (Context context,EditText editText , Uri imageUri)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();

        String s = editText.getText().toString() ;

        storageRef = FirebaseStorage.getInstance().getReference("images/"+s);


        storageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(context,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(context,"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });

    }


}


package app.genex.com.junosty;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class SingInActivity extends AppCompatActivity {

    private EditText loginEmailfield;
    private EditText contraseñaloginfield;
    private Button buttoninisesion;
    private ProgressDialog progressDialog;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaserefrence;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);


        firebaseAuth = FirebaseAuth.getInstance();

        databaserefrence = FirebaseDatabase.getInstance().getReference().child("Users");
        databaserefrence.keepSynced(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressDrawable(new ColorDrawable(Color.RED));






        loginEmailfield = (EditText)findViewById(R.id.editTextsignemail);
        contraseñaloginfield = (EditText) findViewById(R.id.editTextsignpassword);

        buttoninisesion = (Button) findViewById(R.id.buttoninicias);

        buttoninisesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checklogin();
                createQR();
            }
        });

    }

    private void checklogin() {

        String email = loginEmailfield.getText().toString().trim();// + "@hotmail.com"
        final String password = contraseñaloginfield.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            progressDialog.setMessage("Iniciando sesión...");
            progressDialog.show();
//que pedo

//hice popo

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    progressDialog.dismiss();


                    checkuserExist();






                }
                    else{
                    progressDialog.dismiss();
                    Toast.makeText(SingInActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                }
            });



        }
    }

    private void checkuserExist() {

        final String user_id = firebaseAuth.getCurrentUser().getUid();

        databaserefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if(dataSnapshot.hasChild(user_id)){
                    /*
                    Intent intent = new Intent(SingInActivity.this, MainActivity.class);
                    startActivity(intent);

                    String text2Qr = loginEmailfield.getText().toString();
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        intent.putExtra("pic", bitmap);




                    } catch (WriterException e) {
                        e.printStackTrace();

                    }

*/





                }
                else {
                    Toast.makeText(SingInActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }

                }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    private  void createQR(){


    final Context context = this;
    String text2Qr = loginEmailfield.getText().toString();
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
        BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 200, 200);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("pic", bitmap);
        context.startActivity(intent);
    } catch (WriterException e) {
        e.printStackTrace();
    }
}
}

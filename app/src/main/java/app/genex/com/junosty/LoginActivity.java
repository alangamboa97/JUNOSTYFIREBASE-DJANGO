package app.genex.com.junosty;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
//import com.journeyapps.barcodescanner.BarcodeEncoder;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private Button buttonreg;
    private EditText editTextemail;
    private EditText editTextboleta;
    private EditText editTextcontraseña;
    private TextView textViewIniciar;
    private EditText editTextnombre;
    private Button buttoninisesion;

    private ProgressDialog progressdialog;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        final Context context = this;

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("Users");
        progressdialog = new ProgressDialog(this);


        editTextemail = (EditText) findViewById(R.id.editemail);
        editTextboleta = (EditText) findViewById(R.id.editBoleta);
        editTextcontraseña = (EditText) findViewById(R.id.editcontraseña);
        editTextnombre = (EditText) findViewById(R.id.editTextnombre);

        buttonreg = (Button) findViewById(R.id.buttonreg);
        //buttoninisesion = (Button) findViewById(R.id.buttoninisesion);
        /*
        buttoninisesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, SingInActivity.class);
                startActivity(intent);

            }
        });
        */



        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();


                String text2Qr = editTextboleta.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("pic", bitmap);
                    context.startActivity(intent);
                } catch (WriterException e) {
                    e.printStackTrace();

                }

            }

        });


    }




    private void startRegister() {


        String email = editTextemail.getText().toString().trim() + "@hotmail.com";
        final String boleta = editTextboleta.getText().toString().trim();
        final String nombre = editTextnombre.getText().toString().trim();
        String contraseña = editTextcontraseña.getText().toString().trim();


        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(boleta) && !TextUtils.isEmpty(contraseña) && !TextUtils.isEmpty(nombre)) {

            progressdialog.setMessage("Registrando...");
            progressdialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        String user_id = firebaseAuth.getCurrentUser().getUid();

                        DatabaseReference currend_user_bd = database.child(user_id);

                        currend_user_bd.child("boleta").setValue(boleta);
                        currend_user_bd.child("Nombre").setValue(nombre);

                        progressdialog.dismiss();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);



                        // crearQR();




                    }


                }
            });


        }
    }


    private void crearQR() {
        final Context context = this;
        String text2Qr = editTextboleta.getText().toString();
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











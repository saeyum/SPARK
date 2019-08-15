package com.example.sahilbansal.online_open_house;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class register extends AppCompatActivity {
    private EditText email, pass;
    private Button b;
    private TextView fp;
 //   private Toolbar toolbar;
    private DatabaseReference myRef;
    ProgressDialog progressBar;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseAuth firebaseAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email= (EditText) findViewById(R.id.editText);
        firebaseAuth = FirebaseAuth.getInstance();
        pass = (EditText) findViewById(R.id.editText2);
        progressBar = new ProgressDialog(this);
        fp = (TextView)findViewById(R.id.forgot);
        b = (Button)findViewById(R.id.button2);
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString().trim();
                if (em.isEmpty()) {
                    email.requestFocus();
                    Toast.makeText(register.this, "Please enter the email to change password", Toast.LENGTH_SHORT).show();
                } else if (!em.isEmpty()) {
                    firebaseAuth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(register.this, "Password Reset mail is send to your registered email", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString().trim();
                String pasword = pass.getText().toString().trim();
                progressBar.setTitle("Login into your account");
                progressBar.setMessage("Please Wait");
                progressBar.show();
                    firebaseAuth.signInWithEmailAndPassword(useremail, pasword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "successfull", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(register.this, Problems.class);
                                        finish();
                                        startActivity(intent);
                                        progressBar.dismiss();
                                    } else {
                                        Toast.makeText(register.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                        progressBar.dismiss();
                                    }
                                }
                            });
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(register.this , loginorregister.class);
        finish();
        startActivity(intent);
    }
}

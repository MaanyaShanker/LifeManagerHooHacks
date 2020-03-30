package traf7.shankermaanya.lifemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
//https://www.youtube.com/watch?v=V0ZrnL-i77Q
public class SignUp extends AppCompatActivity {
    EditText username, password;
    Button btn_register;
    FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        final EditText temp_first = findViewById(R.id.first_name);
        final EditText temp_last = findViewById(R.id.last_name);
        btn_register = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_password = password.getText().toString();
                String fname =  temp_first.getText().toString();
                String lname = temp_last.getText().toString();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_password) ||  TextUtils.isEmpty(fname) ||  TextUtils.isEmpty(lname)){
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length() < 6)
                    Toast.makeText(SignUp.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                else {
                    String name = fname + " " +lname;
                    register(txt_username, txt_password, name);
                }
            }
        });
    }
//    private void register(final String username, final String password, final String name){
//        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(!task.isSuccessful()){
//                    Toast.makeText(SignUp.this, "Sign Up unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    User temp_user = new User(username, name);
//                    myRef = database.getReference("" + username);
//                    myRef.setValue(temp_user);
//                    Intent intent = new Intent(SignUp.this, Login.class);
//                    intent.putExtra("name", name);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        }); }
private void register(final String username, final String password, final String name){
    auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(!task.isSuccessful()){
                Toast.makeText(SignUp.this, "Sign Up unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(SignUp.this, "Successfully Signed Up!", Toast.LENGTH_SHORT).show();
                FirebaseUser user = auth.getCurrentUser();
                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                user.updateProfile(userProfileChangeRequest);
                Intent intent = new Intent(SignUp.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }
    }); }
}

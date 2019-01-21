package lakercompany.adventure_war.Activnosty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.io.IOException;

import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.User.UserStatistic;
import lakercompany.adventure_war.Zaprosi.Registration;
import lakercompany.adventure_war.R;


public class InfoCreate extends AppCompatActivity {

    EditText login;
    EditText age;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_create);

        button = (Button) findViewById(R.id.button);
        login=(EditText) findViewById(R.id.login);
        age=(EditText) findViewById(R.id.age);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login.getText().toString().equals("") && !age.getText().toString().equals("")) {
                    VKSdk.login(InfoCreate.this);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                User user = new User();
                user.age = Integer.valueOf(String.valueOf(age.getText()));
                user.name = String.valueOf(login.getText());
                user.ID = Integer.parseInt(res.userId);

                new UserShmot().id = Integer.parseInt(res.userId);
                new UserStatistic().id = Integer.parseInt(res.userId);
                try {
                    new Registration();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(InfoCreate.this,MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

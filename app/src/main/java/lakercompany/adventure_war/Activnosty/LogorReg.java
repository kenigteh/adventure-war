package lakercompany.adventure_war.Activnosty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import lakercompany.adventure_war.R;
import lakercompany.adventure_war.Zaprosi.GetShmot;
import lakercompany.adventure_war.Zaprosi.GetStatistic;
import lakercompany.adventure_war.Zaprosi.GetUser;


public class LogorReg extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logorreg);
        b1=(Button) findViewById(R.id.reg);
        b2=(Button) findViewById(R.id.log);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogorReg.this,HeroChooser.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.login(LogorReg.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(final VKAccessToken res) {

                new GetUser(Integer.parseInt(res.userId),LogorReg.this);

                new GetStatistic(Integer.parseInt(res.userId),LogorReg.this);

                new GetShmot(Integer.parseInt(res.userId),LogorReg.this);

                Intent intent = new Intent(LogorReg.this, MainActivity.class);
                startActivity(intent);
                LogorReg.this.finish();

            }
            @Override
            public void onError(VKError error) {
                Log.w("MY","onCreate MyApp");
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

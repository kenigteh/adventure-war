package lakercompany.adventure_war.Activnosty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

import lakercompany.adventure_war.R;

public class FirstMenuClass extends AppCompatActivity {
    RelativeLayout rl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_menu);

        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        Log.i("MyLog",String.valueOf(Arrays.asList(fingerprints)));

       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                    },
                    0);
        }

        if(ActivityCompat.checkSelfPermission(FirstMenuClass.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(FirstMenuClass.this,LogorReg.class);
            startActivity(intent);
            FirstMenuClass.this.finish();
        }




        rl = (RelativeLayout) findViewById(R.id.window);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(FirstMenuClass.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(FirstMenuClass.this,LogorReg.class);
                    startActivity(intent);
                    FirstMenuClass.this.finish();
                }
                else{
                    Toast.makeText(FirstMenuClass.this,"У вас нет нужного разрешения, чтобы использовать приложение :(" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

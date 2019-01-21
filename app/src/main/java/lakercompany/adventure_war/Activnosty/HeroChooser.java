package lakercompany.adventure_war.Activnosty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import lakercompany.adventure_war.R;
import lakercompany.adventure_war.User.User;

public class HeroChooser extends AppCompatActivity {
    ImageView finn;
    ImageView jake;
    ImageView princes;

    TextView hero;
    TextView desription;

    Button button;

    int choose=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooser);

        finn = (ImageView) findViewById(R.id.finn);
        jake = (ImageView) findViewById(R.id.jake);
        princes = (ImageView) findViewById(R.id.princes);

        hero = (TextView) findViewById(R.id.hero);
        desription = (TextView) findViewById(R.id.description);

        button=(Button) findViewById(R.id.button);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == finn){
                    hero.setText(R.string.name_finn);
                    desription.setText(R.string.Finn_description);
                    choose=1;
                }
                if(view == jake){
                    hero.setText(R.string.name_jake);
                    desription.setText(R.string.Jake_description);
                    choose=2;
                }
                if(view == princes){
                    hero.setText(R.string.name_princes);
                    desription.setText(R.string.Princes_description);
                    choose=3;
                }
                if(view==button){
                    User user = new User();
                    user.pers = choose;

                    Intent intent = new Intent(HeroChooser.this,InfoCreate.class);
                    startActivity(intent);
                }
            }
        };

        finn.setOnClickListener(clickListener);
        jake.setOnClickListener(clickListener);
        princes.setOnClickListener(clickListener);
        button.setOnClickListener(clickListener);

    }
}

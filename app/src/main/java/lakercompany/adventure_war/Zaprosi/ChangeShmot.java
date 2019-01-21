package lakercompany.adventure_war.Zaprosi;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.WorkClass.Answer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Laker City on 19.05.2017.
 */

public class ChangeShmot {
    public ChangeShmot(final Context context) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces service = retrofit.create(APIServeces.class);

        UserShmot us = new UserShmot();

        service.changeShmot(us.id,
                us.swards.get(0).have,
                us.swards.get(1).have,
                us.swards.get(2).have,
                us.swards.get(3).have,
                us.swards.get(4).have,
                us.weapon.get(0).have,
                us.weapon.get(1).have,
                us.weapon.get(2).have,
                us.weapon.get(3).have,
                us.weapon.get(4).have,
                us.amulet.get(0).have,
                us.amulet.get(1).have,
                us.amulet.get(2).have,
                us.amulet.get(3).have,
                us.amulet.get(4).have).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null)
                    Log.i("MyLog","ChangeShmot = " + response.body().ansCreate);

            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Log.i("MyLog","ChangeShmot = " + t.getMessage());
            }
        });
    }
}

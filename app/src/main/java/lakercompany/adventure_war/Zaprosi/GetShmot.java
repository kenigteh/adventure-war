package lakercompany.adventure_war.Zaprosi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import lakercompany.adventure_war.WorkClass.Answer;
import lakercompany.adventure_war.User.UserShmot;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetShmot {
    Context context;
    UserShmot us = new UserShmot();
    public GetShmot(Context contex){
        this.context = contex;
        getSm();
    }
    public GetShmot(final int userId, final Context context){

        us.id= userId;
        this.context = context;
        getSm();
    }

    void getSm(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces serveces = retrofit.create(APIServeces.class);

        serveces.getShmot(String.valueOf(us.id)).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null) {

                    us.swards.get(0).have = response.body().s1;
                    us.swards.get(1).have = response.body().s2;
                    us.swards.get(2).have = response.body().s3;
                    us.swards.get(3).have = response.body().s4;
                    us.swards.get(4).have = response.body().s5;

                    us.weapon.get(0).have = response.body().w1;
                    us.weapon.get(1).have = response.body().w2;
                    us.weapon.get(2).have = response.body().w3;
                    us.weapon.get(3).have = response.body().w4;
                    us.weapon.get(4).have = response.body().w5;

                    us.amulet.get(0).have = response.body().a1;
                    us.amulet.get(1).have = response.body().a2;
                    us.amulet.get(2).have = response.body().a3;
                    us.amulet.get(3).have = response.body().a4;
                    us.amulet.get(4).have = response.body().a5;

                    Log.i("ShmotLog"," Good");

                }
                else
                    Toast.makeText(context,"Ошибка! Повторите попытку позднее",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Log.i("MyLog",t.getMessage());
            }
        });
    }
}

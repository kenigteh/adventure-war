package lakercompany.adventure_war.Zaprosi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import lakercompany.adventure_war.User.UserStatistic;
import lakercompany.adventure_war.WorkClass.Answer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetStatistic {
    public GetStatistic(final int userId, final Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces serveces = retrofit.create(APIServeces.class);

        serveces.getStats(String.valueOf(userId)).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null) {
                    Log.i("MyLog", String.valueOf(response.body().m2));

                    UserStatistic us = new UserStatistic();
                    us.id = userId;
                    us.m1 = response.body().m1;
                    us.m2 = response.body().m2;
                    us.m3 = response.body().m3;
                    us.m4 = response.body().m4;
                    us.m5 = response.body().m5;
                    us.m6 = response.body().m6;
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

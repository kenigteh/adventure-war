package lakercompany.adventure_war.Zaprosi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import lakercompany.adventure_war.WorkClass.Answer;
import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.User.UserStatistic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeInfo {
    public ChangeInfo(final Context context) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces service = retrofit.create(APIServeces.class);

        User user  = new User();
        service.changeUser(user.ID, user.score, user.gold).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null)
                     Log.i("MyLog","Change = " + response.body().ansCreate);

            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Log.i("MyLog","Change = " + t.getMessage());
                Toast.makeText(context,"Увы нам не удалось сохранить ваши данный, проверьте соединение с интернетом!",Toast.LENGTH_LONG).show();
            }
        });

        UserStatistic us = new UserStatistic();

        Log.i("MyInfo",String.valueOf(us.id)+ "  || " +
                String.valueOf(us.m1)+ "  || " +
                String.valueOf(us.m2)+ "  || " +
                String.valueOf(us.m3)+ "  || " +
                String.valueOf(us.m4)+ "  || " +
                String.valueOf(us.m5)+ "  || " +
                String.valueOf(us.m6));

        service.changeStats(us.id,us.m1,us.m2,us.m3,us.m4,us.m5,us.m6).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null)
                    Log.i("MyLog","ChangeStatistic = " + response.body().ansCreate);

            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Log.i("MyLog","ChangeStatistic = " + t.getMessage());
            }
        });
    }
}

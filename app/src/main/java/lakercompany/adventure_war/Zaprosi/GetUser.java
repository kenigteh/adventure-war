package lakercompany.adventure_war.Zaprosi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import lakercompany.adventure_war.WorkClass.Answer;
import lakercompany.adventure_war.User.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUser {
    public GetUser(final int userId, final Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces serveces = retrofit.create(APIServeces.class);

        serveces.getUser(String.valueOf(userId)).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null) {
                    Log.i("MyLog", response.body().name);

                    User user = new User();
                    user.ID = response.body().ID;
                    user.name = response.body().name;
                    user.age = response.body().age;
                    user.pers = response.body().pers;
                    user.gold = response.body().gold;
                    user.score = response.body().score;
                    user.lvlChek();
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

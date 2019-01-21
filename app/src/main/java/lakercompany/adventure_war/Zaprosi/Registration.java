package lakercompany.adventure_war.Zaprosi;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.WorkClass.Answer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration {
    public Registration() throws IOException {
        User user  = new User();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces service = retrofit.create(APIServeces.class);

        service.createUser(user.ID, user.name, user.age, user.pers, Locale.getDefault().getCountry()).enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.body()!=null)
                    Log.i("MyLog",response.body().ansCreate);
            }

            @Override
            public void onFailure(Call<Answer> call, Throwable t) {

            }
        });
    }
}

package lakercompany.adventure_war.Zaprosi;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lakercompany.adventure_war.WorkClass.Answer;
import lakercompany.adventure_war.R;
import lakercompany.adventure_war.User.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getRecords {
    public static List<Person> persons;
    public getRecords(){
    }

    Context context;

    public getRecords(final Context context) throws IOException {
        this.context = context;
    }

    public void changeRecords(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app-1490276640.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServeces service = retrofit.create(APIServeces.class);

        service.getRec("12345").enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {
                persons = new ArrayList<>();
                if(response.body()!=null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        switch (response.body().get(i).country) {
                            case "RU":
                                persons.add(new Person(response.body().get(i).name, response.body().get(i).score, R.drawable.ru));
                                break;
                            case "DE":
                                persons.add(new Person(response.body().get(i).name, response.body().get(i).score, R.drawable.de));
                                break;
                            case "US":
                                persons.add(new Person(response.body().get(i).name, response.body().get(i).score, R.drawable.us));
                                break;
                            default:
                                persons.add(new Person(response.body().get(i).name, response.body().get(i).score, R.drawable.ru));
                                break;
                        }
                    }

                    Collections.sort(persons, new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            return o2.score - o1.score;
                        }
                    });
                }
                else
                    changeRecords();

            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {
                Log.i("MyLog","Change = " + t.getMessage());
            }
        });
    }
}

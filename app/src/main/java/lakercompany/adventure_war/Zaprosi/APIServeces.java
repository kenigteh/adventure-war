package lakercompany.adventure_war.Zaprosi;

import java.util.List;

import lakercompany.adventure_war.WorkClass.Answer;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServeces {

    @FormUrlEncoded
    @POST("get_user.php")
    Call<Answer> getUser(@Field("ID") String ID);

    @FormUrlEncoded
    @POST("delete_user.php")
    Call<Answer> deleteUser(@Field("ID") String ID);

    @FormUrlEncoded
    @POST("change_user.php")
    Call<Answer> changeUser(@Field("ID") int ID,
                            @Field("score") int score,
                            @Field("gold") int gold);


    @FormUrlEncoded
    @POST("create_user.php")
    Call<Answer> createUser(@Field("ID") int ID,
                            @Field("name") String name,
                            @Field("age") int age,
                            @Field("pers") int pers,
                            @Field("country") String country);

    @FormUrlEncoded
    @POST("get_records.php")
    Call<List<Answer>> getRec(@Field("kye") String key);

    @FormUrlEncoded
    @POST("get_stats.php")
    Call<Answer> getStats(@Field("ID") String ID);

    @FormUrlEncoded
    @POST("get_shmot.php")
    Call<Answer> getShmot(@Field("ID") String ID);

    @FormUrlEncoded
    @POST("change_statistic.php")
    Call<Answer> changeStats(@Field("ID") int ID,
                             @Field("tm") int m1,
                             @Field("ice_king") int m2,
                             @Field("m3") int m3,
                             @Field("m4") int m4,
                             @Field("m5") int m5,
                             @Field("m6") int m6);

    @FormUrlEncoded
    @POST("change_shmot.php")
    Call<Answer> changeShmot(@Field("ID") int ID,
                            @Field("s1") int s1,
                            @Field("s2") int s2,
                            @Field("s3") int s3,
                            @Field("s4") int s4,
                            @Field("s5") int s5,
                            @Field("w1") int w1,
                            @Field("w2") int w2,
                            @Field("w3") int w3,
                            @Field("w4") int w4,
                            @Field("w5") int w5,
                            @Field("a1") int a1,
                            @Field("a2") int a2,
                            @Field("a3") int a3,
                            @Field("a4") int a4,
                            @Field("a5") int a5);
}

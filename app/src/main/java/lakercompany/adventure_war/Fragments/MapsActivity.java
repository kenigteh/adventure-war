package lakercompany.adventure_war.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.support.v4.app.Fragment;

import lakercompany.adventure_war.Activnosty.Fighting;
import lakercompany.adventure_war.R;
import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.User.UserStatistic;
import lakercompany.adventure_war.WorkClass.Stacks;
import lakercompany.adventure_war.WorkClass.myPosition;
import lakercompany.adventure_war.Zaprosi.getRecords;

public class MapsActivity extends Fragment implements OnMapReadyCallback {
    ArrayList<Stacks> stackList = new ArrayList<>();
    ArrayList<BitmapDescriptor> iconList = new ArrayList<>();
    int ppp = 0;
    public GoogleMap map = null;
    LatLng MyPos = new LatLng(0, 0);
    GroundOverlay iam;
    myPosition mPosition;

    boolean isRecord = false;

    User user = new User();

    RelativeLayout preload;

    TextView namename;
    TextView hp_txt;
    TextView energy_txt;
    TextView lvl_txt;
    TextView dmg_txt;

    public static final String TAG = "";

    ImageView imageView;
    ImageView rec_img;

    private RecordsFragment rf;
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;

    getRecords gR;

    View view;
    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.activity_maps, container, false);

        mPosition = new myPosition(inflater,getActivity());

        rf = new RecordsFragment();

        manager = getActivity().getSupportFragmentManager();

        //Подгружаю рекорды

        try {
            gR = new getRecords(inflater.getContext());
            gR.changeRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Запускаем картику для загрузки
        startLoad();

        //Создаем карту
        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Запускаю важный поток
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Проверяю обновилась ли карта и координаты
                while (map == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while (MyPos.latitude == 0 || MyPos.longitude == 0) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            MyPosUpdate();
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //Создаю опасных ребят и себя и меняю позицию себя и камеры
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        createHero();
                        createStack();
                        iconUpdate();
                        positionUpdute();
                    }
                });

                while (true) {
                    ppp++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            if (ppp == 30) {
                                gR.changeRecords();
                                ppp = 0;
                            }
                            MyPosUpdate();
                            iconUpdate();
                            changeStatistic();
                        }
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        imageView = (ImageView) view.findViewById(R.id.posBut);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionUpdute();
            }
        });

        rec_img = (ImageView) view.findViewById(R.id.records);
        rec_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecods();
            }
        });

        iconList.add(BitmapDescriptorFactory.fromResource(R.drawable.icon1));
        iconList.add(BitmapDescriptorFactory.fromResource(R.drawable.icon2));
        iconList.add(BitmapDescriptorFactory.fromResource(R.drawable.icon3));

        namename = (TextView) view.findViewById(R.id.current_character_name);
        dmg_txt = (TextView) view.findViewById(R.id.dmg);
        lvl_txt = (TextView) view.findViewById(R.id.lvl);
        hp_txt = (TextView) view.findViewById(R.id.hp);
        energy_txt = (TextView) view.findViewById(R.id.energy);

        //изменяю статы в панеле
        changeStatistic();

        return view;
    }

    private void startLoad() {
        preload = (RelativeLayout) view.findViewById(R.id.load);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        preload.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onMapReady(final GoogleMap mapp) {
        //Когда карта готова
        map = mapp;
        map.setMinZoomPreference(18);
        map.getUiSettings().setTiltGesturesEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);
    }

    private void startRecods() {
        fragmentTransaction = manager.beginTransaction();
        if (!isRecord){
            rf = new RecordsFragment();
            fragmentTransaction.add(R.id.container, rf, rf.TAG);
        }else{
            fragmentTransaction.remove(rf);
        }
        fragmentTransaction.commit();
        isRecord = !isRecord;
    }

    void createStack() {
        //Создаю случайных монстров
        for (int i = 0; i < 50; i++) {
            int rand = new Random().nextInt(6);
            if (rand == 0) {
                //Типичный Монстр
                createMonster(i, R.drawable.tm, 25f, 30f, 1);
            } else if (rand == 1) {
                //Гантер
                createMonster(i, R.drawable.gunter, 32f, 24f, 2);
            } else if (rand == 2) {
                //Снежный Король
                createMonster(i, R.drawable.ice_king, 32f, 24f, 3);
            } else if (rand == 3) {
                //Дерево
                createMonster(i, R.drawable.tree, 28f, 30f, 4);
            } else if (rand == 4) {
                //Вампир
                createMonster(i, R.drawable.vamper, 32f, 24f, 5);
            } else {
                //Лич
                createMonster(i, R.drawable.lich, 24f, 32f, 6);
            }
        }
        stackList.add(new Stacks(map.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.vamper))
                .position(new LatLng(
                                MyPos.latitude + 0.0005,
                                MyPos.longitude + 0.0002),
                        24f,
                        32f))
                , 5)
        );
        stackList.get(50).groundOverlay.setClickable(true);

        stackList.add(new Stacks(map.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.gunter))
                .position(new LatLng(
                                MyPos.latitude - 0.0005,
                                MyPos.longitude - 0.0002),
                        24f,
                        32f))
                , 2)
        );
        stackList.get(51).groundOverlay.setClickable(true);

        //Запускаю проверку
        stackTouch();
    }

    void createMonster(int i, int res, float wi, float hi, int type) {
        stackList.add(new Stacks(map.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(res))
                .position(new LatLng(
                                MyPos.latitude + getRandom(),
                                MyPos.longitude + getRandom()),
                        hi,
                        wi))
                , type)
        );
        stackList.get(i).groundOverlay.setClickable(true);
    }

    void createHero() {
        iam = map.addGroundOverlay(new GroundOverlayOptions()
                .image(getIcon())
                .position(MyPos, 20f, 20f));

        namename.setText(user.name);
    }

    BitmapDescriptor getIcon() {
        if (user.pers == 1)
            return iconList.get(0);
        else if (user.pers == 2)
            return iconList.get(1);
        else
            return iconList.get(2);
    }

    void MyPosUpdate() {
        if(mPosition.mLastLocation!=null)
            MyPos = new LatLng(mPosition.mLastLocation.getLatitude(),mPosition.mLastLocation.getLongitude());
        mPosition.displayLocation();
    }

    void iconUpdate() {
        iam.setPosition(MyPos);
    }

    private void positionUpdute() {
        //Обновление камеры
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(iam.getPosition(), 18));
    }

    double getRandom() {
        //Случайные числа для координат
        int nmin = -1000;
        int nmax = 1000;
        return (double) (nmin + (int) (Math.random() * ((nmax - nmin) + 1))) / 100000;
    }

    void stackTouch() {
        //Проверка клика по монстрам
        map.setOnGroundOverlayClickListener(new GoogleMap.OnGroundOverlayClickListener() {
            @Override
            public void onGroundOverlayClick(GroundOverlay groundOverlay) {
                int i = 0;
                while (!groundOverlay.equals(stackList.get(i).groundOverlay)) i++;
                if(distance(i)>0.001){
                    Toast.makeText(inflater.getContext(),"Он слишком далеко (",Toast.LENGTH_SHORT).show();
                }
                else {
                    newActivity(stackList.get(i).type);
                    deleteStack(i);
                }
            }
        });
    }

    void deleteStack(int i) {
        stackList.get(i).groundOverlay.remove();
        stackList.remove(i);
    }

    void newActivity(int u) {
        new UserStatistic().tm = u;
        Intent intent = new Intent(inflater.getContext(), Fighting.class);
        startActivity(intent);
    }

    double distance(int i) {
        return Math.sqrt(
                (MyPos.longitude - stackList.get(i).groundOverlay.getPosition().longitude) * (MyPos.longitude - stackList.get(i).groundOverlay.getPosition().longitude)
                        + (MyPos.latitude - stackList.get(i).groundOverlay.getPosition().latitude) * (MyPos.latitude - stackList.get(i).groundOverlay.getPosition().latitude)
        );
    }

    void changeStatistic() {
        namename.setText(user.name);
        dmg_txt.setText(String.valueOf(user.damage));
        hp_txt.setText(String.valueOf(user.hp));
        lvl_txt.setText(String.valueOf(user.lvl));
        energy_txt.setText(String.valueOf(user.gold));
    }

}
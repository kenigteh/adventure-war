package lakercompany.adventure_war.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.Zaprosi.ChangeInfo;
import lakercompany.adventure_war.Zaprosi.ChangeShmot;
import lakercompany.adventure_war.R;

public class TovarFragment extends Fragment {
    View view;
    ImageView img;
    TextView name;
    TextView desc;
    Button btn;
    Button close;
    UserShmot us;
    User user = new User();
    LayoutInflater inflater;
    public static int TAG =0;
    private FragmentTransaction fragmentTransaction;
    private TypeFragment tf;
    Magazin.onCloseProduct callback;

    public void setCallback(Magazin.onCloseProduct c){
        callback = c;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TAG=1;
        view = inflater.inflate(R.layout.tovar, container, false);
        this.inflater =inflater;
        img = (ImageView) view.findViewById(R.id.tovar_img);
        name = (TextView) view.findViewById(R.id.tovar_name);
        desc = (TextView) view.findViewById(R.id.tovar_descrip);
        btn = (Button) view.findViewById(R.id.by_but);
        close = (Button) view.findViewById(R.id.close_btn);

        us = new UserShmot();

        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        tf = new TypeFragment();
        fragmentTransaction.add(R.id.type_cont,tf);
        fragmentTransaction.commit();

        switch (us.ts){
            case 1:
                img.setImageResource(us.swards.get(us.tnum).res);
                name.setText(us.swards.get(us.tnum).name);
                desc.setText(us.swards.get(us.tnum).descrip);
                if(us.swards.get(us.tnum).have == 1)
                    btn.setText("Выбрать");
                else
                    btn.setText("Купить");
                break;
            case 2:
                img.setImageResource(us.weapon.get(us.tnum).res);
                name.setText(us.weapon.get(us.tnum).name);
                desc.setText(us.weapon.get(us.tnum).descrip);
                if(us.weapon.get(us.tnum).have == 1)
                    btn.setText("Выбрать");
                else
                    btn.setText("Купить");
                break;
            default:
                img.setImageResource(us.amulet.get(us.tnum).res);
                name.setText(us.amulet.get(us.tnum).name);
                desc.setText(us.amulet.get(us.tnum).descrip);
                if(us.amulet.get(us.tnum).have == 1)
                    btn.setText("Выбрать");
                else
                    btn.setText("Купить");
                break;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (us.ts){
                    case 1:
                        if(us.swards.get(us.tnum).have == 1){
                            us.tsward = us.tnum;
                            finish();
                        }
                        else {
                            if(user.gold >= us.swards.get(us.tnum).price){
                                user.gold -= us.swards.get(us.tnum).price;
                                us.swards.get(us.tnum).have=1;
                                try {
                                    new ChangeShmot(inflater.getContext());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                us.tsward = us.tnum;
                                finish();
                            }
                            else
                                Toast.makeText(inflater.getContext(),"У вас недостаточно средств",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        if(us.weapon.get(us.tnum).have == 1){
                            us.tweapon = us.tnum;
                            finish();
                        }
                        else {
                            if(user.gold >= us.weapon.get(us.tnum).price){
                                user.gold -= us.weapon.get(us.tnum).price;
                                us.weapon.get(us.tnum).have=1;
                                try {
                                    new ChangeShmot(inflater.getContext());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                us.tweapon = us.tnum;
                                finish();
                            }
                            else
                                Toast.makeText(getContext(),"У вас недостаточно средств",Toast.LENGTH_LONG).show();
                        }
                        break;
                    default:
                        if(us.amulet.get(us.tnum).have == 1){
                            us.tamulet = us.tnum;
                            finish();
                        }
                        else {
                            if(user.gold >= us.amulet.get(us.tnum).price){
                                user.gold -= us.amulet.get(us.tnum).price;
                                us.amulet.get(us.tnum).have=1;
                                try {
                                    new ChangeShmot(inflater.getContext());
                                    new ChangeInfo(inflater.getContext());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                us.tamulet = us.tnum;
                                finish();
                            }
                            else
                                Toast.makeText(inflater.getContext(),"У вас недостаточно средств",Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        return view;
    }

    void finish()
    {
        TAG=0;
        callback.onCloseProduct();
    }
}

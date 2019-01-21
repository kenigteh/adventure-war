package lakercompany.adventure_war.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lakercompany.adventure_war.R;
import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.WorkClass.AmuletSposob;

public class TypeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats, container, false);

        ImageView img = (ImageView) view.findViewById(R.id.img_type);
        TextView name = (TextView) view.findViewById(R.id.type_tovar);
        TextView har = (TextView) view.findViewById(R.id.haracter);

        UserShmot us = new UserShmot();
        switch (us.ts){
            case 1:
                img.setImageResource(R.drawable.attack);
                name.setText("Урон");
                har.setText(String.valueOf(us.swards.get(us.tnum).dmg));
                break;
            case 2:
                img.setImageResource(R.drawable.hp);
                name.setText("Жизни");
                har.setText(String.valueOf(us.weapon.get(us.tnum).hp));
                break;
            default:
                img.setImageResource(R.drawable.level);
                name.setText("Способность");
                har.setText(new AmuletSposob().getSposobnost(us.amulet.get(us.tnum).type));
                break;
        }

        return view;
    }
}

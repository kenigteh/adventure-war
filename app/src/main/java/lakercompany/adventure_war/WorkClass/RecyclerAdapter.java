package lakercompany.adventure_war.WorkClass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.Fragments.Magazin;
import lakercompany.adventure_war.R;

/**
 * Created by Laker City on 17.05.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ShmotHolder> {

    UserShmot us = new UserShmot();
    Magazin.onClickProduct callback;

    public void setCallback(Magazin.onClickProduct c){
        callback = c;
    }


    @Override
    public ShmotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_shmot, parent, false);
        return new ShmotHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ShmotHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickProduct(position);
            }
        });

        if(us.ts==1){
            holder.mItemImage.setImageResource(us.swards.get(position).res);
            holder.name.setText(us.swards.get(position).name);
            if(us.tsward == position)
                holder.mItemShmot.setText("Выбрано");
            else if(us.swards.get(position).have==1)
                holder.mItemShmot.setText("Уже есть");
            else
                holder.mItemShmot.setText("Цена - " + String.valueOf(us.swards.get(position).price+"$"));
        }
        else if(us.ts ==2){
            holder.mItemImage.setImageResource(us.weapon.get(position).res);
            holder.name.setText(us.weapon.get(position).name);
            if(us.tweapon == position)
                holder.mItemShmot.setText("Выбрано");
            else if(us.weapon.get(position).have==1)
                holder.mItemShmot.setText("Уже есть");
            else
                holder.mItemShmot.setText("Цена - " + String.valueOf(us.weapon.get(position).price+"$"));
        }
        else{
            holder.mItemImage.setImageResource(us.amulet.get(position).res);
            holder.name.setText(us.amulet.get(position).name);
            if(us.tamulet == position)
                holder.mItemShmot.setText("Выбрано");
            else if(us.amulet.get(position).have==1)
                holder.mItemShmot.setText("Уже есть");
            else
                holder.mItemShmot.setText("Цена - " + String.valueOf(us.amulet.get(position).price+"$"));
        }

    }


    @Override
    public int getItemCount() {
        if(us.ts==1)
            return us.swards.size();
        else if(us.ts ==2)
            return us.weapon.size();
        else
            return us.amulet.size();
    }


}

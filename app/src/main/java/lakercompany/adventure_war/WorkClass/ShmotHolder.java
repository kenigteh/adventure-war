package lakercompany.adventure_war.WorkClass;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lakercompany.adventure_war.R;


public class ShmotHolder extends RecyclerView.ViewHolder{
    ImageView mItemImage;
    TextView mItemShmot;
    TextView name;
    CardView cv;
    View view;

    public ShmotHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        cv=(CardView) itemView.findViewById(R.id.card);
        mItemImage = (ImageView) itemView.findViewById(R.id.card_img);
        mItemShmot = (TextView) itemView.findViewById(R.id.card_price);
        name = (TextView) itemView.findViewById(R.id.card_name);
    }

}

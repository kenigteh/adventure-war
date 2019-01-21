package lakercompany.adventure_war.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lakercompany.adventure_war.User.User;
import lakercompany.adventure_war.User.UserShmot;
import lakercompany.adventure_war.WorkClass.RecyclerAdapter;
import lakercompany.adventure_war.R;


public class Magazin extends Fragment {
    RecyclerView rv;
    LayoutInflater inflater;
    public static final String TAG ="";
    UserShmot us;
    TextView txt;
    TextView money;
    private FragmentTransaction fragmentTransaction;
    private TovarFragment tf;

    View view;

    public interface onClickProduct{
       void  onClickProduct(int point);
    }

    public interface onCloseProduct{
        void  onCloseProduct();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;

        view = inflater.inflate(R.layout.shmot_list, container, false);
        us = new UserShmot();

        txt = (TextView) view.findViewById(R.id.smot_name);
        money = (TextView) view.findViewById(R.id.my_money);
        switch (us.ts){
            case 1:
                txt.setText("Мечи:");
                break;
            case 2:
                txt.setText("Вещи:");
                break;
            default:
                txt.setText("Барахло:");
                break;
        }
        setRecycle();
        return view;
    }

    void setRecycle(){
        money.setText(String.valueOf(new User().gold));
        rv = (RecyclerView) view.findViewById(R.id.rv_shmot);
        GridLayoutManager glm = new GridLayoutManager(inflater.getContext(),3);
        rv.setLayoutManager(glm);
        RecyclerAdapter ra = new RecyclerAdapter();
        tf = new TovarFragment();
        ra.setCallback(new onClickProduct() {
            @Override
            public void onClickProduct(int point) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                if(tf.TAG == 0) {
                    us.tnum = point;
                    fragmentTransaction.add(R.id.shmot_cont, tf);
                    tf.setCallback(new onCloseProduct() {
                        @Override
                        public void onCloseProduct() {
                            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.remove(tf);
                            fragmentTransaction.commit();
                            setRecycle();
                        }
                    });
                }
                fragmentTransaction.commit();
            }
        });
        rv.setAdapter(ra);
    }
}

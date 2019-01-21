package lakercompany.adventure_war.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import lakercompany.adventure_war.R;
import lakercompany.adventure_war.User.Person;
import lakercompany.adventure_war.Zaprosi.getRecords;

public class RecordsFragment extends Fragment  {

    RecyclerView rv;
    LayoutInflater inflater;
    public static final String TAG ="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.inflater=inflater;

        View view = inflater.inflate(R.layout.records, container, false);
        initializeData();

        rv = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(inflater.getContext());
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
        return view;
    }


    private List<Person> persons;
    private void initializeData(){
        persons = new ArrayList<>();
        getRecords gR = new getRecords();
        persons = gR.persons;
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

        public class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView personName;
            TextView personAge;
            ImageView personPhoto;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                personName = (TextView)itemView.findViewById(R.id.name);
                personAge = (TextView)itemView.findViewById(R.id.score);
                personPhoto = (ImageView)itemView.findViewById(R.id.flag);
            }
        }

        List<Person> persons;

        RVAdapter(List<Person> persons){
            this.persons = persons;
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_records, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            personViewHolder.personName.setText(persons.get(i).name);
            personViewHolder.personAge.setText(String.valueOf(persons.get(i).score));
            personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

    }
}

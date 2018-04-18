package fr.wcs.ateliergalaxian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Model> listPlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NourirList();

        Button push = findViewById(R.id.push);
        final EditText nomV = findViewById(R.id.nom);
        final EditText mdpV = findViewById(R.id.mdp);
        final EditText scoreV = findViewById(R.id.score);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nomV.getText().toString();
                String mdp = mdpV.getText().toString();
                String score = scoreV.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Joueur");
                Model joueur = new Model(nom, mdp, score);
                myRef.push().setValue(joueur);

                nomV.setText("");
                nomV.setHint(" nom ");
                mdpV.setText("");
                mdpV.setHint(" Mot de passe");
                scoreV.setText("");
                scoreV.setHint(" Score ");
                Toast.makeText(MainActivity.this, "Please enter new player", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void NourirList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference studentRef = database.getReference("Joueur");
        studentRef.orderByChild("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ModelSnapshot : dataSnapshot.getChildren()){

                    Model player = ModelSnapshot.getValue(Model.class);

                TextView bestPlayer = findViewById(R.id.bestPlayer);
                ListView otherplayer = findViewById(R.id.other);
                bestPlayer.setText("Best score is : " + player.getJoueur() +" = "+ player.getScore());

                Model scorelist = new Model(player.getJoueur(), player.getMdp(), player.getScore());
                listPlayer.add(scorelist);

                    ScoreAdapter adapter = new ScoreAdapter(MainActivity.this, listPlayer);
                    otherplayer.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cant read Firebase Values", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


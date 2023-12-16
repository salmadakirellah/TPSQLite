// MainActivity.java
package ma.fst.projet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ma.fst.projet.classes.Etudiant;
import ma.fst.projet.services.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button add;

    private EditText id;
    private Button rechercher;
    private TextView res;

    // Méthode pour vider les champs après l’ajout
    void clear() {
        nom.setText("");
        prenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService es = new EtudiantService(this);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        add = findViewById(R.id.bn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insertion des étudiants
                es.create(new Etudiant(nom.getText().toString(), prenom.getText().toString()));
                clear();

                // Lancer l'activité ListeEtudiantsActivity pour afficher la liste des étudiants
                startActivity(new Intent(MainActivity.this, ListeEtudiantsActivity.class));
            }
        });

        id = findViewById(R.id.id);
        rechercher = findViewById(R.id.load);
        res = findViewById(R.id.res);

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int studentId = Integer.parseInt(id.getText().toString());
                    Etudiant e = es.findById(studentId);
                    if (e != null) {
                        res.setText(e.getNom() + " " + e.getPrenom());
                    } else {
                        res.setText("Étudiant non trouvé");
                    }
                } catch (NumberFormatException ex) {
                    Log.e("NumberFormatException", ex.getMessage());
                    res.setText("Format d'ID invalide");
                }
            }
        });
    }
}

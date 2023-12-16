// ListeEtudiantsActivity.java
package ma.fst.projet;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ma.fst.projet.classes.Etudiant;
import ma.fst.projet.services.EtudiantService;

public class ListeEtudiantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiants);

        ListView listView = findViewById(R.id.listView);

        // Récupérer la liste des étudiants depuis le service
        EtudiantService etudiantService = new EtudiantService(this);
        List<Etudiant> etudiants = etudiantService.findAll();

        // Créer un tableau de chaînes pour stocker les noms des étudiants
        String[] studentNames = new String[etudiants.size()];
        for (int i = 0; i < etudiants.size(); i++) {
            studentNames[i] = etudiants.get(i).getNom() + " " + etudiants.get(i).getPrenom();
        }

        // Utiliser un adaptateur pour afficher les noms des étudiants dans la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentNames);
        listView.setAdapter(adapter);
    }
}

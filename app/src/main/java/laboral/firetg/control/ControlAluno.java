package laboral.firetg.control;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import laboral.firetg.control.exc.AlunoException;
import laboral.firetg.model.aluno;

public class ControlAluno {
    private FirebaseFirestore database;
    private aluno alu;
    private boolean status;
    private List<aluno> lista_alunos;
    //private DocumentReference documento; //se for precisar do Id depois que inserir


    public ControlAluno(){
        this.database = FirebaseFirestore.getInstance();
        status=false;
    }

    public aluno getAluno() {
        return alu;
    }

    public void setAluno(aluno alu){
        this.alu = alu;
    }

    private void verifica_nome() throws AlunoException {
        if(alu.getNome().equals("")){
            throw new AlunoException(1);
        }
    }

    public boolean registra() throws AlunoException{

        verifica_nome();
        database.collection("alunos").add(alu).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //sucesso
                //documento=documentReference;
                Log.d("aluno","Inserido com Sucesso com o ID"+documentReference.getId());
                status=true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //falha
                Log.wtf("aluno","Falha ao adicionar documento no banco de dados");
                status=false;
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                //cancelado
                Log.w("aluno","O Processo foi cancelado");
                status=false;
            }
        }).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                //completado
                status=true;
            }
        });
        return (status);//se funcionou status;
    }

    public List<aluno> busca(String nome){

        lista_alunos= new ArrayList<aluno>();
        if(nome.equals("")){
            database.collection("alunos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot snap : task.getResult()){
                            Log.d("aluno",snap.getId() + "=>"+snap.getData());
                            lista_alunos.add(snap.toObject(aluno.class));
                        }
                    }else{
                        Log.d("aluno", "Error getting documents: ", task.getException());

                    }
                }
            });
        }else{
            database.collection("alunos").whereEqualTo("nome", nome).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot snap : task.getResult()) {
                            Log.d("aluno", snap.getId() + "=>" + snap.getData());
                            lista_alunos.add(snap.toObject(aluno.class));
                        }
                    } else {
                        Log.d("aluno", "Error getting documents: ", task.getException());
                    }
                }
            });
        }
        return lista_alunos;
    }

}

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

import java.io.IOException;

import laboral.firetg.control.exc.AlunoException;
import laboral.firetg.model.aluno;

public class CadastrarAluno {
    private FirebaseFirestore database;
    private aluno alu;
    private boolean status;
    //private DocumentReference documento; //se for precisar do Id depois que inserir


    public CadastrarAluno(FirebaseFirestore database){
        this.database = database;
        status=false;
    }

    public aluno getAluno() {
        return alu;
    }

    public void setAluno(aluno alu) throws AlunoException {
        this.alu = alu;
        verifica_nome();
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseFirestore database) {
        this.database = database;
    }

    private void verifica_nome() throws AlunoException {
        if(alu.getNome()==null){
            throw new AlunoException("O Nome do aluno não pode estar vazio");
        }
    }

    public boolean registra(){
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


}

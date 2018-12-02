package laboral.firetg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import laboral.firetg.control.ControlAluno;
import laboral.firetg.control.exc.AlunoException;
import laboral.firetg.model.aluno;

public class Cadastrar extends AppCompatActivity {

    private Button jbtnCancelar,jbtnSalvar;
    private EditText jedtNome,jedtObservacao;
    private FirebaseFirestore db;
    aluno a = new aluno();
    ControlAluno ca=new ControlAluno();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        inicializa_componentes();
        standby();
    }

    private void inicializa_componentes(){
        jbtnCancelar=findViewById(R.id.btnCancelar);
        jbtnSalvar=findViewById(R.id.btnSalvar);
        jedtNome=findViewById(R.id.edtNome);
        jedtObservacao=findViewById(R.id.edtObservacao);
        db=FirebaseFirestore.getInstance();

    }
    private void standby() {
        jbtnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Principal.class);
                startActivity(it);
            }
        });

        jbtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setNome(jedtNome.getText().toString());
                a.setObservacao(jedtObservacao.getText().toString());
                try {
                    ca.setAluno(a);
                    if(ca.registra()){
                        Toast.makeText(getApplicationContext(),"Cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
                    }
                } catch (AlunoException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}

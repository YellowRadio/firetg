package laboral.firetg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import laboral.firetg.control.ControlAluno;
import laboral.firetg.control.adp.AlunoAdapter;
import laboral.firetg.model.aluno;

public class Pesquisar extends AppCompatActivity {

    private Button jbtnCancelar, jbtnPesquisar;
    private EditText jedtPesquisar;
    private ListView jlistAluno;
    private ArrayList<aluno> lista_alunos;
    private AlunoAdapter adaptador;
    private ControlAluno ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        inicializa_componentes();
        standby();
    }

    private void inicializa_componentes(){
        jbtnCancelar=findViewById(R.id.btnCancelar);
        jbtnPesquisar=findViewById(R.id.btnPesquisar);
        jedtPesquisar=findViewById(R.id.edtPesquisar);
        jlistAluno=findViewById(R.id.listAluno);

        ca=new ControlAluno();
    }
    private void standby() {
        jbtnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Principal.class);
                startActivity(it);
            }
        });

        jbtnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_alunos=(ArrayList<aluno>)ca.busca(jedtPesquisar.getText().toString());
                adaptador = new AlunoAdapter(getApplicationContext(),lista_alunos);
                jlistAluno.setAdapter(adaptador);
            }
        });
    }
}

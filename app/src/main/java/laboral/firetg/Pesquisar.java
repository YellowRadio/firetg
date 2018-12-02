package laboral.firetg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pesquisar extends AppCompatActivity {

    private Button jbtnCancelar, jbtnPesquisar;
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

    }
    private void standby() {
        jbtnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Principal.class);
                startActivity(it);
            }
        });
    }
}

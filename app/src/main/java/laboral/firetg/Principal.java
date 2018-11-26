package laboral.firetg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Principal extends AppCompatActivity {
    private Button jbtnLogout;
    private TextView jtxtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        inicializa_componentes();
        standby();
    }

    private void inicializa_componentes(){
        jbtnLogout=findViewById(R.id.btnLogout);
    }
    private void standby(){
        jbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(getApplicationContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Deslogou",Toast.LENGTH_SHORT).show();
                        Log.d("Deslogou:","Deslogou com sucesso");
                        startActivity(new Intent(getApplicationContext(),Inicial.class));
                        finish();
                    }
                });
            }
        });
    }
}

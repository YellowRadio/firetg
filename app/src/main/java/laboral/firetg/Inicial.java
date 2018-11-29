package laboral.firetg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class Inicial extends AppCompatActivity {
    /* Choose authentication providers */
    private Button jbtnTentarNov;
    private TextView jtxtFalha;
    FirebaseUser usuario_firebase=null;
    int RC_SIGN_IN=1;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        inicializar_componentes();
        standby();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                usuario_firebase = FirebaseAuth.getInstance().getCurrentUser();
                startActivity(new Intent(getApplicationContext(),Principal.class));
                finish();
            }else{
                Log.d("Erro de autenticacao",response.getError().toString());
                Toast.makeText(this, "Falha ao Conectar, try again", Toast.LENGTH_SHORT).show();
                jtxtFalha.setText("Ocorreu uma falha conecte-se e Faça o Login");
                //vai aparecer o botao se der erro
                jbtnTentarNov.setEnabled(true);
                jbtnTentarNov.setVisibility(View.VISIBLE);
                standby();
            }
        }
    }

    private void inicializar_componentes(){
        jtxtFalha = findViewById(R.id.txtFalha);
        jbtnTentarNov = findViewById(R.id.btnTentarNov);
        jbtnTentarNov.setEnabled(false);
        jbtnTentarNov.setVisibility(View.INVISIBLE);
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false,true).setAvailableProviders(providers).build(),RC_SIGN_IN);
    }

    private void standby(){
        //botao para tentar novamente
        jbtnTentarNov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Inicial.class));
                finish();
            }
        });
    }
}
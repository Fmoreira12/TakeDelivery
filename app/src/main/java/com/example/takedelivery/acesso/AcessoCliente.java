package com.example.takedelivery.acesso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takedelivery.cliente.CadastroInicialCliente;
import com.example.takedelivery.cliente.ClienteActivity;
import com.example.takedelivery.firebase.FirebaseOptions;
import com.example.takedelivery.R;
import com.example.takedelivery.model.Cliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class AcessoCliente extends AppCompatActivity {

    private EditText Email, Senha;
   // private ImageView perfil;
 //   private LoginButton login;
 //   private TextView info;

    private FirebaseAuth autenticar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acesso_cliente);
        autenticar = FirebaseOptions.getFirebaseAutenticacao();
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Take Delivery Cliente");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        Email = findViewById(R.id.writeEmailCliente);
        Senha = findViewById(R.id.writeSenhaCliente);
    }

    public void logarUsuario(Cliente usuario) {

        autenticar.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    abrirTelaCliente();

                } else {

                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usu??rio n??o cadastrado.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email e senha n??o correspondem";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usu??rio: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(AcessoCliente.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void validarAutenticacaoUsuario(View view) {

        String textoEmail = Email.getText().toString();
        String textoSenha = Senha.getText().toString();

        if (!textoEmail.isEmpty()) {
            if (!textoSenha.isEmpty()) {

                Cliente usuario = new Cliente();
                usuario.setEmail(textoEmail);
                usuario.setSenha(textoSenha);

                logarUsuario(usuario);

            } else {
                Toast.makeText(AcessoCliente.this,
                        "Preencha a senha!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(AcessoCliente.this,
                    "Preencha o email!",
                    Toast.LENGTH_SHORT).show();
        }

    }


    //@Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser usuarioAtual = autenticar.getCurrentUser();
//        if (usuarioAtual != null) {
//            abrirTelaCliente();
//        }
//    }

    public void abrirTelaCadastro(View view) {
        Intent intent = new Intent(AcessoCliente.this, CadastroInicialCliente.class);
        startActivity(intent);
    }


    public void abrirTelaCliente() {
        Intent intent = new Intent(AcessoCliente.this, ClienteActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser usuarioAtual = autenticar.getCurrentUser();
//        if ( usuarioAtual != null ){
//            abrirTelaPrincipal();
//        }
//    }

}
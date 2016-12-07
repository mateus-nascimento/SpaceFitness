package ledare.com.br.spacefitness.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import ledare.com.br.spacefitness.R;
import ledare.com.br.spacefitness.SpaceApplication;
import ledare.com.br.spacefitness.model.Aluno;

import static ledare.com.br.spacefitness.activity.MainActivity.USER_LOGIN;

public class LoginActivity extends BaseActivity {

    private SignInButton mButtonGoogle;
    private static final int RC_GOOGLE_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private LoginButton mButtonFacebook;
    private CallbackManager mCallbackmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        setupToolbar("Bem Vindo");

        initGoogleLogin();

        initFacebookLogin();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                showProgress();
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }else{
                toast("Falha no login com o Google");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initGoogleLogin() {

        mButtonGoogle = (SignInButton) findViewById(R.id.button_google_login);
        mButtonGoogle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onGoogleLogin();
                    }
                }
        );

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_key))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        toast("Falha na conexão com o Google");
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void onGoogleLogin(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        SpaceApplication.getInstance().getAuth().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            onAuthSucess(task.getResult().getUser());
                        }else{
                            toast("Falha na autenticação com o Google");
                        }
                    }
                });
    }

    private void initFacebookLogin(){

        mCallbackmanager = CallbackManager.Factory.create();

        mButtonFacebook = (LoginButton) findViewById(R.id.button_facebook_login);
        mButtonFacebook.setReadPermissions("email", "public_profile");

        mButtonFacebook.registerCallback(mCallbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FACEBOOK", "onSuccess: " + loginResult);
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("FACEBOOK", "onCancel!");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FACEBOOK", "onError: ", error);

            }
        });

    }

    private void firebaseAuthWithFacebook(AccessToken token){
        showProgress();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        SpaceApplication.getInstance().getAuth().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            onAuthSucess(task.getResult().getUser());
                        }else{
                            Log.d("FACEBOOK", "onComplete: " + task.getException());
                            toast("Falha na autênticação do Facebook");
                        }

                    }
                });
    }

    private void onAuthSucess(FirebaseUser user) {

        Aluno aluno = new Aluno();
        aluno.id = user.getUid();
        aluno.nome = user.getDisplayName();
        aluno.email = user.getEmail();
        aluno.foto = user.getPhotoUrl().toString();

        SpaceApplication.getInstance().getDatabaseReference()
                .child("alunos")
                .child(user.getUid())
                .setValue(aluno);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean(USER_LOGIN, true).apply();

        hideProgress();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void toast(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }



}

package ledare.com.br.spacefitness.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import ledare.com.br.spacefitness.R;

public class MainActivity extends BaseActivity{

    public static final String LOGIN = "LOGIN";
    public static final String USER_LOGIN = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkUser()){
            setupToolbar();
            setupNavigation();
        }else{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    //Arrumar
    private void setupTreino() {

    }

    public boolean checkUser() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean accepted = pref.getBoolean(USER_LOGIN, false);
        return accepted;
    }

//    Usar para salvar o usuario logado
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor ed = pref.edit();
//        ed.putBoolean(USER_LOGIN, true).apply();
}

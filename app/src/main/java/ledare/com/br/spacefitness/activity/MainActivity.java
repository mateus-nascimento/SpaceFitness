package ledare.com.br.spacefitness.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import ledare.com.br.spacefitness.R;
import ledare.com.br.spacefitness.fragment.LoginFragment;

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
            Fragment loginFragment = LoginFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

            transaction.replace(R.id.content_main, loginFragment, LOGIN);
            transaction.commit();
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

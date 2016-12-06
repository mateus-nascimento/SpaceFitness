package ledare.com.br.spacefitness.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ledare.com.br.spacefitness.R;
import ledare.com.br.spacefitness.fragment.TreinoFragment;

public class MainActivity extends BaseActivity{

    public static final String LOGIN = "LOGIN";
    public static final String USER_LOGIN = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyHash();

        if(checkUser()){
            setupToolbar("Space Fitness");
            setupNavigation();
            setupFragment();
        }else{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void keyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
        }
        catch (NoSuchAlgorithmException e) {
        }
    }


    private void setupFragment() {
        TreinoFragment fragment = TreinoFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, fragment)
                .commit();
    }

    public boolean checkUser() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean accepted = pref.getBoolean(USER_LOGIN, false);
        return accepted;
    }
}

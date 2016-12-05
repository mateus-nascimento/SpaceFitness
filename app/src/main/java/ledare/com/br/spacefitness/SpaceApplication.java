package ledare.com.br.spacefitness;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SpaceApplication extends Application{
    public static final String TAG = "SpaceApplication";

    private static SpaceApplication instance = null;

    public static SpaceApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public DatabaseReference getDatabaseReference(){
        return FirebaseDatabase.getInstance().getReference();
    }

    public FirebaseAuth getAuth(){
        return FirebaseAuth.getInstance();
    }
}

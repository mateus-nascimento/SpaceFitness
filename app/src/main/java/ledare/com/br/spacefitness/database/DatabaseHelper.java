package ledare.com.br.spacefitness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_CARGA;
import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_EXERCICIO;
import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_ID;
import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_REPETICAO;
import static ledare.com.br.spacefitness.database.EvolucaoModel.TABLE_EVOLUCAO;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_EVOLUCAO + "("
            + EVOLUCAO_ID                + " INTEGER AUTOINCREMENT PRIMARY KEY,"
            + EVOLUCAO_EXERCICIO         + " TEXT NOT NULL,"
            + EVOLUCAO_CARGA             + " TEXT NOT NULL,"
            + EVOLUCAO_REPETICAO         + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

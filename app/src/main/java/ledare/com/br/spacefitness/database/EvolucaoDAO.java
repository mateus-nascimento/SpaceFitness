package ledare.com.br.spacefitness.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ledare.com.br.spacefitness.model.Evolucao;

import static ledare.com.br.spacefitness.database.EvolucaoModel.TABLE_EVOLUCAO;
import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_EXERCICIO;
import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_CARGA;
import static ledare.com.br.spacefitness.database.EvolucaoModel.EVOLUCAO_REPETICAO;


public class EvolucaoDAO {

    private Context mContext;

    public EvolucaoDAO(Context context){
        this.mContext = context;
    }

    public void inserirEvolucao(Evolucao evolucao){
        ContentValues values = valuesEvolucao(evolucao);
        getWritable().insert(TABLE_EVOLUCAO, null, values);
        getWritable().close();
    }

    private ContentValues valuesEvolucao(Evolucao evolucao){
        ContentValues values = new ContentValues();
        values.put(EVOLUCAO_EXERCICIO, evolucao.exercicio);
        values.put(EVOLUCAO_CARGA, evolucao.carga);
        values.put(EVOLUCAO_REPETICAO, evolucao.repeticao);
        return values;
    }

    private SQLiteDatabase getReadable(){
        DatabaseHelper helper = new DatabaseHelper(mContext);
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase getWritable(){
        DatabaseHelper helper = new DatabaseHelper(mContext);
        return helper.getWritableDatabase();
    }
}

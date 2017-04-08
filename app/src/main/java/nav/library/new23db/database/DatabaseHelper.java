package nav.library.new23db.database;



        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;

        import nav.library.new23db.constant.DatabaseConstant;


/**
 * Created by abhin on 3/31/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context myContext;
    private String pathToSaveDBFile;
    private static String filePath;

    private static DatabaseHelper instance=null;

    private DatabaseHelper(Context context){
        super(context, DatabaseConstant.DBNANME,null,DatabaseConstant.DATABASE_VERSION);
        this.myContext=context;
        filePath=DatabaseConstant.DBPATH+context.getPackageName()+"/databases/"+DatabaseConstant.DBNANME;
    }

    public String getDBPath(){
        return filePath;
    }
    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance==null){
            instance=new DatabaseHelper(context);
            filePath=context.getDatabasePath(DatabaseConstant.DBNANME).toString();
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        Log.i(DatabaseConstant.TAG,"onCreate");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion,int newVersion){

    }
    public void copyDatabase() throws IOException{
        InputStream is=myContext.getAssets().open(DatabaseConstant.DBNANME);
        File file=new File(filePath);
        file.getParentFile().mkdirs();
        OutputStream os=new FileOutputStream(file);
        byte [] buffer=new byte[1024];
        int length;
        while ((length=is.read(buffer))>0){
            os.write(buffer,0,length);
        }
        is.close();
        os.flush();
        os.close();
    }
    private boolean checkDatabase(){
        boolean checkDB=false;
        try{
            File file=new File(filePath);
            checkDB=file.exists();
        }catch (SQLiteException e){
            Log.e(DatabaseConstant.TAG,e.getMessage());
        }
        return checkDB;
    }
    public void prepareDatabase(){
        boolean dbExist=checkDatabase();
        if(dbExist){

        }else{
            try{
                copyDatabase();
            }catch(IOException e){
                Log.e("TAG",e.getMessage());
            }finally {
                Log.w("DB"," out of prepareDatabase");
            }

        }
    }
    public void closeDB(){
        if(instance!=null){
            instance.close();
        }
    }
}

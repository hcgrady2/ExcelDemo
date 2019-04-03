package com.example.exceldmeo.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.exceldmeo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {


    public static String copyAssetsAndWrite(Context context, String fileName){
        try {

            File file = new File(getSDPath() + "/Record");
            if ( ! file.exists()){
                file.mkdir();
            }

            File outFile = new File(file,fileName);

            if (!outFile.exists()){
                boolean res = outFile.createNewFile();
                if (res){
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                   // Toast.makeText(context,"copy file  success!",Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }else {
                  //  Toast.makeText(context,"文件已经存在",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return "";
    }

    private static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;
    }




}

package com.example.exceldmeo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exceldmeo.bean.Student;
import com.example.exceldmeo.utils.ExcelUtils;
import com.example.exceldmeo.utils.FileUtils;
import com.example.exceldmeo.bean.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button btn_export,btn_inert;

    //ArryaList 中存储 ArrayList(每一个行数据)
    private ArrayList<ArrayList<String>> recordList;
    private ArrayList<ArrayList<String>> recordListWithImg;
    private List<Student> students;
    private ArrayList<Person> personList;
    private static String[] title = { "编号","姓名","性别","年龄","班级","数学","英语","语文" };
    private static String[] titleWithImg = {"编号","姓名","性别","头像"};//用字符串数组存放标题

    private static String[] NameArrays = {"张三","李四","王五","小六","赵四","刘琦"};
    private static String[]  SexArrays = {"男","女"};

    private File file;
    private String fileName;

    public String imageFilePath = "";
    public String ImageFileName = "image.png";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileUtils.copyAssetsAndWrite(this,"image.png");

        //手机本地存储的图片的位置
        imageFilePath = getSDPath() + File.separator + "Record" + File.separator + ImageFileName;

        btn_export = findViewById(R.id.btn_export);
        btn_inert = findViewById(R.id.btn_inert);

        //模拟数据集合
        students = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            students.add(new Student("小红"+i,"女","12","1"+i,"一班","85","77","98"));
            students.add(new Student("小明"+i,"男","14","2"+i,"二班","65","57","100"));
        }

        //数据带图片
        personList = new ArrayList<>();
        Random random=new Random();

        for (int i = 0; i < 10; i ++){
            personList.add(new Person("No." + i,NameArrays[random.nextInt(6)] + i,  SexArrays[random.nextInt(2)] ,imageFilePath));
        }


        btn_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel();
            }
        });

        btn_inert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertImage();
            }
        });


    }

    public void  insertImage(){
        file = new File(getSDPath() + "/Record");
        if ( ! file.exists()){
            makeDir(file);
        }
        ExcelUtils.initExcel(file.toString() + "/带图片Excel.xls", titleWithImg);
        fileName = getSDPath() + "/Record/带图片Excel.xls";
        ExcelUtils.writeObjListToExcelWithImg(getRecordWithImage(), fileName, this);
    }






    /**
     * 导出excel
     * @param
     */
    public void exportExcel( ) {
        file = new File(getSDPath() + "/Record");
        makeDir(file);
        ExcelUtils.initExcel(file.toString() + "/成绩表.xls", title);
        fileName = getSDPath() + "/Record/成绩表.xls";
        ExcelUtils.writeObjListToExcel(getRecordData(), fileName, this);
    }












    /**
     * 将数据集合 转化成ArrayList<ArrayList<String>>
     * @return
     */
    private  ArrayList<ArrayList<String>> getRecordWithImage() {
        recordListWithImg = new ArrayList<>();
        for (int i = 0; i <personList.size(); i++) {
            Person person = personList.get(i);
            ArrayList<String> beanList = new ArrayList<String>();

            beanList.add(person.number);
            beanList.add(person.name);
            beanList.add(person.sex);
            beanList.add(person.avator);

            recordListWithImg.add(beanList);
        }


        return recordListWithImg;
    }





    /**
     * 将数据集合 转化成ArrayList<ArrayList<String>>
     * @return
     */
    private  ArrayList<ArrayList<String>> getRecordData() {
        recordList = new ArrayList<>();
        for (int i = 0; i <students.size(); i++) {
            Student student = students.get(i);
            ArrayList<String> beanList = new ArrayList<String>();
            beanList.add(student.id);
            beanList.add(student.name);
            beanList.add(student.sex);
            beanList.add(student.age);
            beanList.add(student.classNo);
            beanList.add(student.math);
            beanList.add(student.english);
            beanList.add(student.chinese);
            recordList.add(beanList);
        }
        return recordList;
    }

    private  String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;
    }

    public  void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }




}

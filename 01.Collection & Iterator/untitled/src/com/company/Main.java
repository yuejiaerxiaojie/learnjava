package com.company;

import javax.sound.sampled.Line;
import java.util.*;

import static java.lang.System.in;
import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class Main {

    private List<Student> list = new ArrayList<Student>();
    private List<String> listStr = new ArrayList<String>();
    private Map map = new HashMap();
    private Set set = new HashSet();
    private String filePath = "D:/00_git_code/learnjava-1/student.txt";
    private String fileCopyPath = "D:/00_git_code/learnjava-1/student_copy.txt";

    public static void main(String[] args) {
        Main main = new Main();
        //读取文件
        readStudentInfo(main);
        //展示list、map、set中的数据
        showStudentInfo(main);
        //根据ID排序并写入student.txt
        reorderByID(main);
        writeStudentInfo(main);

        //根据name长度排序并写入student.txt
        reorderByName(main);
        writeStudentInfo(main);
    }

    public static void readStudentInfo(Main main){
        //读取student文件内容
        OperateFile txtContent = new OperateFile();
        String result = txtContent.readFile(main.filePath);
        System.out.println(result);

        //获取student中每行的值存入字符串数组，并且获取总行数
        String[] sLines = result.split("\r\n");

        //获取每行的内容并且进行分割；
        for (int i = 0; i < sLines.length; i++) {
            //获取每个学生的信息写入student结构体中
            String[] LineContents = sLines[i].split(";");
            String studentID = LineContents[0].split(":")[1];
            String studentName = LineContents[1].split(":")[1];
            int studentAge = Integer.parseInt(LineContents[2].split(":")[1]);
            int score1 = Integer.parseInt(LineContents[3].split(":")[1]);
            int score2 = Integer.parseInt(LineContents[4].split(":")[1]);
            Student student = new Student(studentID, studentName, studentAge, score1, score2);

            //将结构体数据写入list
            main.list.add(student);
            main.listStr.add(student.getStudentInfo() + "\r\n");
            main.map.put(studentID, student.getStudentInfo());
            main.set.add(student.getStudentInfo());
        }
    }

    public static void showStudentInfo(Main main){
        //读取list中的值
        System.out.print("获取list中的值：\r\n");
        //把数据取出来
        for (Student sd : main.list) {
            System.out.println(sd.getStudentInfo());
        }

        System.out.print("\r\n获取list2中的值：\r\n");
        Iterator it = main.listStr.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "  ");
        }


        //读取map中的值
        System.out.print("\r\n获取map中的值：\r\n");
        Iterator mapit = main.map.entrySet().iterator();
        while (mapit.hasNext()) {
            Map.Entry entry = (Map.Entry) mapit.next();
            System.out.println(entry.getValue());
        }

        //读取set中的值
        System.out.print("\r\n获取set中的值：\r\n");
        Iterator setit = main.set.iterator();
        while (setit.hasNext()) {
            System.out.println(setit.next().toString());
        }
    }

    public static void reorderByID(Main main){
        Collections.sort(main.list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.parseInt(s1.getStudentID()) == Integer.parseInt(s2.getStudentID()) ? 0 : Integer.parseInt(s1.getStudentID()) - Integer.parseInt(s2.getStudentID());
            }
        });

//        Collections.reverse(main.list);

        System.out.println("\r\n排序 by studentID");
        for(Student stu : main.list){
            System.out.println(stu.getStudentInfo());
        }
    }

    public static void reorderByName(Main main){
        Collections.sort(main.list, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().length() == s2.getName().length() ? s1.getName().compareTo(s2.getName()) : s1.getName().length() - s2.getName().length();
            }
        });

        System.out.println("\r\n排序 by StudentName");
        for(Student stu : main.list){
            System.out.println(stu.getStudentInfo());
        }
    }

    public  static void writeStudentInfo(Main main){
        OperateFile.writeFileByBytes(main.fileCopyPath,"\r\n",true);
        //读取student文件内容
        for(Student stu : main.list){
            OperateFile.writeFileByBytes(main.fileCopyPath,stu.getStudentInfo()+ "\r\n",true);
        }
        System.out.println("写入student.txt");
        System.out.println(OperateFile.readFile(main.fileCopyPath));
    }
}

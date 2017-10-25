package com.company;

import java.io.*;
import java.util.List;

/**
 * Created by yuexiaoli on 2017/10/25.
 */
public class OperateFile {
    /**
     * 读取指定文件的内容
     * @param filePath ： 文件的路径
     * @return  返回的结果
     */
    public static String readFile( String filePath ){
        FileInputStream fis=null;
        String result = "" ;
        try {
            // 根据path路径实例化一个输入流的对象
            fis  = new FileInputStream( filePath );

            //2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
            int size =  fis.available() ;
            //3. 根据输入流中的字节数创建byte数组；
            byte[] array = new byte[size];
            //4.把数据读取到数组中；
            fis.read( array ) ;

            //5.根据获取到的Byte数组新建一个字符串，然后输出；
            result = new String(array);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result ;
    }

    /****
     * 字符流读文件，逐行读取
     * @param fileName
     * @return
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                System.out.println(line+":"+tempString );
                builder.append(tempString).append("\n");
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return builder.toString();
    }

    /**
     * 字节流实现写文件
     *
     * @param filePath
     *            ： 文件的路径
     * @param content
     *            : 需要写入的内容
     */
    public static void writeFileByBytes(String filePath, String content, boolean isAppend) {
        FileOutputStream fos = null;
        try {
            // 1、根据文件路径创建输出流
            fos = new FileOutputStream(filePath, isAppend);

            // 2、把string转换为byte数组；
            byte[] array = content.getBytes();
            // 3、把byte数组输出；
            fos.write(array);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

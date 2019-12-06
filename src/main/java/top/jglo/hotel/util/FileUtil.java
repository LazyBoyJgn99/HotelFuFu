package top.jglo.hotel.util;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 * 类说明 文件工具类
 * @author jingening
 */
@Component
public class FileUtil {

    public void upLoadFile(MultipartFile multipartFile,String basePath,String directory,String sftpFileName) throws Exception {
        int n;
        // 得到MultipartFile文件
        File f ;
        // 输出文件的新name 就是指上传后的文件名称
        System.out.println("getName:"+multipartFile.getName());
        // 输出源文件名称 就是指上传前的文件名称
        System.out.println("Oriname:"+multipartFile.getOriginalFilename());
        // 创建文件
        f = new File(multipartFile.getOriginalFilename());
        try (InputStream in  = multipartFile.getInputStream(); OutputStream os = new FileOutputStream(f)){
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            while ((n = in.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            // 读取文件第一行
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            System.out.println(bufferedReader.readLine());
            // 输出路径
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        // 输出file的URL
        System.out.println(f.toURI().toURL().toString());
        // 输出文件的绝对路径
        System.out.println(f.getAbsolutePath());
        File file = new File(f.toURI());

        SFTPUtil sftp = new SFTPUtil("root", "Jgnzxcvbnm,666!", "39.106.56.132", 22);
        sftp.login();
        InputStream is = new FileInputStream(file);
        //改变大小
        File file2=new File("test2");
        OutputStream os = new FileOutputStream(file2);
        new ResizeImg().resizeImage(is, os, "jpg");
        InputStream is2 = new FileInputStream(file2);

        sftp.upload(basePath,directory, sftpFileName, is2);
        sftp.logout();
        // 操作完上的文件 需要删除在根目录下生成的文件
        if (file.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        if (file2.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
}

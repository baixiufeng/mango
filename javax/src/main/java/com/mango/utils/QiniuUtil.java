package com.mango.utils;

import com.google.gson.Gson;
import com.mango.model.MyPutRet;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.sun.tools.javac.jvm.Items;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class QiniuUtil {

    private static  final Logger logger= LoggerFactory.getLogger(QiniuUtil.class);

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.path}")
    private String path;
    @Value("${qiniu.domainOfBucket}")
    private String domainOfBucket;


    //断点上传图片
    public String uploadImg(FileInputStream flie,String key){
        Configuration configuration = new Configuration(Zone.zone0());

        Auth auth = Auth.create(accessKey,secretKey);
        String upToken = auth.uploadToken(bucket);
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(configuration, fileRecorder);
            try {
                Response response = uploadManager.put(flie, key, upToken, null, null);
                MyPutRet myPutRet = response.jsonToObject(MyPutRet.class);
                String return_path = path + "/" + myPutRet.getKey();
                logger.info("return_path", return_path);
                return return_path;

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.print(r.toString());
                try {
                    System.err.print(r.bodyString());
                } catch (QiniuException ex2) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    //私有空间下载文件
    public String privateDownloadImg(String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        Auth auth = Auth.create(accessKey,secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        return finalUrl;
    }

    //获取文件信息
    public FileInfo getImgFile(String key) {
        FileInfo fileInfo = null;
        try {
            fileInfo = getbucketManager(accessKey, secretKey).stat(bucket,key);
        } catch (QiniuException e) {
            logger.info(e.response.toString());
        }
        return fileInfo;
    }
    //移动或重命名文件
    public Response updateImgFile(String fromBucket,String fromKey,String toBucket,String toKey) throws QiniuException {
        return getbucketManager(accessKey,secretKey).move(fromBucket,fromKey,toBucket,toKey);
    }

    //删除空间中的文件
    public Response delImgFile(String key) throws QiniuException {
        return getbucketManager(accessKey,secretKey).delete(bucket,key);
    }
    //获取空间文件列表
    public List<FileInfo> fileInfoList(){
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";

        BucketManager.FileListIterator fileListIterator = getbucketManager(accessKey,secretKey)
                .createFileListIterator(bucket, prefix, limit, delimiter);
        while (fileListIterator.hasNext()){
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item:items){
                fileInfoList.add(item);
                /*System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);*/
            }
        }
        return fileInfoList;
    }

    public List<FileInfo> getfileimg(){
        List<FileInfo> keyList = this.fileInfoList();
        try {
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addStatOps(bucket,keyList.get(1).key);
            Response response = getbucketManager(accessKey,secretKey).batch(batchOperations);
            BatchStatus[] batchStatusesList = response.jsonToObject(BatchStatus[].class);
            for (int i=0;i<keyList.size();i++){
                BatchStatus status = batchStatusesList[i];
                String key = keyList.get(i).key;
                if (status.code ==200){
                    //文件存在
                    System.out.println(status.data.hash);
                    System.out.println(status.data.mimeType);
                    System.out.println(status.data.fsize);
                    System.out.println(status.data.putTime);
                }else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException e) {
            logger.info(e.response.toString());
        }

        return new FinalArrayList();
    }

    public static BucketManager getbucketManager(String accessKey,String secretKey){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth,cfg);
        return bucketManager;
    }
}
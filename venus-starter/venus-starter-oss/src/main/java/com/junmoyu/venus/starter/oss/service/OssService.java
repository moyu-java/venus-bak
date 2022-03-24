package com.junmoyu.venus.starter.oss.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.junmoyu.venus.starter.oss.OssProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * aws-s3 通用存储操作 支持所有兼容s3协议的云存储: {阿里云OSS，腾讯云COS，七牛云，京东云，minio 等}
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@RequiredArgsConstructor
public class OssService implements InitializingBean {

    private final OssProperties ossProperties;

    private AmazonS3 amazonS3;

    /**
     * 创建 bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket((bucketName));
        }
    }

    /**
     * 获取全部 bucket
     *
     * @return bucket 列表
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS API Documentation</a>
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return amazonS3.listBuckets();
    }

    /**
     * 获取 bucket
     *
     * @param bucketName bucket名称
     * @return bucket
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS API Documentation</a>
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return amazonS3.listBuckets().stream().filter(b -> b.getName().equals(bucketName)).findFirst();
    }

    /**
     * 删除 bucket
     *
     * @param bucketName bucket名称
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteBucket">AWS API Documentation</a>
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }

    /**
     * 根据文件前置查询文件
     *
     * @param bucketName bucket名称
     * @param prefix     前缀
     * @param recursive  是否递归查询
     * @return S3ObjectSummary 列表
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListObjects">AWS API Documentation</a>
     */
    @SneakyThrows
    public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
        return new ArrayList<>(objectListing.getObjectSummaries());
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 &lt;=7
     * @return url
     * @see AmazonS3#generatePresignedUrl(String bucketName, String key, Date expiration)
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, expires);
        URL url = amazonS3.generatePresignedUrl(bucketName, objectName, calendar.getTime());
        return url.toString();
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS API Documentation</a>
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return amazonS3.getObject(bucketName, objectName).getObjectContent();
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     */
    @SneakyThrows
    public void putObject(String bucketName, String objectName, InputStream stream) {
        putObject(bucketName, objectName, stream, (long) stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @return 文件上传结果
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/PutObject">AWS API Documentation</a>
     */
    @SneakyThrows
    public PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        byte[] bytes = IOUtils.toByteArray(stream);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contextType);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 上传
        return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 文件信息
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS API Documentation</a>
     */
    @SneakyThrows
    public S3Object getObjectInfo(String bucketName, String objectName) {
        return amazonS3.getObject(bucketName, objectName);
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteObject">AWS API Documentation</a>
     */
    @SneakyThrows
    public void removeObject(String bucketName, String objectName) {
        amazonS3.deleteObject(bucketName, objectName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(ossProperties.getEndpoint(), ossProperties.getRegion());
        AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(), ossProperties.getSecretKey());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        this.amazonS3 = AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration).withClientConfiguration(clientConfiguration).withCredentials(awsCredentialsProvider).disableChunkedEncoding().withPathStyleAccessEnabled(ossProperties.getPathStyleAccess()).build();
    }
}

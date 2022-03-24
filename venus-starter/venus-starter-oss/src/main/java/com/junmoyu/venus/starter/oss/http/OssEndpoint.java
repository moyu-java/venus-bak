package com.junmoyu.venus.starter.oss.http;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.junmoyu.venus.starter.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * aws 对外提供服务端点(默认不开启)
 * oss.info: true 开启
 * 但是开启后可能存在安全问题，需要自行在上层实现安全认证等
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "oss.info", havingValue = "false")
public class OssEndpoint {

    private final OssService ossService;

    /**
     * Bucket Endpoints
     *
     * @param bucketName bucket 名称
     * @return bucket 信息
     */
    @SneakyThrows
    @PostMapping("/bucket/{bucketName}")
    public Bucket createBucket(@PathVariable String bucketName) {
        ossService.createBucket(bucketName);
        Optional<Bucket> bucket = ossService.getBucket(bucketName);
        if (bucket.isPresent()) {
            return bucket.get();
        }
        throw new RuntimeException("create bucket exception.");
    }

    /**
     * 获取所有 bucket 列表
     *
     * @return bucket 列表
     */
    @SneakyThrows
    @GetMapping("/bucket")
    public List<Bucket> getBuckets() {
        return ossService.getAllBuckets();
    }

    @SneakyThrows
    @GetMapping("/bucket/{bucketName}")
    public Bucket getBucket(@PathVariable String bucketName) {
        return ossService.getBucket(bucketName).orElseThrow(() -> new IllegalArgumentException("Bucket Name not found!"));
    }

    @SneakyThrows
    @DeleteMapping("/bucket/{bucketName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBucket(@PathVariable String bucketName) {
        ossService.removeBucket(bucketName);
    }

    @SneakyThrows
    @PostMapping("/object/{bucketName}")
    public S3Object createObject(@RequestBody MultipartFile object, @PathVariable String bucketName) {
        String name = object.getOriginalFilename();
        ossService.putObject(bucketName, name, object.getInputStream(), object.getSize(), object.getContentType());
        return ossService.getObjectInfo(bucketName, name);
    }

    @SneakyThrows
    @PostMapping("/object/{bucketName}/{objectName}")
    public S3Object createObject(@RequestBody MultipartFile object, @PathVariable String bucketName, @PathVariable String objectName) {
        ossService.putObject(bucketName, objectName, object.getInputStream(), object.getSize(), object.getContentType());
        return ossService.getObjectInfo(bucketName, objectName);

    }

    @SneakyThrows
    @GetMapping("/object/{bucketName}/{objectName}")
    public List<S3ObjectSummary> filterObject(@PathVariable String bucketName, @PathVariable String objectName) {
        return ossService.getAllObjectsByPrefix(bucketName, objectName, true);
    }

    @SneakyThrows
    @GetMapping("/object/{bucketName}/{objectName}/{expires}")
    public Map<String, Object> getObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable Integer expires) {
        Map<String, Object> responseBody = new HashMap<>(8);
        // Put Object info
        responseBody.put("bucket", bucketName);
        responseBody.put("object", objectName);
        responseBody.put("url", ossService.getObjectUrl(bucketName, objectName, expires));
        responseBody.put("expires", expires);
        return responseBody;
    }

    @SneakyThrows
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/object/{bucketName}/{objectName}/")
    public void deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {
        ossService.removeObject(bucketName, objectName);
    }
}

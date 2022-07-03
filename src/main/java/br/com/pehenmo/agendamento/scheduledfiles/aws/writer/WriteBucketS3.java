package br.com.pehenmo.agendamento.scheduledfiles.aws.writer;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;

public class WriteBucketS3 {

    public static void putObject(S3Client s3, String keyName, String bucketName, String path) {

        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder().key(keyName).bucket(bucketName).build();
            RequestBody requestBody = RequestBody.fromFile(new File(path));
            s3.putObject(objectRequest, requestBody);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails());
            System.exit(1);

        }
    }
}

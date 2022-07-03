package br.com.pehenmo.agendamento.scheduledfiles.aws.reader;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.InputStream;

public class ReadBucketS3Class {

    public static InputStream getObject (S3Client s3, String bucketName, String keyName) {

        try {

            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            ResponseInputStream<GetObjectResponse> objectInputStream = s3.getObject(objectRequest);

            return objectInputStream;

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }
}

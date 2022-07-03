package br.com.pehenmo.agendamento.scheduledfiles.aws.config;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

@Configuration
public class AWSCredentials implements AwsCredentials {
    @Override
    public String accessKeyId() {
        return System.getenv("AWS_ACCESS_KEY_ID");
    }

    @Override
    public String secretAccessKey() {
        return System.getenv("AWS_SECRET_ACCESS_KEY");
    }
}

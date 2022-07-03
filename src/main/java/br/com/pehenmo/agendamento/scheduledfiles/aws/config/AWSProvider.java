package br.com.pehenmo.agendamento.scheduledfiles.aws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;


@Configuration
public class AWSProvider implements AwsCredentialsProvider {

    @Autowired
    private AWSCredentials awsCredentials;

    @Override
    public AwsCredentials resolveCredentials() {
        return awsCredentials;
    }
}

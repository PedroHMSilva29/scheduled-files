package br.com.pehenmo.agendamento.scheduledfiles.aws.factory;

import br.com.pehenmo.agendamento.scheduledfiles.aws.config.AWSProvider;
import br.com.pehenmo.agendamento.scheduledfiles.aws.config.AWSS3Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class AWSS3ClientFactory {

    @Autowired
    private AWSProvider provider;

    @Autowired
    private AWSS3Region region;


    public S3Client generate(){
        return S3Client.builder().region(region.getRegion()).credentialsProvider(provider).build();
    }

}

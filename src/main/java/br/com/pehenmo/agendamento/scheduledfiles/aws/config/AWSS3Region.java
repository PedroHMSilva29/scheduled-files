package br.com.pehenmo.agendamento.scheduledfiles.aws.config;

import software.amazon.awssdk.regions.Region;

public class AWSS3Region {

    private static final Region REGION = Region.US_EAST_2;

    public static Region getRegion() {
        return REGION;
    }
}

package io.kinguin.nft.broker.contracts;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
@EnableConfigurationProperties(ContractsProperties.class)
public class ContractsBeans {

    @Bean
    public Credentials credentials() {
        return Credentials.create("3453543");
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return new DefaultGasProvider();
    }

    @Bean
    public Web3j web3j(ContractsProperties contractsProperties) {
        return Web3j.build(new HttpService(String.format("%s%s", contractsProperties.getInfuraUrl(), contractsProperties.getInfuraProjectId())));
    }
}

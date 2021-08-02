package io.kinguin.nft.broker.contracts.cryptostrikers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kinguin.nft.broker.contracts.cryptostrikers.contract.CryptoStrikersContract;
import io.kinguin.nft.broker.contracts.cryptostrikers.repositories.CryptoStrikersTokensRepository;
import okhttp3.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

@Configuration
@EnableConfigurationProperties(CryptoStrikersProperties.class)
public class CryptoStrikersBeans {
    @Bean
    public CryptoStrikersContract cryptoStrikersContract(
        CryptoStrikersProperties cryptoStrikersProperties,
        Web3j web3j,
        Credentials credentials,
        ContractGasProvider contractGasProvider
    ) {
        return CryptoStrikersContract.load(cryptoStrikersProperties.getContractAddress(), web3j, credentials, contractGasProvider);
    }

    @Bean
    public CryptoStrikersTokensRepository cryptoStrikersRepository(CryptoStrikersContract cryptoStrikersContract, ModelMapper cryptoStrikersMetadataToTokenMapper) {
        return new CryptoStrikersTokensRepository(cryptoStrikersContract, new OkHttpClient(), new ObjectMapper(), cryptoStrikersMetadataToTokenMapper);
    }
}

package io.kinguin.nft.broker;

import io.kinguin.nft.broker.contracts.cryptostrikers.repositories.CryptoStrikersTokensRepository;
import io.kinguin.nft.broker.exceptions.TokenException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Log
@SpringBootApplication
public class BrokerApplication {
    @Autowired
    private CryptoStrikersTokensRepository cryptoStrikersRepository;

    @PostConstruct
    public void listen() throws TokenException {
        log.info(cryptoStrikersRepository.getTokensOfOwner("0xefaa0f8e57f6280d46072b97473ac3f04b083f9d").toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(BrokerApplication.class, args);
    }
}

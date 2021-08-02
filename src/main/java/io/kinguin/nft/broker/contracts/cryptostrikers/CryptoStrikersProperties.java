package io.kinguin.nft.broker.contracts.cryptostrikers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tokens.nft.crypto-strikers")
public class CryptoStrikersProperties {
    private String contractAddress;
}

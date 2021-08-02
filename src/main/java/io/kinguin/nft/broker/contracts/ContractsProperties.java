package io.kinguin.nft.broker.contracts;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "contracts")
public class ContractsProperties {
    private String infuraUrl;
    private String infuraProjectId;
}

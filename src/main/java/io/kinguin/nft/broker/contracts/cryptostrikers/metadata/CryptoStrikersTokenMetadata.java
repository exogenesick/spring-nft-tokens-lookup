package io.kinguin.nft.broker.contracts.cryptostrikers.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoStrikersTokenMetadata {
    private String image;
    private String name;
    private List<CryptoStrikersTokenAttributeMetadataDto> attributes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CryptoStrikersTokenAttributeMetadataDto {
        private String trait_type;
        private String value;
    }
}
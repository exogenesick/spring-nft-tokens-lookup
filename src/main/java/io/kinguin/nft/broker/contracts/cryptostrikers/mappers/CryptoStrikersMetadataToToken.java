package io.kinguin.nft.broker.contracts.cryptostrikers.mappers;

import io.kinguin.nft.broker.contracts.cryptostrikers.metadata.CryptoStrikersTokenMetadata;
import io.kinguin.nft.broker.tokens.Token;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CryptoStrikersMetadataToToken {

    @Bean
    public ModelMapper cryptoStrikersMetadataToTokenMapper() {
        ModelMapper mapper = new ModelMapper();

        PropertyMap<CryptoStrikersTokenMetadata, Token> productDtoProductPropertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(traitsConverter()).map(source.getAttributes(), destination.getTraits());
            }
        };

        mapper.addMappings(productDtoProductPropertyMap);

        return mapper;
    }

    private AbstractConverter traitsConverter() {
        return new AbstractConverter<List<CryptoStrikersTokenMetadata.CryptoStrikersTokenAttributeMetadataDto>, Map<String, String>>() {
            @Override
            protected Map<String, String> convert(List<CryptoStrikersTokenMetadata.CryptoStrikersTokenAttributeMetadataDto> attributes) {
                if (attributes == null) {
                    return null;
                }

                return attributes.stream()
                    .collect(Collectors.toMap(CryptoStrikersTokenMetadata.CryptoStrikersTokenAttributeMetadataDto::getTrait_type,
                        CryptoStrikersTokenMetadata.CryptoStrikersTokenAttributeMetadataDto::getValue));
            }
        };
    }
}

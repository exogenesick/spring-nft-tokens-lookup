package io.kinguin.nft.broker.tokens;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String name;
    private String image;
    private Map<String, String> traits;
}
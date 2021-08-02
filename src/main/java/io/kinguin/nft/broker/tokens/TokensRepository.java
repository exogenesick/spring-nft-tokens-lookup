package io.kinguin.nft.broker.tokens;

import io.kinguin.nft.broker.exceptions.TokenException;

import java.util.List;

public interface TokensRepository {
    List<Token> getTokensOfOwner(String ownerAddress) throws TokenException;
    Token getToken(String tokenId) throws TokenException;
}

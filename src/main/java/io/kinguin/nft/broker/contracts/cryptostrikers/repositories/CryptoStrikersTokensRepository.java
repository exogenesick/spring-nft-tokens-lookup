package io.kinguin.nft.broker.contracts.cryptostrikers.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kinguin.nft.broker.contracts.cryptostrikers.contract.CryptoStrikersContract;
import io.kinguin.nft.broker.contracts.cryptostrikers.metadata.CryptoStrikersTokenMetadata;
import io.kinguin.nft.broker.exceptions.TokenException;
import io.kinguin.nft.broker.tokens.Token;
import io.kinguin.nft.broker.tokens.TokensRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log
@AllArgsConstructor
public class CryptoStrikersTokensRepository implements TokensRepository {
    private CryptoStrikersContract cryptoStrikersContract;
    private OkHttpClient okHttpClient;
    private ObjectMapper objectMapper;
    private ModelMapper cryptoStrikersMetadataToTokenMapper;

    @Override
    public List<Token> getTokensOfOwner(String ownerAddress) throws TokenException {
        try {
            List tokensIds = cryptoStrikersContract.tokenIdsForOwner(ownerAddress)
                .send();
            List<Token> fetchedTokens = (List<Token>) tokensIds.stream()
                .map((tokenId) -> apply((BigInteger) tokenId))
                .collect(Collectors.toList());
            fetchedTokens.removeAll(Collections.singleton(null));
            return fetchedTokens;
        } catch (Exception e) {
            throw new TokenException(String.format("Error has been occur during fetching tokens for CryptoStrikers. Cause: %s", e.getMessage()));
        }
    }

    @Override
    public Token getToken(String tokenId) throws TokenException {
        try {
            String tokenURI = cryptoStrikersContract.tokenURI(new BigInteger(tokenId)).send();
            return cryptoStrikersMetadataToTokenMapper.map(getTokenMetadata(tokenURI), Token.class);
        } catch (Exception e) {
            throw new TokenException(String.format("Could not get token %s. Cause: %s", tokenId, e.getMessage()));
        }
    }

    private Token apply(BigInteger tokenId) {
        try {
            return getToken(tokenId.toString());
        } catch (TokenException e) {
            log.warning(String.format("There was issue with getting token %s. Token has been skipped.", tokenId));
            return null;
        }
    }

    private CryptoStrikersTokenMetadata getTokenMetadata(String url) throws TokenException {
        try {
            Response response = okHttpClient.newCall(new Request.Builder().url(url).build()).execute();
            return objectMapper.readValue(response.body().string(), CryptoStrikersTokenMetadata.class);
        } catch (IOException e) {
            throw new TokenException(String.format("Getting token %s metadata has failed. Cause: %s", url, e.getMessage()));
        }
    }
}
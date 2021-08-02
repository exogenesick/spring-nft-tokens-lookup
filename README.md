# Spring NFT Tokens lookup 

## Purpose

Getting tokens out of smart contracts for given owner address.

### Before use

Put your Infura project key into `application.yml`

### Usage

```
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
```

### Launch
```
./gradlew clean check bootRun
```
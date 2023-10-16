package com.integrasi.lhd.sinkrondataconsumer.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import com.integrasi.lhd.sinkrondataconsumer.repo.ClientRepository;
import com.integrasi.lhd.sinkrondataconsumer.entity.Client;
import java.time.LocalDate;

@Slf4j
@Service
public class CronGetClientFromKafkaService {

@Resource
protected ObjectMapper mapper;

@Resource
protected ClientRepository clientRepository;

@Setter
@Autowired
protected RedisTemplate<String, Object> redisTemplate;

@KafkaListener(topics = "${topic.client.kafka}")
public void listenClientSubscribe(String message) {
   log.debug("Receiving Client Publish Message: {}", message);
   try {
      ExchangeDtoClient exchangeClient = mapper.readValue(message, ExchangeDtoClient.class);
      processClient(exchangeClient);
  } catch (KafkaException e) {
     log.error("Error Kafka : {}", e.getMessage());
  } catch (Exception e) {
    log.error(e.getMessage());
  }
}

protected void processClient(ExchangeDtoClient exchangeClient) {

Client client = clientRepository.findById(exchangeClient.getClientId()).orElse(null);
String clientCode = exchangeClient.getCode();
Map<Object, Object> clientFromCache = redisTemplate.opsForHash().entries(clientCode);
if (client == null || clientFromCache == null) {
log.debug("Client Not Found In Database or in Redis For Client Code {}, Try Insert To Database", exchangeClient.getCode());
clientRepository.saveAndFlush(exchangeClient));
log.debug("Client Code {}, Success Insert To Database", exchangeClient.getCode());
} else {
log.debug("Client Found In Database For Client Id ");
}
}


protected Client formatClientToDb(ExchangeDtoClient exchangeClient) {
Client client = new Client();
client.setClientId(exchangeClient.getClientId());
client.setClientCode(exchangeClient.getCode());
client.setCLientName(exchangeClient.getName());
client.setAddress(exchangeClient.getAddress());

return client;
}

}
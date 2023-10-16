package com.integrasi.lhd.sinkrondataconsumer.dto.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Generated
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeClient {

protected long clientId;

protected String code;

protected String name;

protected String address;



}

package com.integrasi.lhd.sinkrondataconsumer.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Generated
@Entity
@Table(name = "client")
public class Client implements Serializable {

@Id
@Column(name = "id")
protected Long clientId;

@Column(name = "client_code")
protected String clientCode;

@Column(name = "client_name")
protected String clientName;

@Column(name = "address")
protected String address;

}
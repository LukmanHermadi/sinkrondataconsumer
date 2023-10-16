package com.integrasi.lhd.sinkrondataconsumer.repo;

import com.integrasi.lhd.sinkrondataconsumer.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
Client findOneByCode(String code);

}

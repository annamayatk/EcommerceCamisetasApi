package com.stylescoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stylescoder.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

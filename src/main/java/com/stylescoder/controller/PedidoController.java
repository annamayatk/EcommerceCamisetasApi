package com.stylescoder.controller;

import com.stylescoder.dto.PedidoRequestDTO;
import com.stylescoder.dto.PedidoResponseDTO;
import com.stylescoder.entity.Pedido;
import com.stylescoder.mapper.PedidoMapper;
import com.stylescoder.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody PedidoRequestDTO dto) {
        Pedido novoPedido = pedidoService.criarPedido(dto);
        PedidoResponseDTO responseDTO = PedidoMapper.toDTO(novoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        List<PedidoResponseDTO> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        PedidoResponseDTO responseDTO = PedidoMapper.toDTO(pedido);
        return ResponseEntity.ok(responseDTO);
    }
}

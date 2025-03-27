package br.com.fiap.checkpoint1.controller;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listAll() {
        return pedidoService.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    public Pedido create(@RequestBody @Valid Pedido pedido) {
        pedido.setDataPedido(LocalDate.now());
        return pedidoService.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody @Valid Pedido pedidoAtualizado) {
        Optional<Pedido> pedidoExistente = pedidoService.findById(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setClienteNome(pedidoAtualizado.getClienteNome());
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());

            Pedido pedidoSalvo = pedidoService.save(pedido);
            return ResponseEntity.ok(pedidoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
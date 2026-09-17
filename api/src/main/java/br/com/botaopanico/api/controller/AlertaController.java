package br.com.botaopanico.api.controller;

import br.com.botaopanico.api.model.Alerta;
import br.com.botaopanico.api.repository.AlertaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Informa ao Spring que esta classe vai responder requisições da internet
@RequestMapping("/api/alertas") // Define o endereço (URL) principal. Ex: http://localhost:8080/api/alertas
@CrossOrigin(origins = "*") // Permite que a tela do painel e outros sistemas se comuniquem com essa API
public class AlertaController {

    // Variável que nos permite usar os superpoderes de salvar e buscar do banco
    private final AlertaRepository repository;

    // O Spring Boot injeta o repository automaticamente aqui
    public AlertaController(AlertaRepository repository) {
        this.repository = repository;
    }

    // Método POST: Usado para CRIAR algo novo.
    // O script da máquina do usuário vai enviar os dados para cá.
    @PostMapping
    public Alerta receberAlerta(@RequestBody Alerta alerta) {
        // Pega o alerta recebido, salva no banco de dados e devolve ele pronto (agora
        // com um ID e Data)
        return repository.save(alerta);
    }

    // Método GET: Usado para BUSCAR informações.
    // O painel de monitoramento vai acessar essa URL a cada 5 segundos.
    @GetMapping("/pendentes")
    public List<Alerta> listarPendentes() {
        // Usa o método que você mesmo criou no passo anterior!
        return repository.findByStatusAtendimento("PENDENTE");
    }

    // Rota para o painel marcar o alerta como resolvido
    // Adicionamos o @RequestParam para receber o nome do agente pela URL
    @PutMapping("/{id}/atender")
    public Alerta atenderAlerta(@PathVariable Long id, @RequestParam String agente) {
        Alerta alerta = repository.findById(id).orElseThrow();

        alerta.setStatusAtendimento("ATENDIDO");
        alerta.setAgenteAtendimento(agente); // Salva o nome de quem clicou no botão

        return repository.save(alerta);
    }

    // ROTA NOVA: Retorna todo o histórico de alertas
    @GetMapping("/historico")
    public List<Alerta> listarHistorico() {
        return repository.findAllByOrderByIdDesc();
    }
}
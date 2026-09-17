package br.com.botaopanico.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioAd;
    private String nomeMaquina;
    private LocalDateTime dataHora = LocalDateTime.now();
    private String statusAtendimento = "PENDENTE";

    // NOVO CAMPO: Guardará o nome de quem atendeu
    private String agenteAtendimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuarioAd() {
        return usuarioAd;
    }

    public void setUsuarioAd(String usuarioAd) {
        this.usuarioAd = usuarioAd;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(String statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    // NOVOS GETTERS E SETTERS
    public String getAgenteAtendimento() {
        return agenteAtendimento;
    }

    public void setAgenteAtendimento(String agenteAtendimento) {
        this.agenteAtendimento = agenteAtendimento;
    }
}
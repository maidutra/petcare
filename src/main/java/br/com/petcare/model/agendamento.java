package br.com.petcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePet;
    private String tipoPet;
    private String raca;
    private String porte;
    private String servico;
    private String data;    
    private String horario; 
    private String nomeTutor;
    private String telefone;
    private String observacoes;
    
    // Adicionamos o status com um valor padrão para novos agendamentos
    private String status = "PENDENTE"; 

    // NOVOS CAMPOS: Para salvar a opção de TaxiDog e o endereço de busca
    private String tipoEntrega; 
    private String enderecoTaxi;

    public agendamento() {}

    // GETTERS E SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomePet() { return nomePet; }
    public void setNomePet(String nomePet) { this.nomePet = nomePet; }
    public String getTipoPet() { return tipoPet; }
    public void setTipoPet(String tipoPet) { this.tipoPet = tipoPet; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public String getPorte() { return porte; }
    public void setPorte(String porte) { this.porte = porte; }
    public String getServico() { return servico; }
    public void setServico(String servico) { this.servico = servico; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getNomeTutor() { return nomeTutor; }
    public void setNomeTutor(String nomeTutor) { this.nomeTutor = nomeTutor; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    // Getters e Setters do Status adicionados
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // GETTERS E SETTERS ADICIONADOS PARA O TAXIDOG
    public String getTipoEntrega() { return tipoEntrega; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }
    public String getEnderecoTaxi() { return enderecoTaxi; }
    public void setEnderecoTaxi(String enderecoTaxi) { this.enderecoTaxi = enderecoTaxi; }
}
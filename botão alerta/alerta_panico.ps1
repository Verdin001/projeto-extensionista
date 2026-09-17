# Captura os dados do ambiente do Windows (AD e Nome do PC)
$usuario = $env:USERNAME
$maquina = $env:COMPUTERNAME

# ATENÇÃO: Troque "localhost" pelo IP do servidor onde o Spring Boot vai rodar na rede do hospital
$apiUrl = "http://localhost:8080/api/alertas"

# Converte os dados para o formato JSON que nossa API espera
$body = @{
    usuarioAd = $usuario
    nomeMaquina = $maquina
} | ConvertTo-Json

# Tenta enviar para a API. Se o servidor estiver fora, ele falha silenciosamente sem assustar o usuário
try {
    Invoke-RestMethod -Uri $apiUrl -Method Post -Body $body -ContentType "application/json"
} catch {
    Write-Error "Não foi possível contatar o servidor de monitoramento."
}
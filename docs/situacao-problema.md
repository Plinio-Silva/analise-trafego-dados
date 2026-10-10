Monitoramento do Tráfego de Rede em uma Empresa Durante 24 Horas
Introdução

Uma empresa de tecnologia com aproximadamente 120 colaboradores utiliza diariamente diversos serviços digitais
para apoiar suas operações. Entre os principais serviços utilizados estão:

Sistema ERP hospedado em nuvem;
Correio eletrônico corporativo;
Compartilhamento de arquivos;
Videoconferências;
Sistema de chamados técnicos;
Banco de dados corporativo;
Servidor de backup automatizado.

A empresa possui uma conexão principal de Internet com capacidade máxima de:

1000 Mbps1000\ \text{Mbps}

Além disso, mantém um servidor em nuvem responsável por sincronizações, armazenamento de arquivos
e execução de rotinas automáticas durante períodos de baixa utilização.

O setor de Tecnologia da Informação deseja analisar o comportamento do tráfego da rede
ao longo de um dia inteiro para:

Identificar horários de pico;
Identificar períodos de baixa utilização;
Estimar riscos de congestionamento;
Planejar futuras expansões da infraestrutura.

Para isso, foi realizado um monitoramento do tráfego médio da rede durante 24 horas.

Dados Coletados
Horário	Tempo (h)	Tráfego (Mbps)00:00	0	40
02:00	2	35
04:00	4	30
06:00	6	45
08:00	8	50
10:00	10	130
12:00	12	370
14:00	14	820
15:00	15	980
16:00	16	900
18:00	18	650
20:00	20	300
22:00	22	120
24:00	24	50
Contexto Operacional
Madrugada (00h às 06h)

Durante a madrugada, a maior parte dos colaboradores está fora do expediente. 
O tráfego observado é gerado principalmente por:

Backups automáticos;
Sincronização em nuvem;
Monitoramento dos servidores;
Atualizações automáticas.

Consequentemente, o consumo da rede permanece baixo.

Início do Expediente (08h às 12h)

Com o início das atividades dos colaboradores, ocorre um aumento gradual no uso dos recursos corporativos, 
incluindo:

Sistemas internos;
Correio eletrônico;
Armazenamento em nuvem;
Bancos de dados.

Nesse período, o tráfego da rede cresce rapidamente.

Pico Operacional (12h às 16h)

Durante o período de maior utilização, ocorrem simultaneamente:

Videoconferências;
Acesso intenso ao ERP;
Transferência de arquivos;
Consultas ao banco de dados.

A rede aproxima-se do limite de sua capacidade operacional.

O maior valor registrado foi:

980 Mbps980\ \text{Mbps}

às 15h, correspondendo a 98% da capacidade total disponível.

Encerramento do Expediente (16h às 20h)

Após o horário de pico, os usuários começam a encerrar suas atividades gradualmente.

Como consequência, o tráfego da rede apresenta uma tendência de redução.

Período Noturno (20h às 24h)

Nesse período permanecem ativos apenas os serviços automatizados da infraestrutura.

A utilização da rede retorna a níveis próximos aos observados durante a madrugada.

Objetivos da Análise Matemática

Com base nos dados coletados, deseja-se aplicar conceitos estudados na disciplina de Cálculo I 
para responder às seguintes questões:

Como representar matematicamente os diferentes trechos do comportamento da rede?

Como estimar o tráfego em horários intermediários, como:

09h30;
13h15;
17h40?

(Interpolação)

Em quais intervalos a função é crescente?

T′(t)>0T'(t) > 0
Em quais intervalos a função é decrescente?
T′(t)<0T'(t) < 0

Qual é o horário de máxima utilização da rede?

(Máximo local ou absoluto)

Qual é o horário de mínima utilização da rede?

(Mínimo local ou absoluto)

Qual é a taxa de crescimento do tráfego em cada instante?

(Interpretação da derivada)

Em qual momento a rede aproxima-se da capacidade máxima de 1000 Mbps?

O que acontece com o tráfego quando o tempo se aproxima do horário de pico?

(Conceito de limite)

Como os conceitos de Limites e Derivadas podem auxiliar na previsão de congestionamentos
e no planejamento da infraestrutura?

Aplicação dos Conceitos de Cálculo I

Este cenário foi desenvolvido para permitir a aplicação prática de diversos conceitos 
estudados na disciplina de Cálculo I, tais como:

✅ Funções

✅ Interpretação de gráficos

✅ Crescimento e decrescimento

✅ Máximos e mínimos

✅ Limites

✅ Derivadas

✅ Taxa de variação

✅ Reta tangente

✅ Interpolação

✅ Aplicações em Ciência da Computação

Conclusão

O monitoramento do tráfego de rede apresentado neste estudo reproduz uma situação próxima
à realidade de equipes de infraestrutura de TI. 
Através da modelagem matemática dos dados coletados, torna-se possível aplicar
conceitos fundamentais de Cálculo I para compreender o comportamento da rede, 
identificar gargalos, prever congestionamentos e apoiar decisões relacionadas ao planejamento 
e à expansão da infraestrutura computacional.

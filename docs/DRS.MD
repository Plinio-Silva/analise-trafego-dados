DRS – Análise de Tráfego de Dados
1. Introdução
   1.1 Objetivos deste documento

Este documento tem como objetivo especificar os requisitos funcionais e não funcionais do sistema Análise de Tráfego de Dados, descrevendo suas funcionalidades, regras de negócio, usuários e restrições.

O documento servirá como base para o desenvolvimento do software e para a validação dos requisitos definidos para o projeto.

Público-alvo
Professor da disciplina de Cálculo 1;
Equipe de desenvolvimento;
Alunos da disciplina;
Usuários responsáveis pela gestão da infraestrutura tecnológica da empresa.
1.2 Escopo do Produto
1.2.1 Nome do Produto

Análise de Tráfego de Dados

Funcionalidades principais
Importação de medições de tráfego;
Construção automática de gráficos;
Identificação de crescimento e decrescimento;
Localização de máximos e mínimos;
Interpolação de valores;
Geração de relatório executivo;
Emissão de alertas de congestionamento;
Funcionalidade de Interpolação no Sistema;
Apoio à tomada de decisões gerenciais.
1.2.2 Descrição do Produto

O sistema Análise de Tráfego de Dados é uma aplicação web desenvolvida em Spring Boot destinada à análise matemática de dados de utilização de rede corporativa.

O sistema receberá dados coletados por ferramentas de monitoramento ao longo de um dia de operação.

A partir desses dados, serão aplicados conceitos de Cálculo Diferencial para produzir análises e indicadores capazes de auxiliar gestores na avaliação da infraestrutura tecnológica da empresa.

1.2.3 Missão do Produto

Transformar dados brutos de tráfego de rede em informações estratégicas que auxiliem gestores na identificação de riscos, gargalos e necessidades de expansão da infraestrutura.

1.3 Definições e Siglas
Sigla	DefiniçãoMbps	Megabits por segundo
TI	Tecnologia da Informação
CSV	Comma Separated Values
RF	Requisito Funcional
RNF	Requisito Não Funcional
DRS	Documento de Requisitos de Software
API	Application Programming Interface
1.4 Técnicas Utilizadas

Os requisitos foram levantados através de:

análise do cenário-problema;
pesquisa sobre monitoramento de redes;
entrevistas simuladas com o perfil de gerente de infraestrutura;
modelagem de requisitos baseada em casos de uso.
2. Descrição Geral do Produto
   2.1 Perspectiva do Produto

O sistema funcionará como uma ferramenta complementar aos softwares de monitoramento de rede.

Seu objetivo não é coletar métricas diretamente dos equipamentos, mas receber dados já coletados e transformá-los em análises matemáticas e relatórios gerenciais.

Fluxo geral:

Software de Monitoramento
↓
Arquivo CSV
↓
Sistema Análise de Tráfego de Dados
↓
Modelagem Matemática
↓
Relatório Executivo

2.2 Usuários e Sistemas Relacionados
Ator	DescriçãoGerente	Responsável pela tomada de decisões estratégicas
Analista de TI	Responsável pela análise dos relatórios
Sistema de Monitoramento	Ferramenta responsável pela coleta dos dados
Sistema Análise de Tráfego	Sistema objeto deste documento
3. Requisitos Específicos
   3.1 Prioridades dos Requisitos
   Essencial

Sem este requisito o sistema não atende seu objetivo principal.

Importante

Agrega valor significativo ao sistema.

Desejável

Pode ser implementado em versões futuras.

3.2 Requisitos Funcionais
[RF01] Importar Dados de Tráfego
Descrição

O sistema deverá permitir que o usuário envie um arquivo CSV contendo os pontos coletados durante o monitoramento.

Prioridade

Essencial

[RF02] Exibir Gráfico de Tráfego
Descrição

O sistema deverá gerar automaticamente um gráfico contendo a evolução do tráfego ao longo do tempo.

Prioridade

Essencial

[RF03] Identificar Crescimento e Decrescimento
Descrição

O sistema deverá identificar automaticamente os intervalos onde o tráfego cresce ou diminui.

Prioridade

Essencial

[RF04] Calcular Máximos e Mínimos
Descrição

O sistema deverá identificar:

máximos locais;
mínimos locais;
máximo absoluto;
mínimo absoluto.
Prioridade

Essencial

[RF05] Calcular Taxas de Crescimento
Descrição

O sistema deverá calcular a taxa de variação do tráfego entre os pontos coletados.

Prioridade

Essencial

[RF06] Realizar Interpolação
Descrição

O sistema deverá estimar valores de tráfego para horários intermediários não registrados durante a coleta.

Prioridade

Importante

[RF07] Detectar Risco de Congestionamento
Descrição

O sistema deverá comparar o maior tráfego registrado com a capacidade máxima da rede e indicar possíveis riscos de saturação.

Prioridade

Essencial

[RF08] Gerar Relatório Executivo
Descrição

O sistema deverá gerar automaticamente um relatório contendo:

utilização máxima;
utilização mínima;
períodos críticos;
alertas;
recomendações gerenciais.
Prioridade

Essencial

[RF09] Emitir Recomendações
Descrição

O sistema deverá apresentar recomendações automáticas com base nos resultados obtidos.

Exemplos:

Avaliar aumento da capacidade do link.

Redistribuir serviços de alto consumo.

Investigar horário de pico.

Prioridade

Importante

Caso de Uso Principal
[CU01] Analisar Tráfego Diário
Ator

Gerente

Pré-condições
Arquivo CSV disponível.
Dados coletados por sistema de monitoramento.
Fluxo Principal
O gerente acessa o sistema.
Seleciona o arquivo CSV.
Solicita a análise.
O sistema processa os dados.
O sistema calcula métricas matemáticas.
O sistema gera o gráfico.
O sistema gera o relatório executivo.
O gerente visualiza os resultados.
Pós-condição

Relatório disponível para consulta.

3.3 Requisitos Não Funcionais
[RNF01] Interface Simples
Descrição

A interface deverá ser simples e intuitiva para usuários sem conhecimento técnico avançado.

Prioridade

Essencial

[RNF02] Aplicação Web
Descrição

O sistema deverá ser desenvolvido utilizando Spring Boot.

Prioridade

Essencial

[RNF03] Tempo de Processamento
Descrição

A análise de um arquivo de tráfego deverá ser concluída em até 5 segundos.

Prioridade

Importante

[RNF04] Responsividade
Descrição

A interface deverá funcionar adequadamente em computadores e notebooks.

Prioridade

Importante

[RNF05] Exportação de Relatórios
Descrição

O sistema deverá permitir exportar o relatório em PDF.

Prioridade

Importante

[RNF06] Facilidade de Evolução
Descrição

A arquitetura deverá permitir a inclusão futura de novos indicadores e métricas.

Prioridade

Desejável

Visão do Produto

O sistema não tem como objetivo substituir ferramentas como Grafana ou Zabbix.

Seu objetivo é atuar como uma camada de análise matemática e geração de conhecimento.

A principal entrega ao usuário é um relatório executivo capaz de responder perguntas como:

Qual foi o pico de utilização da rede?
Existe risco de congestionamento?
Quais horários exigem maior atenção?
A infraestrutura atual continua adequada?
Existe necessidade de expansão futura?

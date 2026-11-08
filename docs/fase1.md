# Milestone 1 — Compreensão e planeamento

## 1. Compreensão do problema
Para este projeto, temos de criar uma aplicação gráfica em Java para modelar e analisar uma rede elétrica. A ideia é representar a rede através de um grafo não orientado: os componentes (geradores, subestações e consumidores) funcionam como os nós, e as linhas elétricas são as arestas que os ligam.

Mais do que apenas desenhar a rede, a aplicação tem de garantir que as regras do enunciado são cumpridas. Além disso, precisamos de implementar funcionalidades para avaliar a robustez da rede (como perceber o impacto da falha de uma linha), editar a topologia visualmente, gravar e carregar o estado em JSON e YAML, e ainda incluir um sistema de *undo* para reverter ações.

## 2. Modelo inicial
A partir da nossa análise, definimos as principais classes que vão compor a rede. Teremos o **Gerador** (que regista a tensão de ligação e a potência máxima produzida), a **Subestação** (com os níveis de tensão suportados e capacidade máxima) e o **Consumidor** (com a tensão necessária e a potência consumida).

Para as ligações, vamos criar a classe **Linha Elétrica**, que guarda características como a tensão nominal, capacidade, comprimento e o seu estado atual (funcional ou avariada).

Toda esta estrutura será gerida por uma classe central (provavelmente chamada **Rede** ou **Grafo**), onde ficará a lógica de validação. É esta classe que vai impedir situações proibidas, como ligar um gerador diretamente a um consumidor ou criar linhas paralelas. Logicamente, todos estes elementos terão um ID único e um nome para facilitar a gestão.

## 3. Plano de trabalho
Tentámos dividir o projeto por fases para facilitar a organização das tarefas:

1. **Configuração inicial:** O primeiro passo será preparar o repositório, integrar a biblioteca *JavaFXSmartGraph* e criar o esqueleto das classes do modelo.
2. **Lógica e Validações:** A seguir, focamo-nos na construção da rede e na implementação das regras de negócio (secção 2.2 do enunciado). Vamos também fazer testes unitários logo nesta fase para garantir que a base está sólida.
3. **Interface Gráfica:** Com a lógica principal a funcionar, avançamos para a UI para conseguir visualizar e editar a rede.
4. **Ficheiros e Undo:** Depois tratamos da importação/exportação em JSON e YAML e implementamos o sistema de *undo*.
5. **Algoritmos (Tarjan):** Deixámos a parte analítica para o fim (pesquisa de caminhos, conectividade e deteção de pontes com o algoritmo de Tarjan), pois vai depender da estabilidade do resto do grafo.

## 4. Dificuldades previstas
Já detetámos alguns pontos onde prevemos perder mais tempo:

* **Interface sem FXML:** Como o enunciado não permite usar FXML, vamos ter de desenhar os menus e ecrãs diretamente em código Java. Como estamos mais habituados a usar o Scene Builder e FXML, isto vai exigir alguma habituação, especialmente na integração com a *JavaFXSmartGraph*.
* **A complexidade do Undo:** Fazer o *undo* da remoção de uma linha é relativamente simples, mas se o utilizador apagar um nó com várias ligações, o *undo* tem de conseguir reconstruir o nó e reconectar todas as arestas exatamente como estavam. Prevemos que esta gestão de estado dê algum trabalho.
* **Algoritmo de Tarjan:** A implementação deste algoritmo para identificar as ligações críticas da rede vai exigir algum estudo da nossa parte. Queremos garantir que funciona de forma eficiente, sem comprometer a performance da aplicação.
* **Tratamento de erros nos ficheiros:** Fazer o *parsing* do JSON e YAML deverá ser tranquilo, mas temos de ter cuidado com as validações. Se o utilizador carregar um ficheiro corrompido ou com dados inválidos, a aplicação tem de detetar o erro sem alterar a rede que já estava em memória.
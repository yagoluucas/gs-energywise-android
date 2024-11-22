# EnergyWise

EnergyWise é um aplicativo móvel desenvolvido em Kotlin que visa gerenciar e monitorar o consumo de energia em comunidades residenciais. O aplicativo permite que os usuários visualizem informações sobre o consumo de energia, estoques de energia disponíveis e façam a gestão de comunidades (condomínios).

## 📱 Funcionalidades

- **Login de Usuário**: Tela de login para autenticação do usuário.
- **Visualização de Comunidades**: Lista de comunidades com informações detalhadas sobre cada uma.
- **Cadastro de Novas Comunidades**: Permite adicionar novas comunidades ao sistema.
- **Atualização de Comunidades**: Atualize informações das comunidades existentes.
- **Perfil do Usuário**: Visualize e edite as informações do perfil do usuário.
- **Navegação Intuitiva**: Barra de navegação inferior para facilitar o acesso às principais seções do aplicativo.

## 🛠 Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação utilizada para desenvolver o aplicativo.
- **Android SDK**: Ferramentas e bibliotecas para o desenvolvimento Android.
- **RecyclerView**: Para exibição de listas de comunidades.
- **Fragments**: Utilizados para a estruturação da interface e navegação.
- **Material Design Components**: Para uma interface moderna e amigável.

## 📂 Estrutura do Projeto

- **activity**: Contém as classes das atividades principais do aplicativo.
  - `MainActivity.kt`: Tela de login.
  - `HomeActivity.kt`: Tela principal com a lista de comunidades.
  - `NovoCondominio.kt`: Tela para cadastro de novas comunidades.
  - `PerfilUsuario.kt`: Tela de perfil do usuário.
  - `AtualizarCondominio.kt`: Tela para atualizar informações de comunidades.
  - `DetalhesCondominio.kt`: Tela com informações detalhadas de uma comunidade.
  - `CriarConta.kt`: Tela para criar uma nova conta de usuário.
- **fragment**: Contém os fragments utilizados no aplicativo.
  - `BottomNavigation.kt`: Gerencia a barra de navegação inferior.
  - `InfoCondominio.kt`: Fragmento com informações detalhadas de uma comunidade.
  - `CloseFragment.kt`: Fragmento para fechar a tela de detalhes de uma comunidade e a tela de atualizar comunidade.
- **model**: Contém as classes modelo.
  - `Usuario.kt`: Modelo de dados para o usuário.
  - `Comunidade.kt`: Modelo de dados para a comunidade.
- **recyclerview**: Contém o adapter para a RecyclerView.
  - `AdapterListaCondominio.kt`: Adapter para exibir a lista de comunidades.
- **utils**: Contém classes utilitárias.
  - `Utils.kt`: Contém funções utilitárias para o aplicativo.

## 🚀 Como Executar o Projeto

1. **Pré-requisitos**:
   - Android Studio instalado.
   - Emulador configurado ou dispositivo Android conectado.

2. **Clonar o Repositório**:
   ```bash
   git clone https://github.com/yagoluucas/gs-energywise-android
   ```

3. **Importar o Projeto no Android Studio**:
   - Abra o Android Studio.
   - Selecione "Open an existing Android Studio project" e navegue até o diretório clonado.

4. **Executar o Aplicativo**:
   - Selecione o dispositivo ou emulador.
   - Clique no botão "Run" ou use o atalho `Shift + F10`.

## 💡 Uso

- **Login**:
  - Abra o aplicativo e insira suas credenciais para fazer login.
- **Navegação**:
  - Utilize a barra de navegação inferior para alternar entre as telas de "Condomínio", "Novo Condomínio" e "Perfil".
- **Visualizar Comunidades**:
  - Na tela principal, visualize a lista de comunidades com informações como nome, quantidade de habitantes, energia consumida, energia em estoque e estoque recomendado.
- **Adicionar Nova Comunidade**:
  - Acesse a tela "Novo Condomínio" e preencha os dados necessários para cadastrar uma nova comunidade.
- **Atualizar Comunidade**:
  - Selecione uma comunidade existente para atualizar suas informações.

## 📷 Protótipo

A prototipagem foi feita utilizando a ferramenta FIGMA.  

[Link do Protótipo](https://www.figma.com/files/team/1436703605828537457/project/299832420/Team-project?fuid=1275982344735340604)

## 🎥 Apresentação do App
Apresentamos o nosso app mostrando todas as funcionalidades dele.

[Link do vídeo](https://www.youtube.com/watch?v=YsShtt6mVTE)

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.


## 📞 Contato

**Maurício Pereira**.
- Email: [Gmail](mailto:mauricio.pvieira1@gmail.com)
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/mauriciovpereira/)

**Yago Lucas Gonçalves**.  
- Email: [Gmail](mailto:yago543@gmail.com)  
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/yago-lucas-silva/)

**Luiz Otávio Leitão**.
- Email: [Gmail](mailto:luizotavio.ok@gmail.com)
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/luizotavioleitaosilva/)

---

*Este README foi gerado para a parte móvel do projeto EnergyWise, focando nas funcionalidades e estruturas relevantes ao aplicativo Android desenvolvido em Kotlin.*

# Mupen64Plus-AE (Versão Non-SAF e Retro-Compatível)

Este é um fork do **Mupen64Plus, Android Edition (AE)**, focado em restaurar a facilidade de uso com frontends e garantir compatibilidade com dispositivos Android legados.

## Objetivos do Projeto

Este projeto foi modificado com os seguintes objetivos principais:

1.  **Independência de SAF (Storage Access Framework)**: Removida a obrigatoriedade de uso do SAF, permitindo que frontends e o próprio emulador acessem os arquivos de ROMs de forma direta através das permissões padrão de armazenamento, facilitando a integração com aplicativos externos de gerenciamento de jogos.
2.  **Compatibilidade Retroativa**: O nível mínimo de SDK foi reduzido para **Android 5.0 (API 21)**, permitindo que dispositivos mais antigos voltem a rodar a versão mais recente do emulador.
3.  **Modernização do Build**: O projeto foi migrado para utilizar o **JDK 17**, alinhando-se com as ferramentas modernas de desenvolvimento Android (AGP 8+), mantendo o desugaring para compatibilidade com versões anteriores.
4.  **Assinatura Completa**: Builds gerados com suporte a assinaturas **V1, V2 e V3**, garantindo que o APK possa ser instalado em qualquer versão do Android suportada.
5.  **Tradução Integral**: Sincronização e complementação das traduções para **Português (PT)**, **Português Brasileiro (PT-BR)** e **Espanhol (ES)**, garantindo que todas as strings do projeto original estejam devidamente localizadas.
6.  **Compatibilidade Vulkan (Bypass de Tela Preta)**: Refatoração da linkagem EGL/GLES para permitir a inicialização de plugins modernos (como o ParaLLEl) em dispositivos que exigem gestão estrita da `ANativeWindow`, eliminando o erro de "tela preta com som".
7.  **Multijogador Automatizado (Plug-and-Play)**: Ativação por padrão do mapeamento automático de controles com lógica de atribuição exclusiva. O emulador agora diferencia múltiplos controles (mesmo de modelos idênticos) e os distribui automaticamente pelos slots P1, P2, P3 e P4. A reconexão foi aprimorada para preservar a ordem dos jogadores sem intervenção manual.
8.  **Experiência de Console Simplificada**: Remoção de distrações para uso em arcades e frontends. O sistema agora força o início dos jogos sempre pela tela de título (desativando o auto-resume), desabilita o download automático de capas e **remove todas as notificações da barra de status**. O comportamento de suspensão padrão do Android é mantido (permitindo minimizar o jogo), mas o encerramento total ocorre apenas quando o aplicativo é fechado manualmente pelo usuário ou pelo sistema, garantindo uma inicialização limpa a cada novo carregamento.
9.  **Confirmação de Saída**: Implementação de um diálogo de confirmação ao pressionar o botão "Voltar" na tela inicial. Isso evita o fechamento acidental do emulador e garante que o usuário realmente deseja encerrar a aplicação, seguindo o padrão de interface de consoles dedicados.
10. **Estabilidade com Frontends**: Otimização do ciclo de vida das atividades e ajuste do modo de lançamento das janelas (`launchMode`). Isso resolve o fechamento repentino do aplicativo ao ser iniciado por frontends externos (como DIG, Pegasus ou LaunchBox) em determinados dispositivos, garantindo uma transição estável entre o launcher e o emulador.

## Repositório Oficial

Para acompanhar as atualizações, reportar problemas ou baixar as builds mais recentes, acesse:
[https://github.com/dougretrogames/mupen64plus-ae-nonsaf](https://github.com/dougretrogames/mupen64plus-ae-nonsaf)

---

## Instruções de Build

1.  **Pré-requisitos**:
    *   [Android Studio](https://developer.android.com/studio/index.html) (Versão Iguana ou superior recomendada).
    *   JDK 17 configurado.
    *   Android SDK e NDK (versão 26.1.10909125 especificada no projeto).

2.  **Clonagem**:
    ```bash
    git clone https://github.com/dougretrogames/mupen64plus-ae-nonsaf.git
    ```

3.  **Compilação**:
    *   Abra o projeto no Android Studio.
    *   Certifique-se de que o Gradle JDK está definido como JDK 17 em *Settings > Build Tools > Gradle*.
    *   Para gerar o APK assinado para distribuição, utilize a tarefa `assembleRelease`.

## Licença

Este projeto mantém a licença original GPL. Veja o arquivo `gpl-license` para mais detalhes.

---
*Nota: Este é um projeto focado na comunidade retro e em preservação de hardware antigo.*

<script setup>
import FormsRegistro from './registro/FormsRegistro.vue';

</script>

<template>
    <div class="workbench">
        <!-- Coluna da Esquerda (Menu) -->
        <div class="opcoes">
            <button class="btn-opcao" title="Fazer Novo Registro de Vacinação">
                <img src="../../../assets/prancheta_branca_256x256_pc.png" alt="">
                <span>Novo Registro</span>
            </button>
            <button class="btn-opcao" title="Cadastra um Novo Paciente">
                <img src="../../../assets/paciente_branco_256x256_pc.png" alt="">
                <span>Cadastrar Paciente</span>
            </button>
            <button class="btn-opcao">
                <img src="../../../assets/lupa_branca_256x256_pc.png" alt="">
                <span>Consultas</span>
            </button>
        </div>
        
        <!-- Área principal à direita (Branca) -->
        <div class="conteudo-principal">
            <!-- components vão aqui -->
            <FormsRegistro></FormsRegistro>
        </div>
    </div>
</template>

<style scoped>
.workbench {
    /* Layout do container */
    display: flex;
    flex-direction: row;
    gap: 24px; /* Espaço entre o menu e a área branca */
    padding: 24px; 
    
    /* Tamanho: 90% da largura e o máximo de altura que puder sem vazar */
    width: 90%;
    /* Pode ajustar esse height se quiser mais ou menos espaço na tela */
    height: 70%; 
    
    /* Visual */
    background: #5D8DD4;
    border-radius: 16px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
}

.opcoes {
    display: flex;
    flex-direction: column;
    gap: 16px; /* Espaço entre os botões */
    width: 300px; /* Largura fixa para os botões não encolherem demais */
}

.btn-opcao {
    /* A mágica acontece aqui: 'flex: 1' faz os 3 botões esticarem 
       igualmente preenchendo toda a altura da div .opcoes */
    flex: 1; 
    
    display: flex;
    flex-direction: column; 
    align-items: center;
    justify-content: center;
    gap: 12px;
    
    /* Visual do Botão */
    background: #81CAFF;
    border-radius: 12px;
    border: none;
    color: #FFFFFF;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: background 0.3s ease, transform 0.2s ease;
}

.btn-opcao:hover {
    background: #6ebbf0; /* Fica um pouco mais escuro ao passar o mouse */
    transform: translateY(-2px);
}

.btn-opcao:active {
    transform: translateY(0) scale(0.98);
}

.btn-opcao img {
    width: 40px; 
    height: 40px;
    object-fit: contain;
}

.conteudo-principal {
    /* 'flex: 1' aqui faz a área branca ocupar todo o resto da largura à direita */
    flex: 1; 
    background: #81CAFF;
    border-radius: 12px;
    padding: 24px;
    
    /* Opcional: Adiciona scroll interno caso o componente 
       filho seja muito grande, evitando que o layout quebre */
    overflow-y: auto; 
}
.conteudo-principal::-webkit-scrollbar{
width: 10px;
}
/* Track (fundo da scrollbar) — remove o branco */
.conteudo-principal::-webkit-scrollbar-track {
  background: transparent;  
}
.conteudo-principal::-webkit-scrollbar-thumb {
  background: #4A76B8;
  border-radius: 5px;
}
.conteudo-principal::-webkit-scrollbar-thumb:hover {
  background: #38598a;
}
</style>
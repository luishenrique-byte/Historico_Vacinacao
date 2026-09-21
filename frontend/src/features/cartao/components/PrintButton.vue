<script setup>
// Lógica do componente (ex: função para imprimir) pode vir aqui no futuro
function imprimir(){
    window.print();
}
</script>

<template>
    <!-- Trocado de span para button para acessibilidade e semântica -->
    <button 
        class="botao-imprimir" 
        title="Imprimir" 
        aria-label="Imprimir informações"
        @click="imprimir()"
    >
        <img class="img-impressora" src="../../assets/impressora_100x100_pc.png" alt="Ícone de impressora">
    </button>
</template>

<style scoped>
.botao-imprimir {
    position: absolute;
    bottom: 24px; /* Afastado um pouco mais da borda */
    right: 24px;
    display: flex;
    width: 60px; /* Tamanho maior para área de clique confortável */
    height: 60px;
    justify-content: center;
    align-items: center;
    border-radius: 50%; /* 50% garante um círculo perfeito sempre */
    background: #1A365D;
    border: none; /* Remove borda padrão de botões */
    cursor: pointer; /* Muda o cursor para a 'mãozinha' */
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.25); /* Sombra inicial suave */
    transition: all 0.3s ease; /* Deixa as mudanças de estado suaves */
    z-index: 10; /* Garante que fique por cima de outros conteúdos */
}

/* Efeito ao passar o mouse por cima (Hover) */
.botao-imprimir:hover {
    background: #254b82; /* Azul sutilmente mais claro */
    transform: translateY(-4px); /* Levanta o botão */
    box-shadow: 0 8px 15px rgba(0, 0, 0, 0.3); /* Sombra maior para dar profundidade */
}

/* Efeito ao clicar (Active) */
.botao-imprimir:active {
    transform: translateY(0) scale(0.95); /* Dá um efeito de "pressionar" */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.img-impressora {
    width: 28px; /* Tamanho proporcional para não colar nas bordas do botão */
    height: 28px;
    object-fit: contain;
    transition: transform 0.3s ease;
    
    /* DICA: Se a sua imagem original (impressora_100x100_pc.png) for escura/preta,
       descomente a linha abaixo para transformá-la magicamente em branca 
       e dar contraste com o fundo azul escuro do botão! */
    /* filter: brightness(0) invert(1); */
}

/* Dá um pequeno 'zoom' apenas no ícone quando o mouse passa no botão */
.botao-imprimir:hover .img-impressora {
    transform: scale(1.1);
}

/* PrintButton css */
@media print {
    .botao-imprimir{
    display: none;
  }
}
</style>
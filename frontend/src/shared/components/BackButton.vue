<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router';

const router = useRouter()

const props = defineProps({
    contraste:{
        type: Boolean,
        default: undefined
    }
})

const classTema = computed(() =>
{
    if(props.contraste != undefined){
        return ((props.contraste === true) ? 'tema-azul' : 'tema-branco');
    } else {
        return 'tema-padrao';
    }
})


function voltar(){
    router.back()
}
</script>

<template>
    <!-- Trocado de span para button para acessibilidade e semântica -->
    <button 
        class="botao-voltar"
        :class="classTema" 
        title="Voltar" 
        aria-label="Voltar Página"
        @click="voltar()"
    >

        <span class="img-seta" role="img" aria-label="Ícone de voltar"></span>
    </button>
</template>

<style scoped>
.botao-voltar {
    position: absolute;
    bottom: 24px; /* Afastado um pouco mais da borda */
    left: 24px;
    display: flex;
    width: 60px; /* Tamanho maior para área de clique confortável */
    height: 60px;
    justify-content: center;
    align-items: center;
    border-radius: 50%; /* 50% garante um círculo perfeito sempre */
    border: none; /* Remove borda padrão de botões */
    cursor: pointer; /* Muda o cursor para a 'mãozinha' */
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.25); /* Sombra inicial suave */
    transition: all 0.3s ease; /* Deixa as mudanças de estado suaves */
    z-index: 10; /* Garante que fique por cima de outros conteúdos */
}

/* Efeito ao passar o mouse por cima (Hover) */
.botao-voltar:hover {
    transform: translateY(-4px); /* Levanta o botão */
    box-shadow: 0 8px 15px rgba(0, 0, 0, 0.3); /* Sombra maior para dar profundidade */
}

/* Efeito ao clicar (Active) */
.botao-voltar:active {
    transform: translateY(0) scale(0.95); /* Dá um efeito de "pressionar" */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.img-seta {
    width: 28px; 
    height: 28px;
    transition: transform 0.3s ease; 
    
    -webkit-mask-image: url('../../assets/seta_esquerda_branca_100x100_pc.png');
    -webkit-mask-size: contain;
    -webkit-mask-repeat: no-repeat;
    -webkit-mask-position: center;
    
    mask-image: url('../../assets/seta_esquerda_branca_100x100_pc.png');
    mask-size: contain;
    mask-repeat: no-repeat;
    mask-position: center;
}

/* Dá um pequeno 'zoom' apenas no ícone quando o mouse passa no botão */
.botao-voltar:hover .img-seta {
    transform: scale(1.1);
}

/* 1. TEMA AZUL (Botão Azul Escuro, Seta Branca) */
.botao-voltar.tema-azul { background: #1A365D; }
.botao-voltar.tema-azul:hover { background: #254b82; }
.botao-voltar.tema-azul .img-seta { background-color: #FFFFFF; } /* Pinta a seta de branco */

/* 2. TEMA BRANCO (Botão Branco, Seta Azul) */
.botao-voltar.tema-branco { background: #FFFFFF; }
.botao-voltar.tema-branco:hover { background: #e6e6e6; }
.botao-voltar.tema-branco .img-seta { background-color: #5D8DD4; } /* Pinta a seta de azul */

/* 3. TEMA PADRÃO (Quando o componente não recebe a prop 'contraste') */
.botao-voltar.tema-padrao { background: #d0d0d2; } /* Exemplo: Fundo Cinza */
.botao-voltar.tema-padrao:hover { background: #b0b0b2; }
.botao-voltar.tema-padrao .img-seta { background-color: #333333; } /* Seta escura */


@media print {
    .botao-voltar{
    display: none;
  }
}
</style>
<script setup>
import { findAllByPaciente } from '@/shared/services/registroServices';
import { onMounted, ref } from 'vue';

const idPaciente = localStorage.getItem('paciente-id')

const listRegistros = ref([])

async function getListRegistros() {
    const response = await findAllByPaciente(idPaciente)
    listRegistros.value = response.data
}

onMounted(() => {
    getListRegistros()
})
</script>
<template>
    <div class="card-vaccine">
        <div class="lista-header">
            <p class="item-grid header-text">Vacina</p>
            <p class="item-grid header-text">Data de Vacinação</p>
            <p class="item-grid header-text">Lote</p>
            <p class="item-grid header-text">Data Fabricação</p>
            <p class="item-grid header-text">Profissional</p>
            <p class="item-grid header-text">Unidade</p>
            <p class="item-grid header-text">Data Próxima Vacina</p>
        </div>
        <div class="conteiner-registros" v-if="listRegistros.length > 0">
            <div 
            v-for="registro in listRegistros"
            class="registro">
                <div class="item-grid">{{ registro.vacina.nome }}</div>
                <div class="item-grid">{{ new Date(registro.dataVacinacao).toLocaleDateString('pt-BR') }}</div>
                <div class="item-grid">{{ registro.lote }}</div>
                <div class="item-grid">{{ new Date(registro.dataFabricacao).toLocaleDateString('pt-BR') }}</div>        
                <div class="item-grid">{{ registro.profissional.nome }}</div>
                <div class="item-grid">{{ registro.unidade.nome }}</div>
                <div class="item-grid">{{ new Date(registro.validade).toLocaleDateString('pt-BR') }}</div>
            </div>
        </div>
    </div>
</template>
<style scoped>
/* Container Principal - Sombra para destacar do fundo */
.card-vaccine {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 16px; /* Aumentei um pouco o gap */
    padding: 20px; /* Mais respiro interno */
    border-radius: 16px; /* Cantos mais arredondados */
    background: #5D8DD4;
    height: 70%;
    width: 90%;
    /* Adicionada uma sombra suave para dar profundidade */
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15), 0 4px 10px rgba(0, 0, 0, 0.05);
    /* Se o fundo principal da tela for escuro/azul, uma borda sutil ajuda */
    border: 1px solid rgba(255, 255, 255, 0.1); 
}

/* Base comum para Grid do Header e dos Registros */
.lista-header,
.registro {
    display: grid;
    grid-template-columns: repeat(7, minmax(0, 1fr));
    align-items: center;
    padding: 15px 0px;
    width: 100%;
}

/* Estilo específico do Cabeçalho */
.lista-header {
    background: #4A76B8; /* Cor um pouco mais escura que o fundo para contrastar */
    border-radius: 12px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    color: white;
}

/* Estilo do container que segura todas as linhas */
.conteiner-registros {
    display: flex;
    flex-direction: column;
    width: 100%;
    background: #9BBDEF;
    border-radius: 12px;
    overflow: hidden; /* Garante que os filhos não vazem dos cantos arredondados */
    box-shadow: inset 0 2px 4px rgba(0,0,0,0.05); /* Sombra interna sutil */
    overflow-y: auto; /* Gera scroll vertical apenas se o conteúdo exceder a altura */
}
.conteiner-registros::-webkit-scrollbar{
    width: 10px;
}
/* Track (fundo da scrollbar) — remove o branco */
.conteiner-registros::-webkit-scrollbar-track {
  background: transparent;  
}
.conteiner-registros::-webkit-scrollbar-thumb {
  background: #4A76B8;
  border-radius: 5px;
}
.conteiner-registros::-webkit-scrollbar-thumb:hover {
  background: #38598a;
}

/* Estilo individual de cada linha */
.registro {
    position: relative; /* Necessário para o ::after funcionar corretamente */
    background: transparent;
    transition: background-color 0.2s ease;
    color: #1A365D; /* Cor de texto mais escura para boa leitura */
}

/* Efeito hover (opcional, mas recomendado) */
.registro:hover {
    background: rgba(255, 255, 255, 0.3);
}

/* A mágica do ::after para criar a linha divisória */
.registro::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 2%; /* A linha não encosta totalmente nas bordas */
    width: 96%;
    height: 1px;
    background: rgba(255, 255, 255, 0.4); /* Linha semi-transparente clara */
    /* Você pode usar um gradiente na linha se preferir: */
    /* background: linear-gradient(90deg, transparent, rgba(255,255,255,0.5), transparent); */
}

/* Remove a linha divisória do último elemento da lista */
.registro:last-child::after {
    display: none;
}

/* Textos dentro do grid */
.item-grid {
    text-align: center;
    font-size: 0.9rem;
    font-weight: 500;
}

/* Destaque para o texto do header */
.header-text {
    font-weight: 700;
    font-size: 0.95rem;
    text-transform: uppercase;
    letter-spacing: 0.5px;
}
</style>
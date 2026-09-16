<script setup>
import { computed, onMounted, ref } from 'vue';
import { findById } from '../../shared/services/pacienteServices'

const cpf = localStorage.getItem('paciente-CPF')
const id = localStorage.getItem('paciente-id')
const paciente = ref(null);

onMounted(async () => {
  const responsePac = await findById(id)
  paciente.value = responsePac.data
  console.log(paciente.value)
  new Date(paciente.value.dataNascimento).toLocaleDateString('pt-BR')
})

const dataNascimentoFormatada = computed(() => {
  if(!paciente.value) return ''
  return new Date(paciente.value.dataNascimento).toLocaleDateString('pt-BR')
})

</script>
<!-- HTML -->
<template>
  <header class="header" v-if="paciente">
    
    <!-- Grupo da Esquerda: Informações Principais -->
    <div class="info-group">
      <div class="info-block">
        <span class="label">Paciente</span>
        <span class="value nome">{{paciente.nome}}</span>
      </div>

      <div class="info-block">
        <span class="label">Nascimento</span>
        <span class="value">{{dataNascimentoFormatada}}</span>
      </div>

      <div class="info-block">
        <span class="label">CPF</span>
        <span class="value">{{ cpf }}</span>
      </div>
    </div>

    <!-- Grupo da Direita: ID do Paciente -->
    <div class="id-badge">
      <span class="label">ID Registro</span>
      <span class="value highlight">#{{ paciente.id }}</span>
    </div>

  </header>
</template>

<style scoped>
.header {
  width: 90%;
  display: flex;
  flex-direction: row;
  justify-content: space-between; /* Empurra ID para direita e infos para esquerda */
  align-items: center;
  
  padding: 16px 25px;
  background-color: #ffffff;
  border-radius: 12px;
  
  /* Sombra suave e moderna, sem bordas marcadas */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08), 0 1px 3px rgba(0, 0, 0, 0.04);
  /* Uma borda muito sutil apenas para definir o limite se o fundo for branco */
  border: 1px solid rgba(0, 0, 0, 0.05); 
  
  /* Garante que o texto base tenha uma cor boa para leitura */
  color: #1A365D; 
}

.info-group {
  display: flex;
  flex-direction: row;
  gap: 32px; /* Espaço uniforme entre as informações */
  flex-wrap: wrap; /* Permite quebrar linha em telas muito pequenas */
}

.info-block {
  display: flex;
  flex-direction: column;
  gap: 4px; /* Espacinho entre título e valor */
}

.label {
  font-size: 0.75rem; /* 12px */
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  color: #6B7280; /* Cinza médio, discreto */
}

.value {
  font-size: 1rem; /* 16px */
  font-weight: 500;
  color: #111827; /* Quase preto, alto contraste */
}

/* Destaque específico para o nome, se quiser que ele seja maior */
.value.nome {
  font-weight: 700;
  text-transform: uppercase;
  color: #1A365D; /* Cor temática (azul escuro) */
}

.id-badge {
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* Alinha texto à direita */
  background: #F3F4F6; /* Fundo cinza bem clarinho para destacar */
  padding: 8px 12px;
  border-radius: 8px;
}

.id-badge .label {
  font-size: 0.7rem;
}

.id-badge .value.highlight {
  font-family: monospace; /* Fonte monoespaçada para números fica legal */
  font-size: 1.1rem;
  font-weight: 700;
  color: #4A76B8; /* Azul do seu tema */
}

/* Ajustes para telas menores (ex: celulares em pé) */
@media (max-width: 768px) {
  .header {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
  }
  
  .id-badge {
    align-items: flex-start; /* Volta a alinhar a esquerda no mobile */
    align-self: stretch; /* Ocupa a largura toda */
  }
}
</style>

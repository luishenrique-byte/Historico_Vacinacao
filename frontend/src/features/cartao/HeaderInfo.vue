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
    <span class="info">
      <p class="label">Nome do Paciente:</p>
      <p class="value">{{paciente.nome}}</p>
    </span>

    <span class="info">
      <p class="label">Data de Nascimento</p>
      <p class="value">{{dataNascimentoFormatada}}</p>
    </span>

    <span class="info">
      <p class="label">CPF:</p>
      <p class="value">{{ cpf }}</p>
    </span>
    <p id="id-paciente">ID do Pacinete: {{ paciente.id }}</p>
  </header>
</template>

<!-- CSS -->
<style scoped>
.header {
  height: 10%;
  width: 90%;
  display: flex;
  flex-direction: row;
  justify-content: start;
  align-items: center;
  gap: 10%;
  position: relative;
  border-radius: 12px;
  border-top: 1px solid rgba(0, 0, 0, 0.25);
  border-left: 1px solid rgba(0, 0, 0, 0.25);
  background: #fff;
  box-shadow: 8px 8px 4px 0 rgba(0, 0, 0, 0.25);
  padding: 10px;
}

.info {
  display: flex;
  flex-direction: column;
}

.label {
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.value {
  text-transform: uppercase;
  margin: 0px 0px 0px 10px;
}

#id-paciente {
  position: absolute;
  top: 10px;
  right: 10px;
}
</style>

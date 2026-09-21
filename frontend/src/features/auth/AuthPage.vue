<!-- SCRIPT -->
<script setup>
import { useError } from '../../shared/composables/useError'
import { useRoute, useRouter } from 'vue-router'
import { onMounted, ref } from 'vue'
import { findByCpf as findPacienteByCpf } from '../../shared/services/pacienteServices'
import { filterAtivo } from '../../shared/services/profissionalServices'
import InputText from '@/shared/components/InputText.vue'
import imgEnfermeira from '../../assets/enfermeira_256x256_pc.png'
import imgPaciente from '../../assets/paciente_256x256_pc.png'
import SelectPatient from './components/SelectPatient.vue'


const route = useRoute()

const router = useRouter()

const { showError } = useError()

onMounted(() => {
  localStorage.clear();
})

// route.params.tipo
const userType = route.params.tipo //Recebe o tipo atráves da rota

if (userType !== 'paciente' && userType !== 'profissional') {
  // Exibir uma mensagem de erro
  console.error('Tipo de usuário inválido:', userType)

  // Redirecionar para a página inicial
  router.push('/')
}

const userImg = userType === 'paciente' ? imgPaciente : imgEnfermeira
const userLabel = userType === 'paciente' ? 'Paciente' : 'Profissional'
const userButtonLabel = userType === 'paciente' ? 'Cartão de Vacina' : 'WorkFlow'

const mostrarModal = ref(false)
const listPatientByCPF = ref([])
const idPatient = ref(null)
const cpf = ref('')

function login() {
  if (userType === 'paciente') {
    authPatient();
  } else if (userType === 'profissional') {
    authProfissional();
  }
}

async function authProfissional() {
  try{    
    const profissional = (await filterAtivo(cpf.value)).data
    
    localStorage.setItem('tipo', 'profissional');
    localStorage.setItem('cpf', cpf.value);
    localStorage.setItem('id', profissional.id);
    router.push('/profissional/workbench');
  
  } catch(e){
    showError(e.response.data.mensagem);
  }
}

async function authPatient(){
  try {
      listPatientByCPF.value = (await findPacienteByCpf(cpf.value)).data
    
      if(!isIdPatientSet()){
        if(listPatientByCPF.value.length > 1){
          mostrarModal.value = true;
          return;
        }
      }

      localStorage.setItem('tipo', 'paciente');
      localStorage.setItem('cpf', cpf.value);
      localStorage.setItem('id',idPatient.value);
      router.push('/paciente/vaccine');
    } catch (e) {
      showError(e.response.data.mensagem);
    }
}
function isIdPatientSet(){
  if(idPatient.value != null){ 
    return true;
  } else {
    return false;
  }
}
function finalizarSelectPatient(idRecebido){
  idPatient.value = idRecebido;
  mostrarModal.value = false;

  authPatient();
}

</script>
<!-- HTML -->
<template>
  <span class="card">
    <img class="img" draggable="false" :src="userImg" />

    <span class="label-dinamica">
      Informe o CPF do
      <p>
        {{ userLabel }}
      </p>
    </span>

    <InputText label="CPF" v-model="cpf"></InputText>

    <button class="btn-acess" @click="login">Acessar {{ userButtonLabel }}</button>
  </span>

  <SelectPatient 
  v-if="mostrarModal" 
  :list-patient="listPatientByCPF"
  @select-patient="finalizarSelectPatient($event)"
  
  ></SelectPatient>
</template>

<!-- STYLE -->
<style scoped>
.card {
  display: flex;
  flex-direction: column;
  gap: 6%;
  align-items: center;
  justify-content: center;
  width: 300px;
  height: 300px;
  padding: 20px;
  border-radius: 15px;
  background: #fff;
  box-shadow: 6px 4px 9px 0 rgba(0, 0, 0, 0.25);
}

.img {
  height: 30%;
  width: auto;
  margin-bottom: 20px;
}

.label-dinamica {
  display: flex;
  flex-direction: row;
  gap: 5px;
}

.btn-acess {
  padding: 10px 20px;
  background: #5d8dd4;
  color: white;
  border: none;
  border-radius: 5px;
  transition: 0.3s ease;
}
.btn-acess:hover {
  transform: translateY(-5px);
  cursor: pointer;
}
</style>
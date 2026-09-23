<script setup>
import { save } from '@/shared/services/registroServices';
import { findAll as findAllUnidade} from '@/shared/services/unidadeServices';
import { findAll as findAllVacina, findById as findVacinaById} from '@/shared/services/vacinaServices';
import { onMounted, reactive, ref } from 'vue';

let listVacina = ref([]);
let listUnidade = ref([]);

let registro = reactive({
    lote: "",
    dataVacinacao: "",
    dataFabricacao: "",
    validade: "",
    idPaciente: "",
    idProfissional: "",
    idUnidade: "",
    idVacina: ""
})

async function salvarRegistro(){
    await calcularValidadeRegistro()
    await save(registro)
}

async function calcularValidadeRegistro(){
    const vacina = (await findVacinaById(registro.idVacina)).data;
    
    const dataVacinacao = new Date(registro.dataVacinacao);

    dataVacinacao.setDate(dataVacinacao.getDate() + vacina.intervaloDoses)

    registro.validade = dataVacinacao.toISOString().split('T')[0]

}
async function carregarVacinas() {
    listVacina.value = (await findAllVacina()).data;
}

async function carregasUnidades() {
    listUnidade.value = (await findAllUnidade()).data;
}

onMounted(() =>{
    carregarVacinas();
    carregasUnidades();
})
</script>
<template>
    <div class="registro-container">
        <h2 class="titulo">Novo Registro</h2>

        <div class="form-grid">
            <!-- Linha 1 -->
            <div class="input-group">
                <label for="vacina">Vacina:</label>
                <select id="vacina" class="input-field" v-model="registro.idVacina">
                    <option value="" disabled selected>Selecione a vacina</option>
                    <option v-for="vacina in listVacina" :value='vacina.id'>{{ vacina.nome }}</option>
                </select>
            </div>

            <div class="input-group">
                <label for="data-vacinacao">Data da Vacinação</label>
                <div class="input-with-icon">
                    <input type="date" v-model="registro.dataVacinacao" id="data-vacinacao" class="input-field" placeholder="00/00/0000">
                </div>
            </div>

            <!-- Linha 2 -->
            <div class="input-group">
                <label for="lote">Lote:</label>
                <input type="text" v-model="registro.lote" id="lote" class="input-field" placeholder="Insira o Lote aqui">
            </div>

            <div class="input-group">
                <label for="data-fabricacao">Data da Fabricacao</label>
                <div class="input-with-icon">
                    <input type="date" v-model="registro.dataFabricacao" id="data-fabricacao" class="input-field" placeholder="00/00/0000">
                </div>
            </div>

            <!-- Linha 3 -->
            <div class="input-group">
                <label for="unidade">Código da Unidade:</label>
                <select id="unidade" class="input-field" v-model="registro.idUnidade">
                    <option value="" disabled selected>Selecione a Unidade</option>
                    <option v-for="unidade in listUnidade" :value='unidade.id'>{{ unidade.id + " - " +unidade.nome }}</option>
                </select>
            </div>
            <!-- Div vazia para manter o grid alinhado (já que o Código da Unidade fica sozinho na esquerda) -->
            <div class="input-group"></div>

            <!-- Linha 4 -->
            <div class="input-group">
                <label for="codigo-paciente">Código do Paciente:</label>
                <input type="text" v-model="registro.idPaciente" id="codigo-paciente" class="input-field"
                    placeholder="Insira o código do paciente aqui">
            </div>

            <div class="input-group">
                <label for="codigo-prestador">Código do Prestador:</label>
                <input type="text" v-model="registro.idProfissional" id="codigo-prestador" class="input-field"
                    placeholder="Insira o código do prestador aqui">
            </div>
        </div>

        <div class="form-actions" >
            <button  class="btn-enviar" @click="salvarRegistro()" >Salvar Registro</button>
        </div>
    </div>
</template>

<style scoped>
.registro-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    /* Centraliza o conteúdo dentro da div flex: 1 do pai */
    width: 100%;
    height: auto;
    padding: 20px;
    box-sizing: border-box;
    background-color: #FFFFFF;
    border-radius: 15px;
}

.titulo {
    color: #333;
    font-size: 1.2rem;
    font-weight: 500;
    margin-bottom: 40px;
}

.form-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    /* Cria as duas colunas do design */
    column-gap: 60px;
    /* Espaço horizontal entre as colunas */
    row-gap: 40px;
    /* Espaço vertical entre as linhas */
    width: 100%;
    max-width: 800px;
    /* Evita que o formulário estique demais em telas muito largas */
}

.input-group {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

label {
    font-size: 0.95rem;
    color: #1a1a1a;
    font-weight: 500;
}

.input-field {
    width: 100%;
    border: none;
    border-bottom: 1px solid #999;
    /* Estilo 'material design' com apenas a linha inferior */
    background: transparent;
    padding: 8px 0;
    font-size: 0.95rem;
    color: #333;
    outline: none;
    transition: border-color 0.3s ease;
}

/* Remove a seta padrão do select em alguns navegadores para ficar mais limpo */
select.input-field {
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%23333' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
    background-repeat: no-repeat;
    background-position: right center;
    background-size: 16px;
    cursor: pointer;
}

.input-field::placeholder,
.input-with-icon::placeholder,
select.input-field:invalid {
    color: #a0a0a0;
    /* Cor cinza clara para os placeholders */
}

.input-field:focus {
    border-bottom-color: #5D8DD4;
    /* Fica azul ao focar no campo */
}

/* ========================================= */
/* ESTILOS DO BOTÃO DE ENVIO                 */
/* ========================================= */
.form-actions {
  display: flex;
  justify-content: flex-end; /* Empurra o botão para a direita */
  width: 100%;
  max-width: 800px; /* Mantém o alinhamento com a largura máxima do grid */
  margin-top: 40px; 
}

.btn-enviar {
  background-color: #5D8DD4;
  color: #FFFFFF;
  border: none;
  border-radius: 8px;
  padding: 12px 32px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.btn-enviar:hover {
  background-color: #4a75b3; /* Azul um pouco mais escuro */
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.btn-enviar:active {
  transform: translateY(0) scale(0.98);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
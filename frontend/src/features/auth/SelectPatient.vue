<script setup>
    const props = defineProps({
        listPatient:{
            type: Array,
            default: () => [] // Boa prática: retornar array vazio como default
        },
    })

    const emit = defineEmits(['selectPatient'])
    
    function selectPatient(id){
        return emit('selectPatient',id)
    }

    function getIniciais(nome) {
        return nome.split(' ').map(p => p[0]).join('').slice(0, 2).toUpperCase()
    }

    function getDataNascimentoFormatada(date){
        return new Date(date).toLocaleDateString('pt-BR');
    }
</script>
<template>
    <div class="background">
        <div class="container-opcao">
            <div class="header-texto">
                <h3>Foi indentificado {{ listPatient.length }} pacientes vinculados a esse CPF.</h3>
                <h4>Por favor, selecione uma das opções abaixo</h4>
            </div>
            <div class="lista-pacientes">
                <span
                class="opcao-paciente"
                v-for="patient in listPatient"
                :class="{ 'selecionado': idPatient === patient.id }"
                key="id"
                @click="selectPatient(patient.id)">
                    <span class="iniciais-nome">
                        {{getIniciais(patient.nome)}}
                    </span>
                    <span class="info-paciente">
                        <p>{{ patient.nome }}</p>
                        <p class="data-paciente">{{ getDataNascimentoFormatada(patient.dataNascimento)}}</p>
                    </span> 
                    <p class="id-paciente">#{{ patient.id }}</p>
                </span>
            </div>
        </div>
    </div>
</template>
<style scoped>
 .background {
    position: fixed; /* Fixed é melhor que absolute para modais */
    top: 0;
    left: 0;
    backdrop-filter: blur(4px);
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.4); /* Fundo um pouco mais escuro para destacar o modal */
    z-index: 1000;
}

/* Modal principal com cantos arredondados */
.container-opcao {
    display: flex;
    flex-direction: column;
    width: 90%;
    max-width: 480px;
    max-height: 80vh; /* Limita a altura máxima para não passar da tela */
    background-color: #ffffff; /* Fundo branco deixa os botões mais visíveis */
    border-radius: 24px; /* Pontas bem arredondadas no container */
    padding: 32px 24px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
    text-align: center;
}

.header-texto h3 {
    margin: 0 0 8px 0;
    color: #1f2937;
    font-size: 1.15rem;
}

.header-texto h4 {
    margin: 0 0 24px 0;
    color: #6b7280;
    font-weight: 400;
    font-size: 0.95rem;
}

/* Container da lista com scroll automático */
.lista-pacientes {
    display: flex;
    flex-direction: column;
    gap: 12px;
    overflow-y: auto; /* Adiciona barra de rolagem se houver muitos itens */
    padding: 4px; /* Espaço para o box-shadow do hover não cortar */
}

/* Estilo das opções (Filosofia de pontas redondas) */
.opcao-paciente {
    background-color: #f3f4f6;
    color: #374151;
    padding: 16px 24px;
    border-radius: 999px; /* Efeito "Pílula" (Pill-shape) */
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s ease;
    border: 2px solid transparent;
    display: flex;
    gap: 10px;
    flex-direction: row;
    justify-content: start;
    align-items: center;
}

/* Efeito ao passar o mouse */
.opcao-paciente:hover {
    background-color: #e5e7eb;
    transform: translateY(-2px); /* Dá uma leve "levantada" no botão */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

/* Estilo para quando a opção estiver selecionada */
.opcao-paciente.selecionado {
    background-color: #ebf5ff;
    color: #2563eb;
    border-color: #3b82f6;
}

.iniciais-nome{
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 1000px;
    background-color: #1b459f;
    color: #e5e7eb;
    width: 30px;
    height: 30px;
}
.info-paciente{
    text-align: start;
}
.id-paciente {
    margin-left: auto;
    font-size: 12px;
    color: #7c8088;
    font-weight: 400;
    align-self: flex-end;
}
.data-paciente{
    color: #7c8088;
    font-size: 14px;
}
</style>
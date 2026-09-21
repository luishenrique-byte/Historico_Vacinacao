import axios from 'axios';

const BASE_URL = "http://localhost:8082/v1/profissional"

export function findById(id){
    return axios.get(BASE_URL + "/" + id)
}

export function findByCpf(cpf){
    return axios.get(BASE_URL + "/buscarPorCpf?cpf=" + cpf)
}

export function filterAtivo(cpf){
    return axios.get(BASE_URL + "/filtrarAtivo?cpf=" + cpf)
}
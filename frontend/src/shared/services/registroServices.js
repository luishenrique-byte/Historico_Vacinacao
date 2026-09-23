import axios from 'axios'

const BASE_URL = 'http://localhost:8082/v1/registro'

export function findAllByPaciente(id){
    return axios.get(BASE_URL + '/paciente/'+id)
}

export function save(registro){
    return axios.post(BASE_URL,registro)
}
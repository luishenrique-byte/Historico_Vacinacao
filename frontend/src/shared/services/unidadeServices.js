import axios from 'axios';

const BASE_URL = "http://localhost:8082/v1/unidade"

export function findAll(){
    return axios.get(BASE_URL)
}
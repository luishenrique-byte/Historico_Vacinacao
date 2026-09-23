import axios from "axios";

const BASE_URL = "http://localhost:8082/v1/vacina"

export function findAll(){
    return axios.get(BASE_URL)
}

export function findById(id){
    return axios.get(BASE_URL + "/" + id)
}
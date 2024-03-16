import axios from "axios"

const REST_API_BASE_URL = 'http://localhost:8080/api/trainers/';

export const listTrainers = () => axios.get(REST_API_BASE_URL);

export const createTrainer = (trainer) => axios.post(REST_API_BASE_URL, trainer);

export const getTrainer = (trainerId) => axios.get(REST_API_BASE_URL + '/' + trainerId);

export const updateTrainer = (trainerId, trainer) => axios.put(REST_API_BASE_URL + '/' + trainerId, trainer);

export const deleteTrainer = (trainerId) => axios.delete(REST_API_BASE_URL + '/' + trainerId);

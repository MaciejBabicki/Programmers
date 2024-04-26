import axios from "axios";
 
const REST_API_BASE_URL = 'http://localhost:8080/programmers';

export const listProgrammers = () => axios.get(REST_API_BASE_URL);

export const createProgrammer = (programmer) => axios.post(REST_API_BASE_URL, programmer);

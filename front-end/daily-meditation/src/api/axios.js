import axios, { AxiosResponse } from 'axios';

export const axiosClient = axios.create({
  // baseURL: 'http://'
  baseURL: 'http://localhost:8080/',
  withCredentials: false
});
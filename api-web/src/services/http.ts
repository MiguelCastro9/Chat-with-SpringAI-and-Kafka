import { environment } from '@environments/environment';
import axios from 'axios';

const http = axios.create({
  baseURL: environment.API_URL
});

http.interceptors.response.use(
  response => response,
  error => {
    console.log(error);
    return Promise.reject(error);
  }
);

export default http;

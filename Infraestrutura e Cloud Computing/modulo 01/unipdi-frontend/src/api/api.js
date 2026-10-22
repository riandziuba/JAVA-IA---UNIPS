import axios from "axios";

const api = axios.create({
  // Em Docker a URL é "/api" e o nginx faz proxy para o backend.
  // Fora do Docker, defina VITE_API_URL no .env (ex.: http://localhost:9090).
  baseURL: import.meta.env.VITE_API_URL || "/api",
});

export default api;

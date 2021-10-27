import axios from "axios"

const baseURL = process.env.NEXT_PUBLIC_BACKEND_URL || "http://localhost:5000"

const axiosInstance = axios.create({
    baseURL: baseURL,
    timeout: 30000,
    headers: {
        "Content-Type": "application/json",
        "Accept": "*/*",
    }
})

export default axiosInstance
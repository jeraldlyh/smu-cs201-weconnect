import axiosInstance from "src/axios"

export const getStatus = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.get("/status")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}